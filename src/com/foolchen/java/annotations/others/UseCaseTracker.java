package com.foolchen.java.annotations.others;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 该类用于获取使用了{@link UseCase}注解的方法
 *
 * @author chenchong
 * 2018/7/7 0007
 * 17:53
 */
public class UseCaseTracker {

  public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
    // 获取所有已声明的方法
    Method[] declaredMethods = cl.getDeclaredMethods();

    for (Method method : declaredMethods) {
      // 从已声明方法尝试获取UseCase注解
      UseCase uc = method.getAnnotation(UseCase.class);

      if (uc != null) {
        // 如果uc不为空，则证明该方法确实被UseCase注解
        System.out.println("Found UseCase：" + uc.id() + " " + uc.description());

        // 如果该方法确实被注解，则从列表中移除，列表中仅留未被注解的方法
        useCases.remove(new Integer(uc.id()));
      }
    }
    for (Integer id : useCases) {
      System.out.println("Warning：Missing use case-" + id);
    }
  }

  public static void main(String[] args) {
    List<Integer> useCases = new ArrayList<>();
    Collections.addAll(useCases, 47, 48, 49, 50);
    trackUseCases(useCases, PasswordUtils.class);
  }
}
