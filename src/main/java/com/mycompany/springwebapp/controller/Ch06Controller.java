package com.mycompany.springwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch06")
public class Ch06Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
}
