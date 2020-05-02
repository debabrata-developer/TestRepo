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

public class DeliverableCreateNew {
    public WebDriver driver;
    public static final String userName = "techcloud@platinumpmo.com";
    public static final String password = "tech@1234567$";
    @BeforeTest
    public void openBrowserOnChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rupak\\Downloads\\testing salenium\\chromedriver_win32_80_0_3987_106-20200427T071327Z-001\\chromedriver_win32_80_0_3987_106\\chromedriver.exe");
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
    }
    @Test(priority = 0)
    public void loginIntoSalesforce(){
        WebElement userNameElement = driver.findElement(By.id("username"));
        WebElement passwordElement = driver.findElement(By.id("password"));
        userNameElement.sendKeys(userName);
        passwordElement.sendKeys(password);
        driver.findElement(By.id("Login")).click();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

    }
    @Test(priority = 1)
    public void clickOnApplauncher(){

        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/header/div[3]/one-appnav/div/div/div/nav/one-app-launcher-header/button/div")));
        myDynamicElement.click();
        myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/div[4]/div[2]/div/div[1]/div[1]/one-app-launcher-menu/div/lightning-button/button")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/one-app-launcher-modal/div/div[1]/div/div/one-app-launcher-search-bar/lightning-input/div/input")));
        myDynamicElement.sendKeys("Del");

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/one-app-launcher-modal/div/div[2]/lightning-accordion/slot/lightning-accordion-section[2]/section/div[2]/slot/ul/li[2]/one-app-launcher-tab-item/a/span/lightning-formatted-rich-text/span/p")));
        myDynamicElement.click();

    }
    @Test(priority = 2)
    public void CreateDeliverable() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/ul/li[1]/a")));
        myDynamicElement.click();
        //
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[1]/div[1]/div/div/div/div/div/div[1]/div/input")));

        myDynamicElement.sendKeys(" Extron Organization");
        driver.findElement(By.xpath("//div[@title=\"Extron Organization\"]")).click();

        driver.findElement(By.xpath("//div/Input[@title=\"Search Portfolios\"]")).sendKeys("Extron Portfolio");
        driver.findElement(By.xpath("//div[@title=\"Extron Portfolio\"]")).click();


        driver.findElement(By.xpath("//div/input [@role= \"combobox\" and @title =\"Search Programs\"]")).sendKeys("Extron Program");
        driver.findElement(By.xpath("//div[@title=\"Extron Program\"]")).click();


        driver.findElement(By.xpath("//div/input[@role=\"combobox\" and @title=\"Search Projects\"]")).sendKeys("Extron Project");
        driver.findElement(By.xpath("//div[@title=\"Extron Project\"]")).click();


       // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[3]/div[1]/div/div/lightning-picklist/lightning-dual-listbox/div/div[2]/div/div[3]/div/ul/li[1]/div/span/span")).click();
       // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[3]/div[1]/div/div/lightning-picklist/lightning-dual-listbox/div/div[2]/div/div[4]/lightning-button-icon[1]/button")).click();






        driver.findElement(By.xpath("//div/input[@type=\"text\" and @maxlength=\"80\"]")).sendKeys("New Deliverable 2");

       // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[3]/div[1]/div/div/lightning-picklist/lightning-dual-listbox/div/div[2]/div/div[3]/div/ul/li[1]/div/span/span")).sendKeys(" Project");

        driver.findElement(By.xpath("//div/input[@title=\"Search DeliverableTypes\"or@data-interactive-lib-uid=12]")).sendKeys("Extron DT ");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[3]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[3]/a/div[2]/div[1]")).click();



        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[3]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("test");
        driver.findElement(By.xpath("//div//button[@title=\"Save\"]")).click();

    }
    @Test(priority = 3)
    public void EditDeliverable() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[3]/div/div[1]/div/div[1]/div[1]/header/div[2]/div/div[2]/ul/li[4]/div/div[1]/div/div/a/lightning-icon/lightning-primitive-icon")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[9]/div/ul/li[2]/a")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div/footer/button[1]")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/article/div[3]/div/div[2]/div/div/div[1]/div/div/div/div/input")));
        myDynamicElement.clear();

        myDynamicElement.sendKeys("New Del 2");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/article/div[3]/div/div[4]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("test");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[3]/div/button[3]/span")).click();
        Thread.sleep(5000);
    }


}

