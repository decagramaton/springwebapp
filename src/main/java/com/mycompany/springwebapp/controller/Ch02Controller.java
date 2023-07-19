package com.mycompany.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch02")
public class Ch02Controller {
	
	@RequestMapping("/content")
	public String content() {
		return "ch02/content";
	}
	
	@GetMapping("/method")
	public String getMethod(String bkind, int bno) {
		log.info("getMethod > bkind : " + bkind + " |  bno : " + bno);
		return "ch02/content";
	}
	
	@PostMapping("/method")
	public String postMethod(String bkind, int bno) {
		log.info("postMethod > bkind : " + bkind + " |  bno : " + bno);
		return "ch02/content";
	}
}
