package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.nio.channels.SelectableChannel;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super((ChromeDriver) wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void goToHomePage() {
    click(By.linkText("home page"));
  }

  public void createContact(ContactData contact, boolean b) {
    fillContactForm(contact, b);
    submitContactCreation(By.xpath("//div[@id='content']/form/input[21]"));
    goToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();

  }
}
