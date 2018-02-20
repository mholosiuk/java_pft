package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void modify(ContactData contact) {
    initContactModification();
    fillContactForm(contact, false);
    submitContactModification();
    goToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    isAlertPresent();
  }

  public void initContactModification() { click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a")); }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void goToHomePage() { click(By.cssSelector("#nav > ul > li:first-child > a")); }

  public void create(ContactData contact, boolean b) {
    fillContactForm(contact, b);
    submitContactCreation(By.xpath("//div[@id='content']/form/input[21]"));
    goToHomePage();
  }

  public void deleteSelectedContacts(By xpath) {
    wd.findElement(xpath).click();
  }

 /* public void selectContact(int index) {
    if (! wd.findElements(By.name("selected[]")).get(index).isSelected()) {
      wd.findElements(By.name("selected[]")).get(index).click();
    }
  }
*/
  public void selectContactById(int id) {
    if (! wd.findElement(By.cssSelector("input[value='" + id + "']")).isSelected()) {
      wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert().accept();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//div[@id='content']/form[1]/input[22]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable tr[name='entry']"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname("test1").withLastname("test2").withAddress("test3").withMobile("test4").withGroup(null));
    }
    return contacts;
  }
}
