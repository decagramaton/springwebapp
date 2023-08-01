package com.mycompany.springwebapp.aspect;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect4AfterReturning {
	@AfterReturning (
			pointcut="execution(public * com.mycompany.springwebapp.controller.Ch14Controller.afterReturning(..))",
			returning="returnValue"
			)
	
	//pointcut의 메소드가 반환하는 값을 입력 값으로 받는다.
	public void method(String returnValue) {
		log.info("실행, 리턴 값 : " + returnValue);
	}
}
