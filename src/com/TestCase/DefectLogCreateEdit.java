package com.TestCase;

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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DefectLogCreateEdit {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public DefectLogCreateEdit() throws IOException {
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
        String sObject = sheet.getRow(25).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
   @Test(priority=1)
    public void CreateDefectLog() throws InterruptedException {
       WebDriverWait wait = new WebDriverWait(driver, 30);
       //Click On New Button
       WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"New\"]")));
       myDynamicElement.click();
       Thread.sleep(2000);
      //Associated Organization
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Organization\"]")));
       myDynamicElement.sendKeys("Extron Organization");
       Thread.sleep(2000);
       driver.findElement(By.xpath("//div[@title=\"Extron Organization\"]")).click();

       //Associated portfolio
       driver.findElement(By.xpath("//input[@title=\"Search Portfolios\"]")).sendKeys("Extron Portfolio");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Portfolio\"]")));
       myDynamicElement.click();
       Thread.sleep(2000);

       //Associated Program
       driver.findElement(By.xpath("//input[@title=\"Search Programs\"]")).sendKeys("Extron Portfolio");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Program\"]")));
       myDynamicElement.click();
       //Associated Project
       driver.findElement(By.xpath("//input[@title=\"Search Projects\"]")).sendKeys("Extron Project");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Project\"]")));
       myDynamicElement.click();
       //Defect Name
       driver.findElement(By.xpath("(//input[@class=\" input\"])[1]")).sendKeys("Test Selenium Defect");

       //Defect Description
       driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing Selenium Defect");

       Thread.sleep(2000);
       //Type Of Defect
       driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).click();
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Architecture Defect\"]")));
       myDynamicElement.click();

       //Project Phase
       driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"ASAP: 1- Preparation\"]")));
       myDynamicElement.click();

       //Priority
       driver.findElement(By.xpath("(//a[@class=\"select\"])[3]")).click();
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Normal\"]")));
       myDynamicElement.click();

       //Severity
       driver.findElement(By.xpath("(//a[@class=\"select\"])[4]")).click();
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"S1- Show Stopper\"]")));
       myDynamicElement.click();
       Thread.sleep(2000);

       //Associated Deliverables
       driver.findElement(By.xpath("//input[@title=\"Search Deliverables\"]")).sendKeys("Extron Deliverable");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Deliverable Retest\"]")));
       myDynamicElement.click();
       Thread.sleep(2000);

       //Associates System
       driver.findElement(By.xpath("//input[@title=\"Search Systems\"]")).sendKeys("Extron System ");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron System\"]")));
       myDynamicElement.click();

       //Business Transaction Impacted
        driver.findElement(By.xpath("//input[@title=\"Search Business Transaction Library\"]")).sendKeys("Extron Business Transaction");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Business Transaction\"]")));
       myDynamicElement.click();
        //Integration Impacted
       driver.findElement(By.xpath("(//a[@class=\"select\"])[5]")).click();
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"1- No Integration Impact\"]")));
       myDynamicElement.click();

       //Defect Co-ordinator
       driver.findElement(By.xpath("(//input[@title=\"Search People\"])[1]")).sendKeys("Techcloud Developer");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Techcloud Developer\"])[1]")));
       myDynamicElement.click();
       //Defect Owner
       driver.findElement(By.xpath("(//input[@title=\"Search People\"])[2]")).sendKeys("Techcloud Developer");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Techcloud Developer\"])[2]")));
       myDynamicElement.click();
        Thread.sleep(2000);
       //Defect Resolution Signoff
       driver.findElement(By.xpath("(//input[@title=\"Search People\"])[3]")).sendKeys("Techcloud Developer");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Techcloud Developer\"])[3]")));
       myDynamicElement.click();

       //What Cause The Defect
       driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");

       //Proposed Resolution
       driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");

       //Associated Test Plan
       driver.findElement(By.xpath("//input[@title=\"Search Test Plans\"]")).sendKeys("Extron  RJC Test Plan 1");
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron RJC Test Plan 1\"]")));
       myDynamicElement.click();
       //Test Plan Details
       //driver.findElement(By.xpath("title=\"Search Test Plan Details\"")).sendKeys();

       //Example Url
       driver.findElement(By.xpath("//input[@type=\"url\"]")).sendKeys("www.google.com");

       //Estimated Effort
       driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("8");

       //Estimated Resolution Date
       driver.findElement(By.xpath("//a[@class=\"datePicker-openIcon display\"]")).click();
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='31']")));
       myDynamicElement.click();

       //Historical Comment
       driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");
      //Save
       driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();

       Thread.sleep(2000);

       //get Toast Message
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
       String ToastMessage = myDynamicElement.getAttribute("innerHTML");

       //Expected Toast Message Value Set
       String ExpectedValue = "Defect Log \"Test Selenium Defect\" was created.";

       //Check
       Assert.assertEquals(ToastMessage,ExpectedValue);

    }

    @Test(priority=2)
    public void EditDefectLog() throws InterruptedException {
       Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Edit Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Defect_Log__c.PlatinumPMO__Edit\"]")));
        myDynamicElement.click();

        //Edit popup
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Edit']")));
        myDynamicElement.click();

        //Defect Name
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class=\" input\"])[1]")));
        myDynamicElement.clear();
        myDynamicElement.sendKeys("Test Selenium Defect-Edit");

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment-Edit");
        //Save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Defect Log \"Test Selenium Defect-Edit\" was saved.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);
    }

    @AfterTest
    public void close() {
        //closing the chrome
        driver.quit();
    }
}
