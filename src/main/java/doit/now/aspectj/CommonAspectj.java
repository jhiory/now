package com.example.vi.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CommonAspectj {
	private static final Logger logger = LoggerFactory.getLogger(CommonAspectj.class);

	@Autowired
    MessageSourceAccessor messageSource;

	@Pointcut("execution(* com.example.vi.security.controller.*Controller.*(..))")
	public void getControllerView() {
	}

	@Around("getControllerView()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		String reqUri = request.getRequestURI();
		String method = request.getMethod();
		String contentType = request.getHeader("Content-Type");
		String remoteAddr = request.getRemoteAddr();

		logger.info("==========================================================");
		logger.info("[API]" + messageSource.getMessage("logger.aspect.1"));
		logger.info("[API]Class      : {}", joinPoint.getTarget());
		logger.info("[API]Method     : {}", method);
		logger.info("[API]RequestURI : {}", reqUri);
		logger.info("[API]ContentType: {}", contentType);
		logger.info("[API]RemoteAddr : {}", remoteAddr);
		logger.info("==========================================================");

		SecurityContext aaa = SecurityContextHolder.getContext();

		logger.info("[API]" + "============================ Parameter ==============================" + aaa);
		for (int i = 0; i < joinPoint.getArgs().length; i++) {
			logger.info("[API]" + joinPoint.getArgs()[i]);
		}

		logger.info("[API]" + "====================================================================");
		Object result = joinPoint.proceed(joinPoint.getArgs());

		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("[API]" + messageSource.getMessage("logger.aspect.2") + messageSource.getMessage("logger.aspect.3") + "[{}]", timeTaken);
		logger.info("[API]" + "Result : {}", result);

		return result;
	}
}

