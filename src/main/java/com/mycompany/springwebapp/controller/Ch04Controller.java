package com.mycompany.springwebapp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch04Form1;
import com.mycompany.springwebapp.dto.Ch04Form2;
import com.mycompany.springwebapp.dto.joinForm;
import com.mycompany.springwebapp.dto.loginForm;
import com.mycompany.springwebapp.validator.Ch04Form1Validator;
import com.mycompany.springwebapp.validator.Ch04Form2Validator;
import com.mycompany.springwebapp.validator.Ch04Form3Validator;
import com.mycompany.springwebapp.validator.Ch04Form4Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch04")
public class Ch04Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch04/content";
	}
	
	@InitBinder("ch04Form1")
	public void ch04FormValidator(WebDataBinder binder) {
		binder.setValidator(new Ch04Form1Validator());
	}
	
	@PostMapping("/method1")
	// pom.xml에 validation-api 라이브러리 의존 설정 필요
	// request.setAttribute("ch04Form1", form1); 으로 객체를 자동 저장
	public String method1(@Valid Ch04Form1 form1, Errors errors) {
		
		// errors.rejectValue()가 한번이라도 호출되었다면 hasErrors()는 true를 리턴한다.
		if(errors.hasErrors()) {
			log.info(errors.toString());
			return "ch04/content";
		}
		
		// 요청 처리 코드
		log.info("Param1 : " + form1.getParam1());
		log.info("Param2 : " + form1.getParam2());
		log.info("Param3 : " + form1.getParam3());
		log.info("Param4 : " + form1.isParam4());
		log.info("Param5 : " + form1.getParam5());
		
		return "redirect:/";
	}
	
	
	@InitBinder("ch04Form2")
	public void ch04Form2Validator(WebDataBinder binder) {
		binder.setValidator(new Ch04Form2Validator());
	}
	
	@PostMapping("/method2")
	public String method2(@Valid Ch04Form2 form2, Errors errors) {
		
		if(errors.hasErrors()) {
			log.info(errors.toString());
			return "ch04/content";
		}
		
		// 요청 처리 코드
		log.info("Param1 : " + form2.getSsn());
		log.info("Param2 : " + form2.getYearMonthDay());
		log.info("Param3 : " + form2.getPassword());
		log.info("Param4 : " + form2.isPower());
		log.info("Param5 : " + form2.getCalendar());
		
		return "redirect:/";
	}
	
	
	@InitBinder("joinForm")
	public void ch04Form3Validator(WebDataBinder binder) {
		binder.setValidator(new Ch04Form3Validator());
	}
	
	@PostMapping("/join")
	public String join(@Valid joinForm form3, Errors errors) {
		
		if(errors.hasErrors()) {
			log.info(errors.toString());
			return "ch04/content";
		}
		
		// 요청 처리 코드
		log.info("Param1 : " + form3.getMid());
		log.info("Param2 : " + form3.getMpassword());
		log.info("Param3 : " + form3.getMemail());
		log.info("Param4 : " + form3.getMtel());
		
		return "redirect:/";
	}
	
	
	@InitBinder("loginForm")
	public void loginFormValidator(WebDataBinder binder) {
		binder.setValidator(new Ch04Form4Validator());
	}
	
	@PostMapping("/login")
	public String login(@Valid loginForm loginform, Errors errors) {
		
		if(errors.hasErrors()) {
			log.info(errors.toString());
			return "ch04/content";
		}
		
		// 요청 처리 코드
		log.info("Param1 : " + loginform.getMid());
		log.info("Param2 : " + loginform.getMpassword());
		
		return "redirect:/";
	}
}
