package ru.stqa.pft.addressbook.tasts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.initContactCreation();
        app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "test4", "test1"), true);
    }

}
