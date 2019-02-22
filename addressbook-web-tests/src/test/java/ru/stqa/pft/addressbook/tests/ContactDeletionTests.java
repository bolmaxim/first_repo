package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactCreation() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Max", "Bolshakov", "79214448476", "bolmaxim@gmail.com","test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContactUser(before.size()-1);
        app.getContactHelper().deleteContactUser();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(before.size() -1);
        Assert.assertEquals(before, after);
    }
}
