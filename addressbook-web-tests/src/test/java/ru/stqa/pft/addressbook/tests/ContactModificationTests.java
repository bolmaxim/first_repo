package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactModificationTests extends TestBase {
    @Test
    public void testContactEdit() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Max", "Bolshakov", "79214448476", "bolmaxim@gmail.com","test1"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContactUser(before.size()-1);
        ContactData contact = new ContactData(before.get(before.size() -1).getId(),"Max", "Bolshakov", "79214448476", "bolmaxim@gmail.com","test1");
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().submitUserUpdate();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }


}
