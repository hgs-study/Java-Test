package com.javatest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



//@Test
//@Tag("fast") 두개의 어노테이션을 메타 어노테이션으로 사용할 수 있다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Test
@Tag("fast")
public @interface FastTest {
}

