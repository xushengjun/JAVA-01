package com.gane.maple.jdbc.routing.component;

import com.gane.maple.jdbc.routing.ClientDataSource;
import com.gane.maple.jdbc.routing.ClientDataSourceContextHolder;
import com.gane.maple.jdbc.routing.annotation.ReadOnly;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Random;


@Aspect
@Component
@Slf4j
public class DataSourceRoutingAspect {

    @Around("@annotation(readOnly)")
    public Object aroundDataSourceRouting(ProceedingJoinPoint joinPoint, ReadOnly readOnly)
            throws Throwable {
        ClientDataSource previousClient = ClientDataSourceContextHolder.getClientDatabase();
        System.out.println("readOnly:" + readOnly);
        ClientDataSource db = null;
        if (readOnly != null) {
            if (new Random().nextInt(2) % 2 == 0) {
                ClientDataSourceContextHolder.set(ClientDataSource.SLAVE);
            } else {
                ClientDataSourceContextHolder.set(ClientDataSource.SLAVE2);
            }
        }
        log.warn("Setting clientDatabase {} into DataSourceContext", ClientDataSourceContextHolder.getClientDatabase());

        try {
            return joinPoint.proceed();
        } finally {
            if (previousClient != null) {
                // revert context back to previous state after execute the method
                ClientDataSourceContextHolder.set(previousClient);
            } else {
                // there is no value being set into the context before, just clear the context
                // to prevent memory leak
                ClientDataSourceContextHolder.clear();
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(2));
    }
}
