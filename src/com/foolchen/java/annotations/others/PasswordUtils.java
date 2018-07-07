package com.foolchen.java.annotations.others;

import java.util.List;

/**
 * @author chenchong
 * 2018/7/7 0007
 * 17:45
 */
public class PasswordUtils {

  @UseCase(id = 47, description = "Passwords must contain at lease one numeric")
  public boolean validatePassword(String password) {
    return password.matches("\\w*\\d\\w*");
  }

  @UseCase(id = 48) public String encryptPassword(String password) {
    return new StringBuilder(password).reverse().toString();
  }

  @UseCase(id = 49, description = "New passwords can't equal previously used ones")
  public boolean checkForNewPassword(List<String> prevPasswords, String password) {
    return !prevPasswords.contains(password);
  }
}
