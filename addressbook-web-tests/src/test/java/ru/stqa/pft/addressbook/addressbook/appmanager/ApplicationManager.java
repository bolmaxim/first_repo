package ru.stqa.pft.addressbook.addressbook.appmanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties propeties;
    public WebDriver wd;

    private SessionHelper sessionHelper;
    private  NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private String browser;
    private DbHelper dbHelper;

    public ApplicationManager(String browser)  {
        propeties = new Properties();
        this.browser = browser;

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        propeties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

        dbHelper = new DbHelper();
        if ("".equals(propeties.getProperty("selenium.server"))) {

            if (browser.equals(BrowserType.SAFARI)) {
                wd = new SafariDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            }
       } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(propeties.getProperty("selenium.server")), capabilities);
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(propeties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(propeties.getProperty("web.adminLogin"), propeties.getProperty("web.adminPassword"));
    }



    public void stop() {
        wd.quit();
    }



    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public DbHelper db() { return dbHelper;}
}
