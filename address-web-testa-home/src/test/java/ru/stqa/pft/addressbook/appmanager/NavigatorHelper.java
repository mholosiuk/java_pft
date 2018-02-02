package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigatorHelper {
  FirefoxDriver wd;

  NavigatorHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void goToHomePage() {
      wd.findElement(By.linkText("home page")).click();
  }
}
