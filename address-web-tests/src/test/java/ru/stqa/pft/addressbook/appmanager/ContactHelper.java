package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import javax.crypto.Cipher;
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
    attach(By.name("photo"), contactData.getPhoto());

    String contactGroup = contactData.getGroup();

   if (creation) {
      if(contactGroup != null) new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactGroup);
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void create(ContactData contact, boolean b) {
    fillContactForm(contact, b);
    submitContactCreation(By.xpath("//div[@id='content']/form/input[21]"));
    contactCashe = null;
    goToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModification();
    fillContactForm(contact, false);
    submitContactModification();
    contactCashe = null;
    goToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    isAlertPresent();
    contactCashe = null;
  }

  public void initContactModification() { click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a")); }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void goToHomePage() { click(By.cssSelector("#nav > ul > li:first-child > a")); }

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

  public int count() {
    return wd.findElements(By.cssSelector("[name=entry]")).size();
  }

  private Contacts contactCashe = null;

  public Contacts all() {
    if (contactCashe != null) {
      return new Contacts(contactCashe);
    }
    contactCashe = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable tr[name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElements(By.cssSelector("td")).get(2).getText();
      String lastname = element.findElements(By.cssSelector("td")).get(1).getText();
      String address = element.findElements(By.cssSelector("td")).get(3).getText();
      String mobile = element.findElements(By.cssSelector("td")).get(4).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCashe.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withMobile(mobile).withGroup(null));
    }
    return new Contacts(contactCashe);
  }

  public Set<ContactData> all1() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones));
    }
    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);

  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }
}
