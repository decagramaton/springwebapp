package com.mycompany.springwebapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.springwebapp.dto.Ch03Dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch03")
public class Ch03Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch03/content";
	}
	
	@GetMapping("/method1")
	public String method1(String param1, @RequestParam(defaultValue="0") int param2, @RequestParam(defaultValue="0.0") double param3, @RequestParam(defaultValue="false") boolean param4, @DateTimeFormat(pattern="yyyy-MM-dd") Date param5) {
		log.info("pram1 : " + param1);
		log.info("pram2 : " + param2);
		log.info("pram3 : " + param3);
		log.info("pram4 : " + param4);
		log.info("pram5 : " + param5);
		return "redirect:/ch03/content";
	}
	
	
	@GetMapping("/method2")
	public String method2(@RequestParam(value="param1") String arg1,
			@RequestParam(value="param2", defaultValue="0.0") int arg2,
			@RequestParam(defaultValue="0.0") double param3,
			@RequestParam(defaultValue="false") boolean param4,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date param5) {
		log.info("pram1 : " + arg1);
		log.info("pram2 : " + arg2);
		log.info("pram3 : " + param3);
		log.info("pram4 : " + param4);
		log.info("pram5 : " + param5);
		return "redirect:/ch03/content";
	}
	
	
	@PostMapping("/method3")
	public String method3(@RequestParam(required=true)String param1,
			@RequestParam(defaultValue="0.0") int param2,
			@RequestParam(defaultValue="0.0") double param3,
			@RequestParam(defaultValue="false") boolean param4,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date param5) {
		log.info("pram1 : " + param1);
		log.info("pram2 : " + param2);
		log.info("pram3 : " + param3);
		log.info("pram4 : " + param4);
		log.info("pram5 : " + param5);
		return "redirect:/ch03/content";
	}
	
	@RequestMapping("/GetObjectParameterMethod")
	public void GetObjectParameterMethod(Ch03Dto dto, HttpServletResponse response) throws IOException {
		log.info("pram1 : " + dto.getParam1());
		log.info("pram2 : " + dto.getParam2());
		log.info("pram3 : " + dto.getParam3());
		log.info("pram4 : " + dto.isParam4());
		log.info("pram5 : " + dto.getParam5());
		
		JSONObject root = new JSONObject();
		root.put("result", "success");
		String responseJson = root.toString();
		
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter pw = response.getWriter();
		pw.print(responseJson);
		pw.flush();
		pw.close();
	}
	
	
	@RequestMapping("/GetJSONParameterMethod")
	public void GetJSONParameterMethod(@RequestBody Ch03Dto dto, HttpServletResponse response) throws IOException {
		log.info("pram1 : " + dto.getParam1());
		log.info("pram2 : " + dto.getParam2());
		log.info("pram3 : " + dto.getParam3());
		log.info("pram4 : " + dto.isParam4());
		log.info("pram5 : " + dto.getParam5());
		
		JSONObject root = new JSONObject();
		root.put("result", "success");
		String responseJson = root.toString();
		
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter pw = response.getWriter();
		pw.print(responseJson);
		pw.flush();
		pw.close();
	}
}
