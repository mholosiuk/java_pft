package ru.stqa.pft.addressbook.tasts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.initContactCreation();
        app.fillContactData(new ContactData("test1", "test2", "test3", "test4"));
        app.submitContactCreation();
        app.goToHomePage();
    }

}
