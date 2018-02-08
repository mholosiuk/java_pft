package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {
  public ChromeDriver wd;

  public HelperBase(ChromeDriver wd) {
    this.wd = wd;
  }

  public void submitContactCreation(By locator) {
    click(locator);
  }

  void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    submitContactCreation(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
