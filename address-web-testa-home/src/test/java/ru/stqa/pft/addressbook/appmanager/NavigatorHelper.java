package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigatorHelper extends HelperBase {

  NavigatorHelper(WebDriver wd) {
    super ((ChromeDriver) wd);
  }

  public void goToHomePage() {
      click(By.linkText("home page"));
  }
}
