package com.mycompany.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController{
	
	@RequestMapping("/")	// http://localhost:8080/sptringwebapp/
	public String index() {
		return "index";
	}

}
