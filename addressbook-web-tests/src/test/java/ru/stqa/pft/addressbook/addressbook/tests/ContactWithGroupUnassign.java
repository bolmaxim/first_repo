package ru.stqa.pft.addressbook.addressbook.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactWithGroupUnassign extends TestBase {


    @BeforeTest
    public void ensurePreconditionsForDeletion() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().returnToHomePage();
            app.contact().create(new ContactData().withFirstname("Max").withLastname("Bolshakov").withMobilePhone("79214448476")
                    .withHomePhone("123").withWorkPhone("333111").withEmail("bolmaxim@gmail.com")
                    .withEmail1("123").withEmail2("321").withAddress("Spb"));
        }

        if (app.db().groups().iterator().next().getContacts().size() == 0) {
            Groups groupList = app.db().groups();
            GroupData toGroup = groupList.iterator().next();
            Contacts contactsBefore = app.db().contacts();
            ContactData selectedContact = contactsBefore.iterator().next();
            app.contact().initGroupAssign(selectedContact.getId(), toGroup);
        }

    }

    @Test
    public void testContactGroupDeletion() {
        app.contact().returnToHomePage();
        Contacts contactsBefore = app.db().contacts();
        Groups groupList = app.db().groups();
        ContactData selectedContact = contactsBefore.iterator().next();
        GroupData fromGroup = groupList.iterator().next();
        app.contact().initGroupDeletion(selectedContact, fromGroup);
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter.iterator().next().getGroups(), equalTo(contactsBefore.iterator().next().getGroups().without(fromGroup)));
        app.contact().returnToHomePage();
        verifyContactListInUI();
    }
}
