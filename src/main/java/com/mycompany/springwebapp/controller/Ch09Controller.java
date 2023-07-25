package com.mycompany.springwebapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping("/fileuploadAjax")
	public String fileuploadAjax() {
		
		return "";
	}
	
}
