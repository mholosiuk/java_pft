package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Locale;
import java.util.NoSuchElementException;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super((ChromeDriver) wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
           && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void homePage() {
//    if (isElementPresent(By.id("maintable"))){
//      return;
//    }
    click(By.cssSelector("#nav > ul > li:first-child > a"));
  }

  protected boolean isElementPresent(By locator) {
    try { wd.findElement(locator);
      return true;
} catch (NoSuchElementException ex) {
      return false;
    }

  }
}
