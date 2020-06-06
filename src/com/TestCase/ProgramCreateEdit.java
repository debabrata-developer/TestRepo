package com.TestCase;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ProgramCreateEdit {
    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public ProgramCreateEdit() throws IOException {
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

        //Associated Organization
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys("Extron Organization");
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Organization\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);
        //Associated portfolio
        driver.findElement(By.xpath("//input[@title=\"Search Portfolios\"]")).sendKeys("Extron Portfolio");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Portfolio\"]")));
        myDynamicElement.click();

        //Program Name
        driver.findElement(By.xpath("(//input[@class=\" input\"])[1]")).sendKeys("Test Selenium Program");

        //Sensitive Data
        driver.findElement(By.xpath("//span[@title=\"HR Sensitive Data\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@title=\"Move selection to Chosen\"]")).click();

        //Program Sponsor
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[1]")).sendKeys("Techcloud Developer");
       Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Techcloud Developer\"])[1]")));
        myDynamicElement.click();

        Thread.sleep(2000);
        //Business Lead
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[2]")).sendKeys("Techcloud Developer");
       Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Techcloud Developer\"])[2]")));
        myDynamicElement.click();

        //Technology Lead
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[3]")).sendKeys("Techcloud Developer");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Techcloud Developer\"])[3]")));
        myDynamicElement.click();
       Thread.sleep(2000);
        //Program manager
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[4]")).sendKeys("Techcloud Developer");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Techcloud Developer\"])[4]")));
        myDynamicElement.click();

        //Financial advisor
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[5]")).sendKeys("Techcloud Developer");
       Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Techcloud Developer\"])[5]")));
        myDynamicElement.click();

        //Administrator
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[6]")).sendKeys("Techcloud Developer");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@title=\"Techcloud Developer\"])[6]")));
        myDynamicElement.click();

        //Solution Integrator
        driver.findElement(By.xpath("//input[@title=\"Search Solution Integrator\"]")).sendKeys("Extron Solution Integrator");
       Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Solution Integrator\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);
        //Target Star Date
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[1]")).click();
       Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='20']")));
        myDynamicElement.click();

        //Target Completion Date
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[2]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class=\"navLink nextMonth\"]")));
        myDynamicElement.click();
       Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='20']")));
        myDynamicElement.click();

        //Estimated Budget
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("500000");

        Thread.sleep(1000);
        //Program Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");

        //Program Charter
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");

        //Program Charter Overflow
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");

        //Save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Program \"Test Selenium Program\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

    }
    @Test(priority = 2)
    public void EditProgram() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Edit Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type=\"button\"and @class=\"slds-button slds-button_icon-border-filled\"]")));
        myDynamicElement.click();

        //Edit
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name=\"PlatinumPMO__Program__c.PlatinumPMO__Edit\"]")));
        myDynamicElement.click();

        //Edit Popup
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Edit'])[2]")));
        myDynamicElement.click();
        Thread.sleep(2000);

        //Name
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class=\" input\"])[1]")));
        myDynamicElement.clear();
        myDynamicElement.sendKeys("Test Selenium Program-Edit");

        Thread.sleep(2000);
        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment-Edit");


        //Save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Program \"Test Selenium Program-Edit\" was saved.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

        driver.navigate().refresh();

        Thread.sleep(10000);

    }
    @Test(priority = 3)
    public void AddRaci() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Add Raci Chart
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Program__c.PlatinumPMO__RACI_Chart\"]")));
        myDynamicElement.click();

        //User Type
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class=\"slds-select\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()='Responsible']")));
        myDynamicElement.click();

        //User
        driver.findElement(By.xpath("//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"]")).sendKeys("Techcloud Developer\n");
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Techcloud Developer']")));
        myDynamicElement.click();

        Thread.sleep(2000);
        //Change Description
        driver.findElement(By.xpath("//textarea[@name=\"HistoricalComment\"]")).sendKeys("Test Historical Comment");

        //Save
        driver.findElement(By.xpath("//button[text()='Save']")).click();

        Thread.sleep(4000);
        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The record was successfully created.";


        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);


        //Add Raci Chart
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Program__c.PlatinumPMO__RACI_Chart\"]")));
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

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage1 = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval1 = "The record was successfully created.";


        //Check
        Assert.assertTrue(ToastMessage1.contains(Chechval1));
        Thread.sleep(5000);


        //Add Raci Chart
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Program__c.PlatinumPMO__RACI_Chart\"]")));
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

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage2 = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval2 = "The record was successfully created.";


        //Check
        Assert.assertTrue(ToastMessage2.contains(Chechval2));
        Thread.sleep(5000);
    }

    @Test(priority = 4)
    public void AddUser() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Add User Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Program__c.PlatinumPMO__Add_User\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);
        //Search User
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class=\"slds-lookup__search-input slds-input inputSize input uiInput uiInputText uiInput--default uiInput--input\"]")));
        myDynamicElement.sendKeys("Rupak Maity");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Rupak Maity']")));
        myDynamicElement.click();

        //Save
        driver.findElement(By.xpath("//button[text()='Save']")).click();

        Thread.sleep(4000);
        //get toast message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The Record was Saved";


        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);
    }

    @Test(priority = 5)
    public void AddProject() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Add Project
        WebElement element = driver.findElement(By.xpath("//button[@name=\"PlatinumPMO__Program__c.PlatinumPMO__AddNewProject\"]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();


        //Project Name
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"Project Name\"]")));
        Thread.sleep(2000);
        myDynamicElement.sendKeys("Test Selenium Project");

        Thread.sleep(2000);

        //Project Description
         driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
         Thread.sleep(4000);

        //Target Start Date
        WebElement element1 = driver.findElement(By.xpath("//input[@name=\"TargetStartDate\"]"));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(element1).click().build().perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='17']")).click();

        Thread.sleep(2000);

        //Target Completion Date
        WebElement element2 = driver.findElement(By.xpath("//input[@name=\"TargetCompletionDate\"]"));
        Actions actions2 = new Actions(driver);
        actions2.moveToElement(element2).click().build().perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='30']")).click();

        //Estimated Budget
        driver.findElement(By.xpath("//input[@name=\"EstimatedBudget\"]")).sendKeys("2000");
        Thread.sleep(2000);

        //Capital
        driver.findElement(By.xpath("(//select[@class=\"slds-select\"])[3]")).click();
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()='Capital']")));
        myDynamicElement.click();

        Thread.sleep(2000);
       //Business Process Manager
        driver.findElement(By.xpath("(//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"])[4]")).sendKeys("Techcloud Developer");
        Thread.sleep(4000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Techcloud Developer'])[1]")));
        myDynamicElement.click();

        Thread.sleep(2000);


        //Timesheet Approver
        driver.findElement(By.xpath("(//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"])[6]")).sendKeys("Techcloud Developer");
       Thread.sleep(4000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Techcloud Developer'])[4]")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //project Condition
        driver.findElement(By.xpath("(//select[@class=\"slds-select\"])[1]")).click();
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()='Red']")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //Project Phase
        driver.findElement(By.xpath("(//select[@class=\"slds-select\"])[2]")).click();
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()='ASAP: 0- Initiation']")));
        myDynamicElement.click();

        Thread.sleep(2000);



         //Associated Solution Integrator
        driver.findElement(By.xpath("(//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"])[5]")).sendKeys("Extron Solution Integrator");
        Thread.sleep(4000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Extron Solution Integrator']")));
        myDynamicElement.click();
        Thread.sleep(2000);

        //Expense Approver
        driver.findElement(By.xpath("(//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"])[7]")).sendKeys("Techcloud Developer");
        Thread.sleep(4000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Techcloud Developer'])[7]")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");

        //Save
        driver.findElement(By.xpath("//button[text()='Save']")).click();

        Thread.sleep(5000);

        //get toast message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--info slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The record was saved.";


        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);

    }
    @Test(priority = 6)
    public void getbacktosObject() throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(13).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        Thread.sleep(10000);
        driver.findElement(By.xpath("//input[@name=\"PlatinumPMO__Program__c-search-input\"]")).sendKeys("Test Selenium Program-Edit\n");

        Thread.sleep(7000);
        WebElement obj = driver.findElement(By.xpath("(//a[text()='Test Selenium Program-Edit'])[1]"));
        obj.click();
        Thread.sleep(10000);

    }

    @Test(priority = 7)
      public void AddNewGoal() throws InterruptedException {
       WebDriverWait wait = new WebDriverWait(driver, 30);
       //Add New Goal
       WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type=\"button\"and @class=\"slds-button slds-button_icon-border-filled\"]")));
       myDynamicElement.click();
       Thread.sleep(2000);

       //Click on new goal
       myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Goal']")));
       myDynamicElement.click();

       Thread.sleep(2000);

       //Project
       myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder=\"search..\"])[4]")));
       myDynamicElement.sendKeys("Test Selenium Project");

       Thread.sleep(3000);
       myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Test Selenium Project']")));
       myDynamicElement.click();

       Thread.sleep(3000);

       //Goal Name
       myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"GoalName\"]")));
       myDynamicElement.sendKeys("Test Selenium Goal");

       Thread.sleep(3000);

       //Goal Description
       myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")));
       myDynamicElement.sendKeys("Testing");

       Thread.sleep(2000);

       //Historical Commnet
       driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Commnet");

       Thread.sleep(2000);

       //Save
       driver.findElement(By.xpath("//button[text()='Save']")).click();

       Thread.sleep(5000);
       //get toast message
       myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--info slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
       String ToastMessage = myDynamicElement.getAttribute("innerHTML");

       //checking Toast Message Value Set
       String Chechval = "The record was saved.";


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






