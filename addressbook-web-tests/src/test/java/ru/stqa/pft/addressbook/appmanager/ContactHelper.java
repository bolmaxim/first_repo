package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void fillContactForm(ContactData contactData,boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        if (isElementPresent(By.name("new_group"))) {
        }
    }

    public void deleteContactUser() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
        wd.findElement(By.linkText("home")).click();
    }

    public void editById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }
    public void initUserCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void create(ContactData contact) {
        initUserCreation();
        fillContactForm(contact,true);
        submitUserCreation();
        returnToHomePage();
    }
    public void modify(ContactData contact) {
        editById(contact.getId());
        fillContactForm(contact,false);
        submitUserUpdate();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        editById(contact.getId());
        deleteContactUser();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }


}

