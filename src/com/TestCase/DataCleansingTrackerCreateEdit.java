package com.TestCase;

import com.sun.xml.internal.ws.api.server.WSEndpoint;
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

public class DataCleansingTrackerCreateEdit {
    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public DataCleansingTrackerCreateEdit() throws IOException {
    }


    @BeforeTest
    public void Setup(){
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
        String sObject = sheet.getRow(23).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
    @Test(priority = 1)
    public void DataCleansingTrackerCreate() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Click On New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='New']")));
        myDynamicElement.click();

        Thread.sleep(2000);
        //Associated Organization
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys("Extron Organization");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@title=\"Extron Organization\"]")).click();

        //Associated portfolio
        driver.findElement(By.xpath("//input[@title=\"Search Portfolios\"]")).sendKeys("Extron Portfolio");
        Thread.sleep(200);
        driver.findElement(By.xpath("//div[@title=\"Extron Portfolio\"]")).click();

        Thread.sleep(2000);
        //Associated Program
        driver.findElement(By.xpath("//input[@title=\"Search Programs\"]")).sendKeys("Extron Program");
        driver.findElement(By.xpath("//div[@title=\"Extron Program\"]")).click();

        //Associated Project
        driver.findElement(By.xpath("//input[@title=\"Search Projects\"]")).sendKeys("Extron Project");
        driver.findElement(By.xpath("//div[@title=\"Extron Project\"]")).click();

        //Data Cleasing Tracker Name
        driver.findElement(By.xpath("//input[@class=\" input\"][1]")).sendKeys("Test Selenium Data Cleansing Tracker");
        Thread.sleep(2000);
        //Data Cleansing Tracker Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"][1]")).sendKeys("Test Data Cleansing Tracker Description");

        //Data Cleansing Type
        driver.findElement(By.xpath("//a[@class=\"select\"]")).click();
        driver.findElement(By.xpath("//a[@title=\"Aged open items\"]")).click();

        //Data Sets Impacted
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(2000);

        //StakeHolder Group
        driver.findElement(By.xpath("//input[@title=\"Search Stakeholder Groups\"]")).sendKeys("Extron Stakeholder Group");
        driver.findElement(By.xpath("//div[@title=\"Extron Stakeholder Group\"]")).click();

        Thread.sleep(2000);
        //Associated Deliverable
        driver.findElement(By.xpath("//input[@title=\"Search Deliverables\"]")).sendKeys("Extron Deliverable");
        driver.findElement(By.xpath("//div[@title=\"Extron Deliverable Retest\"]")).click();

        //Associated System
        driver.findElement(By.xpath("//input[@title=\"Search Systems\"]")).sendKeys("Extron System");
        driver.findElement(By.xpath("//div[@title=\"Extron System 1\"]")).click();


        //Cleansing Start Date
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[1]")).click();
        driver.findElement(By.xpath("//span[text()='20']")).click();

        //Estimated Finish Data
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[2]")).click();
        driver.findElement(By.xpath("//a[@title=\"Go to next month\"]")).click();
        driver.findElement(By.xpath("//span[text()='10'][1]")).click();

        //Estimated Effort
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("6");

        Thread.sleep(2000);

        //Cleansing Procedure
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");

        //Save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
       Thread.sleep(2000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Data Cleansing Tracker \"Test Selenium Data Cleansing Tracker\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

    }

   /* @Test(priority = 2)
    public void DataCleansingTrackerEdit() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Dropdown to edit
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class=\"slds-button slds-button_icon-border-filled\"]")));
        myDynamicElement.click();

        //edit option in Dropdown
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name=\"PlatinumPMO__Data_Cleansing_Tracker__c.PlatinumPMO__EDIT\"]")));
        myDynamicElement.click();

        //Edit popup
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Edit']")));
        myDynamicElement.click();

        //Data Cleansing Tracker Name Edit
        driver.findElement(By.xpath("//input[@class=\" input\"]")).clear();
        driver.findElement(By.xpath("//input[@class=\" input\"]")).sendKeys("Data Cleansing Tracker-Edit");

        Thread.sleep(2000);
        //Estimated Effort
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).clear();
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("8");

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment-Edit");

        //Save
       driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Data Cleansing Tracker \"Data Cleansing Tracker-Edit\" was saved.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

    }*/
     @Test(priority = 3)
    public void AddRaciChart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Add Raci Chart
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Data_Cleansing_Tracker__c.PlatinumPMO__RACI_Chart\"]")));
        myDynamicElement.click();

        //User Type
        myDynamicElement= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class=\"slds-select\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()='Responsible']")));
        myDynamicElement.click();

        //User
        driver.findElement(By.xpath("//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"]")).sendKeys("Techcloud Developer\n");
        Thread.sleep(5000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Techcloud Developer']")));
        myDynamicElement.click();

        Thread.sleep(2000);
        //Change Description
        driver.findElement(By.xpath("//textarea[@name=\"HistoricalComment\"]")).sendKeys("Test Historical Comment");

        //Save
        driver.findElement(By.xpath("//button[text()='Save']")).click();

        Thread.sleep(4000);


        //Add Raci Chart
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Data_Cleansing_Tracker__c.PlatinumPMO__RACI_Chart\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //User Type
        myDynamicElement= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class=\"slds-select\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()='Accountable']")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //User
        driver.findElement(By.xpath("//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"]")).sendKeys("Techcloud Developer\n");
        Thread.sleep(3000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Techcloud Developer']")));
        myDynamicElement.click();

        //Change Description
        driver.findElement(By.xpath("//textarea[@name=\"HistoricalComment\"]")).sendKeys("Test Historical Comment");

        //Save
        driver.findElement(By.xpath("//button[text()='Save']")).click();

        Thread.sleep(4000);

        //Add Raci Chart
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Data_Cleansing_Tracker__c.PlatinumPMO__RACI_Chart\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //User Type
        myDynamicElement= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class=\"slds-select\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()='Consulted']")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //User
        driver.findElement(By.xpath("//input[@class=\"slds-lookup__search-input slds-input inputSize input uiInput uiInputText uiInput--default uiInput--input\"]")).sendKeys("Subhajit Mishra\n");
        Thread.sleep(3000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Subhajit Mishra']")));
        myDynamicElement.click();

        //Chasnge Description
        driver.findElement(By.xpath("//textarea[@name=\"HistoricalComment\"]")).sendKeys("Test Historical Comment");

        //Save
        driver.findElement(By.xpath("//button[text()='Save']")).click();

        Thread.sleep(4000);

    }

     /*@Test(priority = 4)
     public void AddDataCleansingTrackerMatrics() throws InterruptedException {
         WebDriverWait wait = new WebDriverWait(driver, 30);
         //Add Data Cleansing Matric
         WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Data_Cleansing_Tracker__c.PlatinumPMO__Data_Cleansing_Tracker_Metrics\"]")));
         myDynamicElement.click();

         Thread.sleep(3000);

         //Data Cleansing Metric Name
         driver.findElement(By.xpath("//input[@class=\" input\"]")).sendKeys("Test Selenium Data Cleansing Metric");
         Thread.sleep(2000);

         //Data Set Description
         driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");

         //Number of Record To Cleanse
         driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("14");

         Thread.sleep(2000);

         //Minimal Acceptable Result
         driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("50");

         //Historical comment
         driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");

         //Save
         driver.findElement(By.xpath("(//span[text()='Save'])[3]")).click();



         }*/
  /* @AfterTest
    public void close(){
        //closing the chrome
        driver.quit();
    }*/




}
