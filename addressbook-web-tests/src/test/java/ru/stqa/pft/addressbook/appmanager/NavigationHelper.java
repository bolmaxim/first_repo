package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() { wd.findElement(By.linkText("groups")).click(); }
    public void initUserCreation() { wd.findElement(By.linkText("add new")).click(); }


}
