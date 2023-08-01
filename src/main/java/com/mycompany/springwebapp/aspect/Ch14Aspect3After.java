package com.mycompany.springwebapp.aspect;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@Order(2)
public class Ch14Aspect3After {
	@After("execution(public * com.mycompany.springwebapp.controller.Ch14Controller.after*(..))")
	
	// pointcut의 메소드가 실행된 후, 실행하는 메소드
	public void weavingMethod() {
		log.info("실행");
	}
}
