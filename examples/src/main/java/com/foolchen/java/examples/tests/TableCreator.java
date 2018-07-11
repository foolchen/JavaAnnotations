package com.foolchen.java.examples.tests;

import com.foolchen.java.examples.bean.Member;
import com.foolchen.java.examples.database.Constraints;
import com.foolchen.java.examples.database.DBTable;
import com.foolchen.java.examples.database.SQLInteger;
import com.foolchen.java.examples.database.SQLString;
import com.foolchen.java.examples.utils.ClassUtils;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link Member}类的解释器，用于将类的定义解释为表
 *
 * @author chenchong
 * 2018/7/7 0007
 * 21:50
 */
public class TableCreator {

  public String process(Class<?> cl) {
    // 获取表的注解
    DBTable dbTable = cl.getAnnotation(DBTable.class);
    if (dbTable == null) {
      System.out.println("No DBTable annotations in class " + cl.getName());
      return null;
    }

    // 获取到表的名称
    String tableName = dbTable.name();
    if (tableName.length() < 1) {
      // 没有表名，则使用类名作为表名
      tableName = cl.getName().toUpperCase();
    }

    // 尝试获取表中定义的字段
    List<String> columnsDef = new ArrayList<>();
    Field[] declaredFields = cl.getDeclaredFields();
    if (declaredFields != null) {
      for (Field field : declaredFields) {
        String s = processField(field);
        if (s != null) {
          columnsDef.add(s);
        }
      }
    }

    StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");

    for (String def : columnsDef) {
      createCommand.append("\n").append("\t").append(def).append(",");
    }
    // 移除掉最后一个逗号
    createCommand.deleteCharAt(createCommand.length() - 1);
    createCommand.append("\n);");
    return createCommand.toString();
  }

  private String processField(Field field) {
    String columnName;
    Annotation[] annotations = field.getDeclaredAnnotations();
    if (annotations.length < 1) return null;

    if (annotations[0] instanceof SQLInteger) {
      SQLInteger sInt = (SQLInteger) annotations[0];
      if (sInt.name().length() < 1) {
        columnName = field.getName().toUpperCase();
      } else {
        columnName = sInt.name();
      }

      return columnName + " INT" + getConstraints(sInt.constraints());
    }

    if (annotations[0] instanceof SQLString) {
      SQLString sString = (SQLString) annotations[0];
      if (sString.name().length() < 1) {
        columnName = field.getName().toUpperCase();
      } else {
        columnName = sString.name();
      }

      return columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(
          sString.constraints());
    }
    throw new IllegalArgumentException(
        "Undefined annotation : " + annotations[0].getClass().getName());
  }

  private String getConstraints(Constraints constraints) {
    String result = "";
    if (!constraints.allowNull()) {
      result += " NOT NULL";
    }

    if (constraints.primaryKey()) {
      result += " PRIMARY KEY";
    }

    if (constraints.unique()) {
      result += " UNIQUE";
    }
    return result;
  }

  public static void main(String[] args) throws ClassNotFoundException {
    /*if (args.length < 1) {
      System.out.println("arguments: annotated classes");
      System.exit(0);
    }*/
    List<String> classes = null;
    try {
      classes = ClassUtils.getClassesForPackage("com.foolchen.java.examples.bean");
    } catch (IOException e) {
      e.printStackTrace();
    }
    TableCreator processor = new TableCreator();

    if (classes == null) {
      System.out.println("arguments: annotated classes");
      System.exit(0);
    }

    for (String className : classes) {
      Class<?> clazz = Class.forName(className);
      String sql = processor.process(clazz);
      if (sql == null) continue;

      System.out.println("Table creation SQL for " + clazz.getName() + " is:\n" + sql);
    }
  }
}
