package com.mycompany.springwebapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch05")
public class Ch05Controller {
	
	@RequestMapping("/content")
	public String content() {
		return "ch05/content";
	}
	
	@GetMapping("/getHeaderValue")
	public String getHeaderValue(HttpServletRequest request, @RequestHeader("User-Agent") String userAgent) {
		
		log.info(request.getRemoteAddr());
		log.info(userAgent);
		
		
		return "redirect:/ch05/content";
	}
	
	
	@RequestMapping(value="/createCookie", method=RequestMethod.GET)
	public String createCookie(HttpServletResponse response) {
		
		Cookie cookie = new Cookie("useremail", "summer@mycompany.com");
		cookie.setDomain("localhost");
		cookie.setPath("/");
		cookie.setMaxAge(30*60);
		
		// JavaScript에서 쿠키 정보 읽기 가능 여부 설정.
		// 즉, HTTP로 요청 시에만 쿠키 데이터 사용 가능
		cookie.setHttpOnly(true);
		
		cookie.setSecure(false);
		response.addCookie(cookie);
		
		
		return "redirect:/ch05/content";
	}
}
