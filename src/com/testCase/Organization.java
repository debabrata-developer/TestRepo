package com.testCase;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Organization {
    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Organization() throws IOException {
    }

    @BeforeTest
    public void Setup() {

        //get WebDriver Path
        String webDriverPath = sheet.getRow(3).getCell(2).getStringCellValue();
        System.out.println(webDriverPath);

        //get UserName & Password
        String username = sheet.getRow(1).getCell(2).getStringCellValue();
        System.out.println(username);
        String password = sheet.getRow(2).getCell(2).getStringCellValue();
        System.out.println(password);

        //Open Chrome & go to Salesforce login page
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        //all other arguments(if need then add)
        //options.addArguments("--always-authorize-plugins"); options.addArguments("--no-sandbox"); options.addArguments("--disable-dev-shm-usage"); options.addArguments("--aggressive-cache-discard"); options.addArguments("--disable-cache"); options.addArguments("--disable-application-cache"); options.addArguments("--disable-offline-load-stale-cache"); options.addArguments("--disk-cache-size=0"); options.addArguments("--headless"); options.addArguments("--disable-gpu"); options.addArguments("--dns-prefetch-disable"); options.addArguments("--no-proxy-server"); options.addArguments("--log-level=3"); options.addArguments("--silent"); options.addArguments("--disable-browser-side-navigation"); options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com");

        //give UserName & Password & Click to Login
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.id("Login")).click();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

        //get sObject URL
        String sObject = sheet.getRow(11).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void CreateOrganization() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" and text()='New']")));
        myDynamicElement.click();

        //Organization Name
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@class=\" input\" and @type=\"text\"]")));
        myDynamicElement.sendKeys("Selenium Test Organization");

        //Timesheet Auto Approval Days
        driver.findElement(By.xpath("(//*[@data-aura-class=\"uiInputSmartNumber\"])[1]")).sendKeys("7");

        //Expense Auto Approval Days
        driver.findElement(By.xpath("(//*[@data-aura-class=\"uiInputSmartNumber\"])[2]")).sendKeys("9");

        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Selenium Test Historical Comment");

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\" ]//span[text()='Save']")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");
        System.out.println("the toast message value--->" + ToastMessage);

        //Expected Toast Message Value Set
        String ExpectedValue = "Organization \"Selenium Test Organization\" was created.";
        System.out.println("the ExpectedValue--->" + ExpectedValue);

        //Check
        Assert.assertEquals(ToastMessage, ExpectedValue);

        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void EditOrganization() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Click Edit Button
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"Edit\"]")));
        myDynamicElement.click();

        //Organization Name
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@class=\" input\" and @type=\"text\"]")));
        myDynamicElement.sendKeys(" Edit");

        //Timesheet Auto Approval Days
        driver.findElement(By.xpath("(//*[@data-aura-class=\"uiInputSmartNumber\"])[1]")).sendKeys("1");

        //Expense Auto Approval Days
        driver.findElement(By.xpath("(//*[@data-aura-class=\"uiInputSmartNumber\"])[2]")).sendKeys("9");

        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Selenium Test Historical Comment Edit");

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\" ]//span[text()='Save']")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");
        System.out.println("the toast message value--->" + ToastMessage);

        //Expected Toast Message Value Set
        String ExpectedValue = "Organization \"Selenium Test Organization Edit\" was saved.";
        System.out.println("the ExpectedValue--->" + ExpectedValue);

        //Check
        Assert.assertEquals(ToastMessage, ExpectedValue);

        Thread.sleep(5000);
    }

    @Test(priority = 3)
    public void InviteUser() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Add Invite User
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Organization__c.PlatinumPMO__Add_User\"]")));
        myDynamicElement.click();
        Thread.sleep(3000);

        //User
        String userName = sheet.getRow(37).getCell(4).getStringCellValue();
        driver.findElement(By.xpath("//input[@placeholder=\"search..\"]")).sendKeys(userName);
        Thread.sleep(3000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+userName+"']")));
        myDynamicElement.click();

        //Save
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();
        Thread.sleep(4000);

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The Record was Saved";

        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);

    }

    @Test(priority = 4)
    public void AddPortfolio() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Click Add Portfolio
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Organization__c.PlatinumPMO__Add_Portfolio2\"]")));
        myDynamicElement.click();
        Thread.sleep(4000);

        //Portfolio Name
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"Portfolio Name\"]")));
        myDynamicElement.sendKeys(" New Portfolio");

        //Portfolio Owner
        String userName = sheet.getRow(37).getCell(4).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\" and @placeholder=\"search..\"])[2]")));
        myDynamicElement.sendKeys(userName);
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+userName+"']")));
        myDynamicElement.click();

        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Selenium Test Portfolio");

        //Click Save Button
        driver.findElement(By.xpath("//button[@type=\"button\" and text()='Save']")).click();

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The record was saved.";

        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);
    }
    @AfterTest
    //Closing the Chrome
    public void terminateBrowser(){
        driver.close();
    }

}
