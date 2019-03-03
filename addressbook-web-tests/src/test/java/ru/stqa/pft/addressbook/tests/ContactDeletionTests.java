package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Max").withLastname("Bolshakov").withMobilePhone("79214448476").withHomePhone("123").withWorkPhone("333111").withEmail("bolmaxim@gmail.com").withGroup("test1").withAddress("Spb"));
        }
    }
    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();

        assertEquals(after.size(),before.size()-1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
