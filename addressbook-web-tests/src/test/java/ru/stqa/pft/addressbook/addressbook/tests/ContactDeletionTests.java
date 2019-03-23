package ru.stqa.pft.addressbook.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Max").withLastname("Bolshakov")
                    .withMobilePhone("79214448476").withHomePhone("123").withWorkPhone("333111")
                    .withEmail("bolmaxim@gmail.com")
                    .withAddress("Spb"));
        }
    }
    @Test
    public void testContactCreation() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();

        assertEquals(after.size(),before.size()-1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
