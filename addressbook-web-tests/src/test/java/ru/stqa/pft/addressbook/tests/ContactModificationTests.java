package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Max").withLastname("Bolshakov").withMobilePhone("79214448476")
                    .withHomePhone("123").withWorkPhone("333111").withEmail("bolmaxim@gmail.com")
                    .withEmail1("123").withEmail2("321").withAddress("Spb"));
        }
    }

    @Test
    public void testContactEdit() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/frog.png");
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Max").withLastname("Bolshakov").withMobilePhone("79214448476")
                .withHomePhone("123").withWorkPhone("333111").withEmail("bolmaxim@gmail.com")
                .withEmail1("123").withEmail2("321").withAddress("Spb")
                //       .withGroup("test1")
                .withPhoto(photo);
        app.contact().modify(modifiedContact.withPhoto(photo));
        app.contact().modify(contact.withPhoto(photo));

        Contacts after = app.db().contacts();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();


    }


}
