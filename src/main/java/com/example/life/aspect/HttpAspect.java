package com.example.life.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HttpAspect {
	
	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	
	@Pointcut("execution(public * com.example.life.controller.SignController.*(..))")
	public void log() {
	};
	
	@Before("log()")
	public void doBefore(JoinPoint joinpoint ) {
		ServletRequestAttributes attributtes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributtes.getRequest();
		//url
		logger.info("url={}", request.getRequestURL());
		//method
		logger.info("method={}", request.getMethod());
		//ip
		logger.info("ip={}", request.getRemoteAddr());
		//类方法
		logger.info("class_method={}", joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName());
		//参数
		logger.info("args={}", joinpoint.getArgs());
	}
	
	@After("log()")
	public void doAfter(){
		logger.info("2222222222");
	};
	
	@AfterReturning(returning = "object",  pointcut = "log()")
	public void doAfterReturning(Object object) {
		if(object != null) {
			logger.info("response={}", object.toString());
		}
	}
}
