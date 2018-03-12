package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public WebDriver wd = new ChromeDriver();

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;
  private final Properties properties;

  public ApplicationManager(String browser) {
    this.browser = browser;
    String target = System.getProperty("target", "local");
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser == BrowserType.FIREFOX) {
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    } else if (browser == BrowserType.CHROME) {
      System.setProperty("webdriver.chrome.driver", "/Users/vladislavfilin/Documents/GitHub/java_pft/address-web-tests/src/test/resources/chromedriver");
      wd = new ChromeDriver();
    } else if (browser == BrowserType.SAFARI) {
      wd = new SafariDriver();
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    SessionHelper sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void initContact() { stop(); }

  public void stop() { wd.findElement(By.linkText("add new")).click(); }

  public GroupHelper group() { return groupHelper; }

  public NavigationHelper goTo() { return navigationHelper; }

  public ContactHelper contact() { return contactHelper; }

}
