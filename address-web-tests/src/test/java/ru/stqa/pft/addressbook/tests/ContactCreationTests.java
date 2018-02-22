package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts () {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {
            new ContactData().withFirstname("test1").withLastname("test1").withAddress("test1").withMobile("test1").withGroup("test1")});
    list.add(new Object[] {
            new ContactData().withFirstname("test2").withLastname("test2").withAddress("test2").withMobile("test2").withGroup("test1")});
    list.add(new Object[] {
            new ContactData().withFirstname("test3").withLastname("test3").withAddress("test3").withMobile("test3").withGroup("test1")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    File photo = new File("src/test/resources/orionfull_jcc_big.jpg");
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.initContact( );
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    System.out.println(after);
    System.out.println(before);
    System.out.println(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt())));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("test").withLastname("test2").withAddress("test3").withMobile("test4").withGroup("test1");
    app.initContact();
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }
}


