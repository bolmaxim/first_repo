package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ContactWithGroupTests extends TestBase {

    @BeforeMethod (enabled = true)
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Max").withLastname("Bolshakov").withMobilePhone("79214448476")
                    .withHomePhone("123").withWorkPhone("333111").withEmail("bolmaxim@gmail.com")
                    .withEmail1("123").withEmail2("321").withAddress("Spb"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ArrayList<ContactData> contactWithGroup = new ArrayList<>();

        for (ContactData contact : contacts) {
            if (contact.getGroups().toString() != "[]") {
                contactWithGroup.add(contact);
            }
        }
        if (contactWithGroup.size() == 0) {
            app.contact().returnToHomePage();
            app.contact().initGroupAssign(contacts.iterator().next().getId(), groups.iterator().next());
        }

        ArrayList<GroupData> groupWithContact = new ArrayList<>();

        for (GroupData group : groups) {
            if (group.getContacts().toString() != "[]") {
                groupWithContact.add(group);
            }
        }
        if (groupWithContact.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test9"));
        }

    }

    @Test
    public void testContactGroupAssign() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().toString() == "[]") {
                app.contact().initGroupAssign(contacts.iterator().next().getId(), groups.iterator().next());
            }
        }
   //     assertThat(contacts.iterator().next(),equalTo());
    }


    @Test
    public void testContactGroupDeletion() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ArrayList<ContactData> ContactWithGroupBefore = new ArrayList<>();
        ArrayList<ContactData> ContactWithGroupAfter = new ArrayList<>();

        for (ContactData contact : contacts) {
            if (contact.getGroups().toString() != "[]") {
                ContactWithGroupBefore.add(contact);
            }
        }

        for (ContactData contact : contacts) {
            if (contact.getGroups().toString() != "[]") {
                ContactWithGroupAfter.add(contact);
                app.contact().initGroupDeletion(contacts.iterator().next(), contact.getGroups().iterator().next());
            }
        }
   //lk    assertThat(ContactWithGroupBefore,);
    }

    @Test
    public void testContactWithGroups() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        System.out.println(groups.iterator().next().getContacts().size());
        System.out.println(contacts.iterator().next().getGroups().size());

   //     for (ContactData contact : contacts) {
  //          if (contact.getGroups().toString() != "[]") {
   //             System.out.println(contact.getGroups().iterator().next());
   //             app.contact().initGroupDeletion(contacts.iterator().next(), contact.getGroups().iterator().next());
   //         }
  //      }
    }

}
