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
import com.mycompany.springwebapp.dto.Ch04Dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch04")
public class Ch04Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch04/content";
	}
	
	@PostMapping("/method1")
	public String method1(Ch04Dto dto) {
		log.info("Param1 : " + dto.getParam1());
		log.info("Param2 : " + dto.getParam2());
		log.info("Param3 : " + dto.getParam3());
		log.info("Param4 : " + dto.isParam4());
		log.info("Param5 : " + dto.getParam5());
		return "redirect:/ch04/content";
	}
}
