package com.mycompany.springwebapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch11City;
import com.mycompany.springwebapp.dto.Ch11Member;
import com.mycompany.springwebapp.dto.Ch11Skill;
import com.mycompany.springwebapp.service.Ch12ServiceCreateByXml;
import com.mycompany.springwebapp.service.Ch12ServiceDiByAnnotation;
import com.mycompany.springwebapp.service.Ch12ServiceDiByXml;
import com.mycompany.springwebapp.service.Ch12ServicePropertyDi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch12")
public class Ch12Controller {
	@Resource
	private Ch12ServiceDiByXml serviceDiByXml;
	
	@Resource
	private Ch12ServiceDiByAnnotation serviceDiByAnnotation;
	
	@Resource
	private Ch12ServicePropertyDi servicePropertyDi;
	
	@RequestMapping("/content")
	public String content() {
		// log.info("실행");
		//serviceDiByXml.method();
		//serviceDiByAnnotation.method();
		servicePropertyDi.method();
		return "ch12/content";
	}
}
