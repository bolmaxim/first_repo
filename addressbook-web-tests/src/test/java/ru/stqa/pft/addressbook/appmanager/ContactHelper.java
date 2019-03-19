package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void submitUserCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void submitUserUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail1());
        type(By.name("email3"), contactData.getEmail2());
        type(By.name("address"), contactData.getAddress());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
      //          Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            } else {
  //              Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
        }
    }

        public void deleteContactUser () {
            click(By.xpath("//div[@id='content']/form[2]/input[2]"));
            wd.findElement(By.linkText("home")).click();
        }

        public void editById ( int id){
            wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
        }
        public void initUserCreation () {
            wd.findElement(By.linkText("add new")).click();
        }

        public void initGroupAssign (int id, GroupData group) {
        wd.findElement(By.id(String.valueOf(id))).click();
            wd.findElement(By.name("to_group")).click();
            new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
            wd.findElement(By.name("add")).click();
        }

        public void initGroupDeletion(ContactData contact, GroupData group) {
        wd.findElement(By.name("group")).click();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
        wd.findElement(By.id(+ contact.getId() + "")).click();
        wd.findElement(By.name("remove")).click();
         }

    private void selectGroupForAdding(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());

    }

    private void selectGroupForRemoval(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());

    }

        public void create (ContactData contact){
            initUserCreation();
            fillContactForm(contact, true);
            submitUserCreation();
            contactCache = null;
            returnToHomePage();
        }
        public void modify (ContactData contact){
            editById(contact.getId());
            fillContactForm(contact, false);
            submitUserUpdate();
            contactCache = null;
            returnToHomePage();
        }

        public void delete (ContactData contact){
            editById(contact.getId());
            deleteContactUser();
            contactCache = null;
        }

        public boolean isThereAContact () {
            return isElementPresent(By.xpath("//img[@alt='Edit']"));
        }

        private Contacts contactCache = null;

        public Contacts all () {
            if (contactCache != null) {
                return new Contacts(contactCache);
            }
            contactCache = new Contacts();
            List<WebElement> elements = wd.findElements(By.name("entry"));
            for (WebElement element : elements) {
                String firstname = element.findElement(By.xpath(".//td[3]")).getText();
                String lastname = element.findElement(By.xpath(".//td[2]")).getText();
                String allphones = element.findElement(By.xpath(".//td[6]")).getText();
                String address = element.findElement(By.xpath(".//td[4]")).getText();
                String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
                contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                        .withAllPhones(allphones).withAllEmails(allEmails).withAddress(address));
            }
            return new Contacts(contactCache);
        }


        public ContactData infoFromEditForm (ContactData contact){
            editById(contact.getId());
            String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
            String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
            String home = wd.findElement(By.name("home")).getAttribute("value");
            String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
            String work = wd.findElement(By.name("work")).getAttribute("value");
            String address = wd.findElement(By.name("address")).getAttribute("value");
            String email = wd.findElement(By.name("email")).getAttribute("value");
            String email1 = wd.findElement(By.name("email2")).getAttribute("value");
            String email2 = wd.findElement(By.name("email3")).getAttribute("value");
            wd.navigate().back();
            return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                    .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail1(email1)
                    .withEmail2(email2).withAddress(address);
        }
    }

