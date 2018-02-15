package ru.stqa.pft.addressbook.tasts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{


  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact (new ContactData("test1", "test2", "test3", "test4", "test1"), true);
    }
    app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test4", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goToHomePage();
  }






}


