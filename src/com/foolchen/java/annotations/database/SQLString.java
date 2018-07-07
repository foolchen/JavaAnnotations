package com.foolchen.java.annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对数据库中的字符串类型进行定义
 *
 * @author chenchong
 * 2018/7/7 0007
 * 21:38
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {

  /**
   * 字符串的值
   */
  int value() default 0;

  /**
   * 字段名
   */
  String name() default "";


  /**
   * 字符串的约束，默认约束为{@link Constraints}中定义的默认值
   */
  Constraints constraints() default @Constraints;
}
