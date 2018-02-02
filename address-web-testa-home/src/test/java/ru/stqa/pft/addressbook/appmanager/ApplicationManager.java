package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  FirefoxDriver wd;

  private SessionHelper sessionHelper;
  private NavigatorHelper navigatorHelper;
  private ContactHelper contactHelper;

  public void init() {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    contactHelper = new ContactHelper(wd);
    navigatorHelper = new NavigatorHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void initContactCreation() {
    stop();
  }

  public void stop() {
    wd.findElement(By.linkText("add new")).click();
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigatorHelper getNavigatorHelper() {
    return navigatorHelper;
  }
}
