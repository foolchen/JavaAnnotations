package com.foolchen.java.annotations.bean;

import com.foolchen.java.annotations.database.Constraints;
import com.foolchen.java.annotations.database.DBTable;
import com.foolchen.java.annotations.database.SQLInteger;
import com.foolchen.java.annotations.database.SQLString;

/**
 * 使用注解来定义一个类，该类的定义可被解释为一个数据库表
 *
 * @author chenchong
 * 2018/7/7 0007
 * 21:43
 */
@DBTable(name = "MEMBER") // 表的名称为“MEMBER”
public class Member {
  @SQLString(30) String firstName;
  @SQLString(50) String lastName;
  @SQLInteger int age;
  @SQLString(value = 30, constraints = @Constraints(primaryKey = true)) String handle; // 该字段为表中的主键

  public String getHandle() {
    return handle;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override public String toString() {
    return handle;
  }

  public int getAge() {
    return age;
  }
}
