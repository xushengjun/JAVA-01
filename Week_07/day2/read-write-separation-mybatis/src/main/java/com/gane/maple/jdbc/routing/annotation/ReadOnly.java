package com.gane.maple.jdbc.routing.annotation;

import com.gane.maple.jdbc.routing.ClientDataSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnly {

    ClientDataSource value() default ClientDataSource.SLAVE;
}
