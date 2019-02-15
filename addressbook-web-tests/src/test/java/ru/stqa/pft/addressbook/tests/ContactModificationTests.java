package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactModificationTests extends TestBase {
    @Test
    public void testContactEdit() {

        app.getContactHelper().editContactUser();
        app.getContactHelper().fillContactForm(new ContactData("Max", "Bolshakov", "79214448476", "bolmaxim@gmail.com",null),false);
        app.getContactHelper().submitUserUpdate();
        app.getContactHelper().returnToHomePage();

    }


}
