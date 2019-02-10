package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().editContactUser();
        app.getContactHelper().deleteContactUser();
    }
}
