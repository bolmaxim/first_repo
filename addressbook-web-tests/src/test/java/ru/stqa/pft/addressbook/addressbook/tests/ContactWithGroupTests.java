package ru.stqa.pft.addressbook.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ContactWithGroupTests extends TestBase {

    @BeforeTest(enabled = false)
    public void ensurePreconditionsForAdding() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Max").withLastname("Bolshakov").withMobilePhone("79214448476")
                    .withHomePhone("123").withWorkPhone("333111").withEmail("bolmaxim@gmail.com")
                    .withEmail1("123").withEmail2("321").withAddress("Spb"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        if (app.db().contacts().iterator().next().getGroups().size() == app.db().groups().size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
/*
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
*/
    }

    @Test(enabled = false)
    public void testContactGroupAssign() {
             Contacts contactsBefore = app.db().contacts();
        Groups groupList = app.db().groups();
        ContactData selectedContact = contactsBefore.iterator().next();
        GroupData toGroup = groupList.iterator().next();
        app.contact().initGroupAssign(selectedContact.getId(), toGroup);
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter.iterator().next().getGroups().withAdded(groupList.iterator().next()), equalTo(contactsBefore.iterator().next().getGroups()));
        verifyContactListInUI();

    }

    @BeforeTest (enabled = false)
    public void ensurePreconditionsForDeletion() {

        if (app.db().groups().iterator().next().getContacts().size() == 0) {
            Groups groupList = app.db().groups();
            GroupData toGroup = groupList.iterator().next();
            Contacts contactsBefore = app.db().contacts();
            ContactData selectedContact = contactsBefore.iterator().next();
            app.contact().initGroupAssign(selectedContact.getId(), toGroup);
        }

    }


    @Test(enabled = false)
    public void testContactGroupDeletion() {
        app.contact().returnToHomePage();
        Contacts contactsBefore = app.db().contacts();
        Groups groupList = app.db().groups();
        ContactData selectedContact = contactsBefore.iterator().next();
        GroupData fromGroup = groupList.iterator().next();
        app.contact().initGroupDeletion(selectedContact, fromGroup);
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter.iterator().next().getGroups().without(groupList.iterator().next()), equalTo(contactsBefore.iterator().next().getGroups()));
        app.contact().returnToHomePage();
        verifyContactListInUI();
    }

    @Test(enabled = false)
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
