package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
    //    app.getContactHelper().initUserCreation();
        app.getContactHelper().createContact(new ContactData("Max", "Bolshakov", "79214448476", "bolmaxim@gmail.com","test1"));
    //    app.getContactHelper().submitUserCreation();
    //    app.getContactHelper().returnToHomePage();
    }
}
