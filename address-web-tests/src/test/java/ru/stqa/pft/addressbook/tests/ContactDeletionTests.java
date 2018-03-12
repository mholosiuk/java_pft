package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    System.out.println(deleteContact.getId());
    app.contact().delete(deleteContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deleteContact)));
  }
}