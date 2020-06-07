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
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Program {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Program() throws IOException {
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
        String sObject = sheet.getRow(13).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void CreateProgram() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Click On New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Associated Organization
        String OrgName = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+OrgName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated portfolio
        String PortName = sheet.getRow(12).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Portfolios\"]")).sendKeys(PortName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+PortName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Program Name
        String ProgName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@class=\" input\"])[1]")).sendKeys(ProgName);
        Thread.sleep(1000);

        //Sensitive Data
        driver.findElement(By.xpath("//span[@title=\"HR Sensitive Data\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@title=\"Move selection to Chosen\"]")).click();
        Thread.sleep(1000);

        //Program Sponsor
        String UserName = sheet.getRow(37).getCell(4).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[1]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Business Lead
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[2]")).sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\""+UserName+"\"])[2]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Technology Lead
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[3]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Program manager
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[4]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Financial advisor
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[5]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Administrator
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[6]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Solution Integrator
        String SolutionName = sheet.getRow(39).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Solution Integrator\"]")).sendKeys(SolutionName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+SolutionName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Target Star Date
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[1]")).click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='20']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Target Completion Date
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[2]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class=\"navLink nextMonth\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='20']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Estimated Budget
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("500000");
        Thread.sleep(1000);

        //Program Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Program Charter
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Program Charter Overflow
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
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
        String ExpectedValue = "Program \""+ProgName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

    }

    @Test(priority = 2)
    public void AddUser() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Add User Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Program__c.PlatinumPMO__Add_User\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Search User
        String UserName = sheet.getRow(37).getCell(8).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class=\"slds-lookup__search-input slds-input inputSize input uiInput uiInputText uiInput--default uiInput--input\"]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+UserName+"']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("//button[text()='Save']")).click();
        Thread.sleep(1000);

        //get toast message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The Record was Saved";


        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);
    }

    @AfterTest
    public void close(){
        //closing the chrome
        driver.quit();
    }
}

