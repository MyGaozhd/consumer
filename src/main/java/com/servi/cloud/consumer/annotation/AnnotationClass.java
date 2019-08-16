package com.servi.cloud.consumer.annotation;

import java.lang.annotation.*;

/**
 * 作用于注解上的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnnotationClass {

}
