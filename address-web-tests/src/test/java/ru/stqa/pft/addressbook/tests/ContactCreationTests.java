package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = false)
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData()
                .withFirstname("test1").withLastname("test2").withAddress("test3").withMobile("test4").withGroup("test1");
        app.initContact();
        app.contact().create(contact, true);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

//        int max = 0;
//        for (ContactData c : after) {
//            if (c.getId() > max) {
//                max = c.getId();
//            }
//        }

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());;
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
