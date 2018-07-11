package com.foolchen.java.examples.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于定义一个数据库中的表
 *
 * @author chenchong
 * 2018/7/7 0007
 * 20:46
 */
@Target(ElementType.TYPE) @Retention(RetentionPolicy.RUNTIME) public @interface DBTable {

  /**
   * 指定表的名称
   *
   * @return 默认值为""
   */
  String name() default "";
}
