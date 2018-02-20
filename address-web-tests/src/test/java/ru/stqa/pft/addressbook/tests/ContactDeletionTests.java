package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.initContact();
      app.contact().create(new ContactData()
              .withFirstname("test1").withLastname("test2").withAddress("test3").withMobile("test4").withGroup("test1"), true);
    }
  }

  @Test(enabled = false)
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    System.out.println(deleteContact.getId());
    app.contact().delete(deleteContact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deleteContact);
    Assert.assertEquals(before, after);
  }
}