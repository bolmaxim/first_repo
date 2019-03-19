package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
       try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
           String json = "";
           String line = reader.readLine();
           while (line != null) {
               json += line;
               line = reader.readLine();
           }
           Gson gson = new Gson();
           List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); // list ContactData.class
           return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
       }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test (dataProvider = "validContactsFromXml")
    public void testContactCreation(ContactData contact) {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/frog.png");
        app.contact().create(contact.withPhoto(photo).inGroup(groups.iterator().next()));
        Contacts after = app.db().contacts();

        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/frog.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
        System.out.println(photo.canWrite());
    }

    @Test (enabled = false)
    public void testContactCreation2() {

        Contacts before = app.db().contacts();
        ContactData contact = new ContactData().withFirstname("Max").withLastname("Bolshakov")
                .withMobilePhone("+79214448476").withHomePhone("444-84-76").withWorkPhone("(812)4448476")
                .withEmail("bolmaxim@gmail.com")
            //    .withGroups("test1")
                .withAddress("Saint Petersburg");
        app.contact().create(contact);
        Contacts after = app.db().contacts();

        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


}
