package com.foolchen.java.examples.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于定义一个字段的值在数据库中唯一
 *
 * @author chenchong
 * 2018/7/7 0007
 * 21:41
 */
@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME) public @interface Uniquness {

  Constraints constraints() default @Constraints(unique = true);
}
