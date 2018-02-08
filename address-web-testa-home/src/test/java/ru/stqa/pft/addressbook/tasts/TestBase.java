package ru.stqa.pft.addressbook.tasts;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import javax.swing.plaf.basic.BasicArrowButton;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
      app.getContactHelper().wd.quit();
  }

  public ApplicationManager getApp() {
    return app;
  }
}
