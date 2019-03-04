package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Max").withLastname("Bolshakov")
                .withMobilePhone("79214448476").withHomePhone("123").withWorkPhone("333111")
                .withEmail("bolmaxim@gmail.com").withEmail1("bolmaxim@me.com").withEmail2("bolmaxim@mail.ru")
                .withGroup("test1").withAddress("Spb");
        app.contact().create(contact);
        Contacts after = app.contact().all();

        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testContactCreation2() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Max").withLastname("Bolshakov")
                .withMobilePhone("+79214448476").withHomePhone("444-84-76").withWorkPhone("(812)4448476")
                .withEmail("bolmaxim@gmail.com").withGroup("test1").withAddress("Saint Petersburg");
        app.contact().create(contact);
        Contacts after = app.contact().all();

        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
