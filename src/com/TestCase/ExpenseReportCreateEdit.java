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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ExpenseReportCreateEdit {
    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);



    public ExpenseReportCreateEdit() throws IOException {
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
        String sObject = sheet.getRow(37).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void CreateExpenseReport() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Click On New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //Start Date
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[1]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='26']")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //Finish Date
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[2]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Go to next month\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='22']")));
        myDynamicElement.click();

        //Save
        driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
        Thread.sleep(5000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = " was created.";

        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);
    }

    @Test(priority = 2)
     public void EditExpenseReport() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Click On Edit Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class=\"slds-button slds-button_icon-border-filled\"] ")));
        myDynamicElement.click();

        Thread.sleep(2000);

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name=\"Edit\"]")));
        myDynamicElement.click();

        //Start Date
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[1]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='28']")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //Save
        driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
        Thread.sleep(5000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = " was saved.";

        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);

    }
    @Test(priority = 3)
     public void AddExpenseEntry() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Click On Edit Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Expense_Report__c.PlatinumPMO__Expense_Entry\"] ")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //Associated Organization
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys("Extron Organization");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Organization\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);
        //Associated Portfolio
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Portfolios\"]")));
        myDynamicElement.sendKeys("Extron Portfolio");
        Thread.sleep(2000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Portfolio\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //Associated Program
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Programs\"]")));
        myDynamicElement.sendKeys("Extron Program");
        Thread.sleep(4000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Program\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);


        //Associated Project
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Projects\"]")));
        myDynamicElement.sendKeys("Extron Project");
        Thread.sleep(2000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Project\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //Expense Amount
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")));
        myDynamicElement.sendKeys("50");
        Thread.sleep(2000);

        //Expense Description
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@class=\" textarea\"]")));
        myDynamicElement.sendKeys("Testing");
        Thread.sleep(2000);

        //Expense Name
        driver.findElement(By.xpath("//input[@class=\" input\"]")).sendKeys("Test Selenium Expense");

        Thread.sleep(2000);

        //Expense Date
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class=\"datePicker-openIcon display\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);
        myDynamicElement= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='30'])")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //Expense Category
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"select\"])[2]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Breakfast\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);
        //Attach Photo
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(2000);

        //Save
        driver.findElement(By.xpath("(//span[text()='Save'])[3]")).click();

        Thread.sleep(5000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = " was created.";

        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);











    }


}
