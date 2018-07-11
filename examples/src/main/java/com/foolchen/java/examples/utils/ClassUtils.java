package com.foolchen.java.examples.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author chenchong
 * 2018/7/7 0007
 * 22:21
 */
public class ClassUtils {

  /**
   * 获取一个包下的所有类
   *
   * @param packageName 要获取类的包名
   * @return 类的列表
   */
  public static List<String> getClassesForPackage(String packageName) throws IOException {
    List<String> classes = new ArrayList<>();
    // 首先根据包名获取到包的相对路径
    String packagePath = packageName.replaceAll("\\.", getFileSeparator());
    System.out.println("packagePath: " + packagePath);
    Enumeration<URL> dirs =
        Thread.currentThread().getContextClassLoader().getResources(packagePath);
    while (dirs.hasMoreElements()) {
      URL url = dirs.nextElement();
      String protocol = url.getProtocol();
      if ("file".equals(protocol)) {
        classes.addAll(
            getClassesForFile(new File(URLDecoder.decode(url.getFile(), "UTF-8")), packageName));
      } else {
        System.out.println("Unsupported protocol : " + protocol);
      }
    }
    return classes;
  }

  private static List<String> getClassesForFile(File file, String packageName) {
    List<String> classes = new ArrayList<>();
    String path = file.getPath();
    if (file.isDirectory()) {
      // 如果该文件是文件夹，则递归获取Class
      File[] files = file.listFiles();
      if (files != null) {
        for (File f : files) {
          classes.addAll(getClassesForFile(f,
              f.isDirectory() ? packageName + "." + f.getName() : packageName));
        }
      }
    } else {
      classes.add(packageName + "." + path.substring(path.lastIndexOf(File.separator) + 1,
          path.lastIndexOf(".")));
    }
    return classes;
  }

  private static String getFileSeparator() {
    char separator = File.separatorChar;
    if (separator == '\\') {
      return "\\\\";
    } else {
      return File.separator;
    }
  }
}
