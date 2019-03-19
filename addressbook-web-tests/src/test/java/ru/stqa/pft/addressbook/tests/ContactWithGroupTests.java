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
import java.util.SubList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactWithGroupTests extends TestBase {

    private SubList<Object> contactWithGroup;

    @BeforeMethod
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
        //         if (contact.getGroups().toString() != "[]") {
        //              System.out.println(contact.getGroups());
        //              continue;
        //           }
        //              System.out.println(contact);
        //              System.out.println(contact.getGroups());
        for ( ContactData contact : contacts )
            if (contact.getGroups().size() > 0) {
                contactWithGroup.add(contact);
            }
        if (contactWithGroup.size() == 0) {
            app.contact().initGroupAssign(contacts.iterator().next().getId(), groups.iterator().next());
        }

    }
        @Test
        public void testContactGroupAssign () {
            Contacts contacts = app.db().contacts();
            Groups groups = app.db().groups();
            app.contact().initGroupAssign(contacts.iterator().next().getId(), groups.iterator().next());
        }


        @Test
        public void testContactGroupDeletion () {
            Contacts contacts = app.db().contacts();
            Groups groups = app.db().groups();
 //          Contacts getContact = app.db().getContactInfo();
    //        System.out.println(getContact);
            for ( ContactData contact : contacts ) {
    //            if (contact.getGroups().toString() != "[]") {
    //                System.out.println(contact.getGroups());
    //            }

                if (contact.getGroups().size() == 0) {
                    System.out.println(contact.getGroups());
                }
  //              System.out.println(contact);
  //              System.out.println(contact.getGroups());
            }
 //           for ( ContactData contact : contacts ) {
  //              System.out.println(contact);
 ////               System.out.println(contact.getGroups());
 //           }
  //          app.contact().initGroupDeletion(contacts.iterator().next(),groups.iterator().next());
    //        for (ContactData contact : contacts) {
    //            for (GroupData group : groups)
    //        }
      //      (contact.getGroups().size() != 0) {
       //         System.out.println(contact);
       //         System.out.println(contact.getGroups());
        //    }
            //     System.out.println(contactData.inGroup(contactData.getGroups().iterator().next()));
            //         ContactData modifiedContact = contacts.iterator().next();
            //         app.contact().initGroupDeletion(modifiedContact.getId(),contactData.getGroups().iterator().next().toString());
            //        System.out.println(groups.iterator().next().getName());
            //     System.out.println(contactData.inGroup(groups.iterator().next()).toString());

        }

    }
