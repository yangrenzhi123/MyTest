package com.yang.test.java.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Validate {

	public boolean requierd() default false;

	public String requierdWithMsg() default "";

	public int maxLength() default 0;

	public int length() default 0;

	public String startWith() default "";

	public int[] amount() default {};
}