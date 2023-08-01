package com.mycompany.springwebapp.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect7Around {
	@Around("@annotation(com.mycompany.springwebapp.aspect.RuntimeCheck)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		
		// 전처리 코드
		long start = System.nanoTime();
		
		Object result = joinPoint.proceed();
		
		// 후처리 코드
		long end = System.nanoTime();
		long howLong = end - start;
		
		// 1. @RuntimeCheck 가 있는 메소드 이름을 가져오는 코드
		String methodName = joinPoint.getSignature().toShortString();
		log.info(methodName + " 실행 시간 : " + howLong + "ns");
		
		// 2. 실행 시간을 Session에 저장
		// 2-1. 세션을 얻는 코드
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		
		session.setAttribute("methodName", methodName);
		session.setAttribute("howLong", howLong);
		
		return result;
	}
}
