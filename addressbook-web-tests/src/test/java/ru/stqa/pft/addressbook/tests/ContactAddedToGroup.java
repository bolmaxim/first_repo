package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ContactAddedToGroup extends TestBase {




        @BeforeTest(enabled = true)
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
        }

    @Test
    public void testContactGroupAssign() {
        Contacts contactsBefore = app.db().contacts();
        Groups groupList = app.db().groups();
        ContactData selectedContact = contactsBefore.iterator().next();
        GroupData toGroup = groupList.iterator().next();
        app.contact().initGroupAssign(selectedContact.getId(), toGroup);
        Contacts contactsAfter = app.db().contacts();
        assertThat(contactsAfter.iterator().next().getGroups(), equalTo(contactsBefore.iterator().next().getGroups().withAdded(toGroup)));
        verifyContactListInUI();
        app.contact().returnToHomePage();

    }
    }

