package com.foolchen.java.examples.others;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenchong
 * 2018/7/7 0007
 * 17:43
 */
@Target(ElementType.METHOD) // 表示当前注解用于方法
@Retention(RetentionPolicy.RUNTIME) // 表示该注解在运行时生效
public @interface UseCase {
  /**
   * 获取方法的id
   */
  int id();

  /**
   * 获取方法的描述，如果没有描述则采用默认值
   */
  String description() default "no description";
}
