package com.ideal.studentlog.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Aspect
@Configuration
public class LoggingAspect {
	
	@Before("execution(* com.ideal.studentlog.controllers.*.*(..))")
	public void beforeRequestProcessing(JoinPoint joinPoint){
		log.info("Received Request on `{}` with the following argument(s): {}",
				joinPoint.getSignature(),
				joinPoint.getArgs());
	}

	@AfterThrowing(pointcut = "execution(* com.ideal.studentlog.*.*.*(..))", throwing = "ex")
	public void afterAllExceptions(Exception ex) {
		log.error(Arrays.toString(ex.getStackTrace()));
	}
}