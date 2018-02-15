package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigatorHelper navigatorHelper;
  private ContactHelper contactHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser == BrowserType.FIREFOX) {
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    } else if (browser == BrowserType.CHROME) {
      System.setProperty("webdriver.chrome.driver", "/Users/vladislavfilin/Documents/GitHub/java_pft/address-web-testa-home/src/test/resources/chromedriver");
      wd = new ChromeDriver();
    } else if (browser == BrowserType.SAFARI) {
      wd = new SafariDriver();
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");
    contactHelper = new ContactHelper(wd);
    navigatorHelper = new NavigatorHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void initContactCreation() {
    stop();
  }

  private void stop() {
    wd.findElement(By.linkText("add new")).click();
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigatorHelper getNavigatorHelper() {
    return navigatorHelper;
  }
}
