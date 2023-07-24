package com.mycompany.springwebapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
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
		
		// 쿠키를 재전송할 서버 지정
		cookie.setDomain("localhost");
		
		// 쿠키를 재전송할 경로 지정
		cookie.setPath("/");
		
		// 쿠키 저장 기간 (유효 기간) - sec
		// 시간 설정 시  브라우저 종료할 때 쿠키도 같이 소멸
		// 시간 미설정 시, 브라우저 메모리에 저장된다. 재사용 가능
		cookie.setMaxAge(30*60);
		
		// JavaScript에서 쿠키 정보 읽기 가능 여부 설정.
		// 즉, HTTP로 요청 시에만 쿠키 데이터 사용 가능 여부 설정
		// true : 서버만 이용
		// false : JavaScript에서 쿠기 데이터 사용 허용 
		cookie.setHttpOnly(true);
		
		// true : only https,  false : http, https
		cookie.setSecure(false);
		response.addCookie(cookie);
		
		
		return "redirect:/ch05/content";
	}
	
	@RequestMapping(value="/getCookie", method=RequestMethod.GET)
	public String getCookie(@CookieValue("useremail") String userEmail) {
		log.info(userEmail);
		return "redirect:/ch05/content";
	}
}
