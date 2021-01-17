package com.hcteleweb.teleweb.config.aspect.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AspectNameUtils
{
    /**
     * 클래스 경로
     * @param proceedingJoinPoint
     * @return
     * @throws Exception
     */
    public String getClassPath(ProceedingJoinPoint proceedingJoinPoint) throws Exception
    {
        return proceedingJoinPoint.getTarget().getClass().getName();
    }
    
    /**
     * 메서드 경로
     * @param proceedingJoinPoint
     * @return
     * @throws Exception
     */
    public String getMehodPath(ProceedingJoinPoint proceedingJoinPoint) throws Exception
    {
        return proceedingJoinPoint.getSignature().toLongString();
    }
    
    /**
     * 메서드명
     * @param proceedingJoinPoint
     * @return
     * @throws Exception
     */
    public String getMehodName(ProceedingJoinPoint proceedingJoinPoint) throws Exception
    {
        return proceedingJoinPoint.getSignature().getName();
    }
}
