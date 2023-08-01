package com.mycompany.springwebapp.aspect;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect6Around {
	
	@Around("execution(public * com.mycompany.springwebapp.controller.Ch14Controller.around*(..))")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//전처리 공통코드
		log.info("전처리");
		
		// proceed:앞으로 나아가다
		// proceed : 핵심 로직(비즈니스 로직)을 실행한다.
		// execution()에 설정한 around*(..) 메소드 호출
		Object result = joinPoint.proceed();
		
		
		// 후처리 공통코드
		log.info("후처리");
		
		return result;
	}
}
