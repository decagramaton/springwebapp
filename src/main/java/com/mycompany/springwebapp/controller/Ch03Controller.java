package com.mycompany.springwebapp.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
