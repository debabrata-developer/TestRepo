package com.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class OrganizationCreateEdit {

    public WebDriver driver;

    @BeforeTest
    public void setup() {
        String userName = "techcloud@platinumpmo.com";
        String password = "tech@1234567$";
        String webDriverPath = "E:\\automation testing\\Jre\\Automation\\chromedriver_win32_80_0_3987_106\\chromedriver.exe";
        //opening chrome
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        ChromeOptions opt = new ChromeOptions();
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("--disable-notifications");
        opt.addArguments(l1);
        driver = new ChromeDriver(opt);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com");

        //read excel

        //login to salesforce
        WebElement userNameElement = driver.findElement(By.id("username"));
        WebElement passwordElement = driver.findElement(By.id("password"));
        userNameElement.sendKeys(userName);
        passwordElement.sendKeys(password);
        driver.findElement(By.id("Login")).click();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

        //Redirect to sObject
    }

    @Test(priority = 1)
    public void clickOnApplauncher(){

        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/header/div[3]/one-appnav/div/div/div/nav/one-app-launcher-header/button/div")));
        myDynamicElement.click();
        myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div[4]/div[2]/div/div[1]/div[1]/one-app-launcher-menu/div/lightning-button/button")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/one-app-launcher-modal/div/div[1]/div/div/one-app-launcher-search-bar/lightning-input/div/input")));
        myDynamicElement.sendKeys("Org");

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/one-app-launcher-modal/div/div[2]/lightning-accordion/slot/lightning-accordion-section[2]/section/div[2]/slot/ul/li[2]/one-app-launcher-tab-item/a/span/lightning-formatted-rich-text/span/p")));
        myDynamicElement.click();

    }

    @Test(priority = 2)
    public void CreateOrganization(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/ul/li[1]/a/div")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[1]/div[1]/div/div/div/input")));
        myDynamicElement.sendKeys("Selenium Org Test");


        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div/div[1]/div/div/div/input")).sendKeys("7");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div/div[2]/div/div/div/input")).sendKeys("9");


        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[3]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("Test Historical Comment");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/button[3]/span")).click();

    }
    @Test(priority = 3)
    public void EditOrganization() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[3]/div/div[1]/div/div[1]/div/header/div[2]/div/div[2]/ul/li[3]/a/div")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/article/div[3]/div/div[1]/div/div/div[1]/div[1]/div/div/div/input")));
        myDynamicElement.clear();
        myDynamicElement.sendKeys("Test Selenium Organization");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/article/div[3]/div/div[4]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("test");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[3]/div/button[3]/span")).click();
        Thread.sleep(5000);
    }

    @AfterTest
    public void terminateBrowser(){
        driver.close();
    }



}

