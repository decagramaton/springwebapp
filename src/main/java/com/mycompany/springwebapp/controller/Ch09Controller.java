package com.mycompany.springwebapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springwebapp.dto.Ch09FileUpload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	
	@PostMapping("/fileupload")
	public String fileupload(String title, String desc, MultipartFile attach) throws Exception {
		log.info("title: " + title);
		log.info("desc: " + desc);
		log.info("file name: " + attach.getOriginalFilename());
		log.info("file content-type: " + attach.getContentType());
		log.info("file size: " + attach.getSize());
		
		// 받은 파일을 영구적으로 저장하기
		String saveFileName = new Date().getTime() + "-" + attach.getOriginalFilename();
		String saveFilePath = "C:/OTI/uploadFiles/" + saveFileName;
		File file = new File(saveFilePath);
		attach.transferTo(file);
		
		return "redirect:/ch09/content";
	}
	
	@PostMapping("/fileuploadDto")
	public String fileuploadDto(Ch09FileUpload fileUpload, HttpSession session) throws Exception {
		log.info("title: " + fileUpload.getTitle());
		log.info("desc: " + fileUpload.getDesc());
		log.info("file name: " + fileUpload.getAttach().getOriginalFilename());
		log.info("file content-type: " + fileUpload.getAttach().getContentType());
		log.info("file size: " + fileUpload.getAttach().getSize());
		
		// 받은 파일을 영구적으로 저장하기
		String saveFileName = new Date().getTime() + "-" + fileUpload.getAttach().getOriginalFilename();
		String saveFilePath = "C:/OTI/uploadFiles/" + saveFileName;
		File file = new File(saveFilePath);
		fileUpload.getAttach().transferTo(file);
		
		session.setAttribute("saveFileName", saveFileName);
		
		return "redirect:/ch09/content";
	}
	
	@PostMapping(value="/fileuploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(Ch09FileUpload fileUpload) throws Exception {
		log.info("title: " + fileUpload.getTitle());
		log.info("desc: " + fileUpload.getDesc());
		log.info("file name: " + fileUpload.getAttach().getOriginalFilename());
		log.info("file content-type: " + fileUpload.getAttach().getContentType());
		log.info("file size: " + fileUpload.getAttach().getSize());
		
		// 받은 파일을 영구적으로 저장하기
		String saveFileName = new Date().getTime() + "-" + fileUpload.getAttach().getOriginalFilename();
		String saveFilePath = "C:/OTI/uploadFiles/" + saveFileName;
		File file = new File(saveFilePath);
		fileUpload.getAttach().transferTo(file);
		
		JSONObject JSONObject = new JSONObject();
		JSONObject.put("result", "success");
		JSONObject.put("saveFileName", saveFileName);
		String json = JSONObject.toString();
		
		return json;
	}
	
	
	@GetMapping("/filedownload")
	public void filedownload(String saveFileName, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fileName = saveFileName;
		String filePath = "C:/OTI/uploadFiles/" + fileName;
		
		String mimeType = request.getServletContext().getMimeType(filePath);
		response.setContentType(mimeType);
		
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName +"\"");
		
		OutputStream os = response.getOutputStream();
		Path path = Paths.get(filePath);
		Files.copy(path, os);
		os.flush();
		os.close();
	}
}
