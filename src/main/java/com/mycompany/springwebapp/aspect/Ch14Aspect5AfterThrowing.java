package com.mycompany.springwebapp.aspect;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect5AfterThrowing {
	@AfterThrowing(
			pointcut="execution(public * com.mycompany.springwebapp.controller.Ch14Controller.afterThrowing(..))",
			throwing="e"
			)
	
	//pointcut의 메소드에 예외 발생 시, 예외 값을 메소드로 받는다.(잘 안씀, 걍 ControllerAdvice 사용 ㄱ)
	public void method(Throwable e) {
		log.info("실행, 예외 값 : " + e);
	}
}
