package com.user.registration.ms.aspect;

import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoggingAspect.class);

    @PostConstruct
    public void init() {
        LOGGER.info("### LoggingAspect LOADED ###");
    }

    @Around("""
            execution(* com.user.registration.ms.controller..*(..))
            || execution(* com.user.registration.ms.service..*(..))
            """)
    public Object logCall(ProceedingJoinPoint jointPoint) throws Throwable {

        String methodName =
                jointPoint.getSignature().toShortString();

        LOGGER.info(
                "Calling {} with inputs {}",
                methodName,
                Arrays.toString(jointPoint.getArgs())
        );

        long start = System.nanoTime();

        try {
            Object result = jointPoint.proceed();

            LOGGER.info(
                    "{} returned {}",
                    methodName,
                    result
            );

            return result;

        } catch (Throwable t) {
            LOGGER.error(
                    "{} failed: {}",
                    methodName,
                    t.getMessage()
            );
            throw t;
        } finally {
            long durationMs =
                    (System.nanoTime() - start) / 1_000_000;
            LOGGER.info(
                    "{} processed in {} ms",
                    methodName,
                    durationMs
            );
        }
    }
}
