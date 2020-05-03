package com.basic;

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
import java.io.IOException;
import java.security.Key;
import java.util.concurrent.TimeUnit;

public class Testing {
    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Testing() throws IOException {
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
        String sObject = sheet.getRow(12).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void CreatePortfolio() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" and text()='New']")));
        myDynamicElement.click();

        //Select Organization
        String OrgLookup = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@placeholder=\"Search Organization...\"]")));
        myDynamicElement.sendKeys(OrgLookup);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+OrgLookup+"\"]")));
        myDynamicElement.click();

        //Portfolio Name
        driver.findElement(By.xpath("//div/input[@class=\" input\" and @type=\"text\"]")).sendKeys("Selenium Test Portfolio");

        //Select Portfolio Owner
        String UserLookup = sheet.getRow(37).getCell(3).getStringCellValue();
        System.out.println("user is--->"+UserLookup);
        driver.findElement(By.xpath("//div/input[@placeholder=\"Search People...\" and @title=\"Search People\"]")).sendKeys(UserLookup);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+UserLookup+"\"]")));
        myDynamicElement.click();

        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Selenium Test Historical Comment");

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\" ]//span[text()='Save']")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");
        System.out.println("the toast message value--->"+ToastMessage);

        //Expected Toast Message Value Set
        String ExpectedValue = "Portfolio \"Selenium Test Portfolio\" was created.";
        System.out.println("the ExpectedValue--->"+ExpectedValue);

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void EditPortfolio() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Click Edit Button
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Edit\"]//div[text()='Edit']")));
        myDynamicElement.click();

        //Select Organization
        String OrgLookup = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"pill focused uiPill--default uiPill\"])")));
        myDynamicElement.sendKeys(Keys.BACK_SPACE);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@placeholder=\"Search Organization...\"]")));
        myDynamicElement.sendKeys(OrgLookup);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\"Extron Organization\"]")));
        myDynamicElement.click();

        //Portfolio Name
        driver.findElement(By.xpath("//div/input[@class=\" input\" and @type=\"text\"]")).sendKeys(" Edit");

        //Select Portfolio Owner
        String UserLookup = sheet.getRow(37).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"pill focused uiPill--default uiPill\"])")));
        myDynamicElement.sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//div/input[@placeholder=\"Search People...\" and @title=\"Search People\"]")).sendKeys(UserLookup);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+UserLookup+"\"]")));
        myDynamicElement.click();

        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Selenium Test Historical Comment");

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\" ]//span[text()='Save']")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");
        System.out.println("the toast message value--->"+ToastMessage);

        //Expected Toast Message Value Set
        String ExpectedValue = "Portfolio \"Selenium Test Portfolio Edit\" was saved.";
        System.out.println("the ExpectedValue--->"+ExpectedValue);

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
