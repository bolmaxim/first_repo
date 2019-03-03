package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Max").withLastname("Bolshakov").withMobile("79214448476").withEmail("bolmaxim@gmail.com").withGroup("test1"));
        }
    }
    @Test
    public void testContactEdit() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Max").withLastname("Bolshakov").withMobile("79214448476").withEmail("bolmaxim@gmail.com").withGroup("test1");
        app.contact().modify(modifiedContact);
        app.contact().modify(contact);
        Contacts after = app.contact().all();

        assertEquals(after.size(),before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));


    }



}
