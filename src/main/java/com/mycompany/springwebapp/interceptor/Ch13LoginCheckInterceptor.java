package com.mycompany.springwebapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycompany.springwebapp.dto.Ch13Member;
import com.mycompany.springwebapp.interceptor.Auth.Role;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch13LoginCheckInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//log.info("preHandler Run");
		
		//Step1. 요청 처리 메소드에 @login가 있는지 확인
		HandlerMethod hm = (HandlerMethod) handler;
		Login login = hm.getMethodAnnotation(Login.class);
		
		//Step2. login 존재 여부 확인
		//메소드에 @login이 있는 경우
		if(login != null) {
			Ch13Member member = (Ch13Member) request.getSession().getAttribute("ch13Login");
			
			if(member != null) {
				return true;
			} else {
				response.sendRedirect(request.getContextPath()+"/ch13/login");
				return false;
			}
		} else {
			return true;
		}
	}
}
