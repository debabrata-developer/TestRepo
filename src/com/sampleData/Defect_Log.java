package com.sampleData;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Defect_Log {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Defect_Log() throws IOException {
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
        Thread.sleep(5000);

        //Associated Organization
        String OrgName = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@title=\""+OrgName+"\"]")).click();
        Thread.sleep(1000);

        //Associated portfolio
        String PortName = sheet.getRow(12).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Portfolios\"]")).sendKeys(PortName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+PortName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated Program
        String ProgName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Programs\"]")).sendKeys(ProgName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+ProgName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated Project
        String ProjName = sheet.getRow(14).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Projects\"]")).sendKeys(ProjName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+ProjName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Defect Name
        String DefectName = sheet.getRow(25).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@class=\" input\"])[1]")).sendKeys(DefectName);
        Thread.sleep(1000);

        //Defect Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing Selenium Defect");
        Thread.sleep(1000);

        //Type Of Defect
        driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Architecture Defect\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Project Phase
        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"ASAP: 1- Preparation\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Priority
        driver.findElement(By.xpath("(//a[@class=\"select\"])[3]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Normal\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Severity
        driver.findElement(By.xpath("(//a[@class=\"select\"])[4]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"S1- Show Stopper\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated Deliverables
        String DelijName = sheet.getRow(15).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Deliverables\"]")).sendKeys(DelijName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+DelijName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associates System
        String SystemName = sheet.getRow(33).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Systems\"]")).sendKeys(SystemName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+SystemName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Business Transaction Impacted
        String BTName = sheet.getRow(50).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Business Transaction Library\"]")).sendKeys(BTName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+BTName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Integration Impacted
        driver.findElement(By.xpath("(//a[@class=\"select\"])[5]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"1- No Integration Impact\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Defect Co-ordinator
        String userName = sheet.getRow(37).getCell(4).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[1]")));
        myDynamicElement.sendKeys(userName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Defect Owner
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[2]")));
        myDynamicElement.sendKeys(userName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Defect Resolution Signoff
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[3]")));
        myDynamicElement.sendKeys(userName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //What Cause The Defect
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Proposed Resolution
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Associated Test Plan
        //driver.findElement(By.xpath("//input[@title=\"Search Test Plans\"]")).sendKeys("Extron  RJC Test Plan 1");
        //myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron RJC Test Plan 1\"]")));
        //myDynamicElement.click();
        //Test Plan Details
        //driver.findElement(By.xpath("title=\"Search Test Plan Details\"")).sendKeys();

        //Example Url
        driver.findElement(By.xpath("//input[@type=\"url\"]")).sendKeys("www.google.com");
        Thread.sleep(1000);

        //Estimated Effort
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("8");
        Thread.sleep(1000);

        //Estimated Resolution Date
        driver.findElement(By.xpath("//a[@class=\"datePicker-openIcon display\"]")).click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='31']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Defect Log \""+DefectName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

    }

    @AfterTest
    public void close() {
        //closing the chrome
        driver.quit();
    }
}
