package com.foolchen.java.annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解定义了字段的约束
 *
 * @author chenchong
 * 2018/7/7 0007
 * 20:55
 */
@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME) public @interface Constraints {

  /**
   * 当前字段是否为表的主键，默认为false
   */
  boolean primaryKey() default false;

  /**
   * 当前字段是否可为空，默认为true
   */
  boolean allowNull() default true;

  /**
   * 当前字段在表中是否唯一，默认为false
   */
  boolean unique() default false;
}
