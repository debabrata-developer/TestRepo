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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Cutover_Event_Journal {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Cutover_Event_Journal() throws IOException {
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
        System.setProperty("webdriver.chrome.driver",webDriverPath );
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
        String sObject = sheet.getRow(22).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
    @Test(priority = 1)
    public void CreateCEJ() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Click On New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);

        //Click On Associated Organization
        String OrgName = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@title=\""+OrgName+"\"]")).click();

        //Click On Associated Portfolio
        String PortName = sheet.getRow(12).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Portfolios\"]")).sendKeys(PortName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@title=\""+PortName+"\"]")).click();

        //Click On Associated Program
        String ProgName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Programs\"]")).sendKeys(ProgName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@title=\""+ProgName+"\"]")).click();

        //Cutover Event Journal Name
        driver.findElement(By.xpath("//input[@class=\" input\"]")).sendKeys("Test Selenium CEJ");

        //Priority
        driver.findElement(By.xpath("//a[@class=\"select\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title=\"Normal\"]")).click();

        //Event Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing Event");

        //Cutover Event Coordinator
        String userName = sheet.getRow(37).getCell(4).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[1]")).sendKeys(userName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@title=\""+userName+"\"])[1]")).click();
        Thread.sleep(2000);
        //Cutover Event Owner
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[2]")).sendKeys(userName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@title=\""+userName+"\"])[2]")).click();
        Thread.sleep(2000);
        //Cutover Event Resolution Signoff
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[3]")).sendKeys(userName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@title=\""+userName+"\"])[3]")).click();

        //Cutover Resolution
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing Cutover Resolution");

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");

        //Save CEJ
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Cutover Event Journal \"Test Selenium CEJ\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

    }

    @Test(priority = 2)
    public void EditCEJ() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Edit Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Cutover_Event_Journal__c.PlatinumPMO__Edit\"]")));
        myDynamicElement.click();

        //Edit popup
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Edit']")));
        myDynamicElement.click();

        //CEJ Name
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class=\" input\"]")));
        myDynamicElement.clear();
        myDynamicElement.sendKeys("Test Selenium CEJ-Edit");

        //Prority
        driver.findElement(By.xpath("//a[@class=\"select\"]")).click();
        driver.findElement(By.xpath("//a[@title=\"Low\"]")).click();
        Thread.sleep(2000);
        //Cutover event Signoff
        // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[2]/div/div/div[5]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Debabrata Ghosh");
        // driver.findElement(By.xpath("//div[@title=\"Debabrata Ghosh\"]")).click();


        //Historical comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment-Edit");

        //Save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Cutover Event Journal \"Test Selenium CEJ-Edit\" was saved.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

    }
    @AfterTest
    public void close(){
        //closing the chrome
        driver.quit();
    }

}
