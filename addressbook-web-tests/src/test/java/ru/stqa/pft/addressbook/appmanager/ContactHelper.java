package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

    public void editContactUser(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }
    public void initUserCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void createContact(ContactData contact) {
        initUserCreation();
        fillContactForm(contact,true);
        submitUserCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath("//td[3]")).getText();
            String lastname = element.findElement(By.xpath("//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstname,lastname,null,null,null);
            contacts.add(contact);
        }
        return contacts;
    }
}

