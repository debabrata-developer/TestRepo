package com.sampleData;

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

public class Business_Transaction {
    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Business_Transaction() throws IOException {
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
        String sObject = sheet.getRow(50).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test
    public void CreateBusiness_Transaction() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Click On New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Business Transaction Name
        String BTName = sheet.getRow(50).getCell(3).getStringCellValue();
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class=\" input\"])[1]")));
        myDynamicElement.sendKeys(BTName);
        Thread.sleep(1000);

        //Associated System
        String SystemName = sheet.getRow(33).getCell(3).getStringCellValue();
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Systems\"]")));
        myDynamicElement.sendKeys(SystemName);
        Thread.sleep(5000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+SystemName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated Object
        String ObjName = sheet.getRow(31).getCell(3).getStringCellValue();
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Objects\"]")));
        myDynamicElement.sendKeys(ObjName);
        Thread.sleep(5000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+ObjName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated Stakeholder Group
        String StakeName = sheet.getRow(42).getCell(3).getStringCellValue();
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Stakeholder Groups\"]")));
        myDynamicElement.sendKeys(StakeName);
        Thread.sleep(5000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+StakeName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Transaction Code
        driver.findElement(By.xpath("(//input[@class=\" input\"])[2]")).sendKeys("01");
        Thread.sleep(1000);

        //Transaction Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Associated Program
        String ProgName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@class=\" input\"])[3]")).sendKeys(ProgName);
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Business Transaction Library \""+BTName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

    }
    @AfterTest
    public void terminateBrowser(){

        driver.close();
    }
}
