package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import static org.testng.Assert.*;


public class ContactDeletionTests {
    private ChromeDriver wd;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        wd.get("http://localhost/addressbook/index.php");
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.id("LoginForm")).submit();
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
        wd.findElement(By.xpath("//div[@id='content']/form[2]/input[2]")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }

}
