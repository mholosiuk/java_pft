package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigatorHelper extends HelperBase {

  NavigatorHelper(FirefoxDriver wd) {
    super (wd);
  }

  public void goToHomePage() {
      click(By.linkText("home page"));
  }
}
