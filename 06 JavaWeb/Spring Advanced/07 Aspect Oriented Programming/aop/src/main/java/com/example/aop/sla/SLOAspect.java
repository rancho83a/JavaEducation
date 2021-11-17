package com.example.aop.sla;

import com.example.aop.modifying.ModifyingExample;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.aop.sla.SLOsConfig.SLOConfig;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class SLOAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(ModifyingExample.class);

    private final SLOsConfig slosConfig;

    public SLOAspect(SLOsConfig slosConfig) {
        this.slosConfig = slosConfig;
    }


    @Around(value = "@annotation(TrackLatency)")
    public Object trackLatency(ProceedingJoinPoint pjp, TrackLatency TrackLatency) throws Throwable {
        String latencyId = TrackLatency.latency();
        SLOConfig config = slosConfig.getSlos().stream()
                .filter(s -> s.getId().equals(latencyId))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("SLO with ID " + latencyId + "is not configured."
                ));

        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Object result = pjp.proceed();
        stopWatch.stop();

        long actualExecutionTime = stopWatch.getLastTaskTimeMillis();
        if(actualExecutionTime>config.getTreshold()){
            //basicly we can have complex calculation here
            LOGGER.warn("Method {} was too slow. Execution time {} millis", latencyId, actualExecutionTime );
        }
        return result;

    }
}
