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
import java.util.concurrent.TimeUnit;

public class Deliverable {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Deliverable() throws IOException, IOException {
    }

    @BeforeTest
    public void Setup() throws IOException {
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
        String sObject = sheet.getRow(15).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 0)
    public void CreateDeliverable() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" or @data-aura-rendered-by=\"659:0\"]")));
        myDynamicElement.click();

        //Associated Organization
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys("Extron Organization");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Organization\"]")));
        myDynamicElement.click();


        //Associated portfolio
        driver.findElement(By.xpath("//input[@title=\"Search Portfolios\"]")).sendKeys("Extron Portfolio");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Portfolio\"]")));
        myDynamicElement.click();

        //Associated Program
        driver.findElement(By.xpath("//input[@title=\"Search Programs\"]")).sendKeys("Extron Program");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Program\"]")));
        myDynamicElement.click();

        //Associated Project
        driver.findElement(By.xpath("//input[@title=\"Search Projects\"]")).sendKeys("Extron Project");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Project\"]")));
        myDynamicElement.click();


        //Click HR Sensitive Data
        driver.findElement(By.xpath("//div[@data-value=\"HR Sensitive Data\"]")).click();

        //Click Button
        driver.findElement(By.xpath("//button[@title=\"Move selection to Chosen\"]")).click();



        //Deliverable Name
        driver.findElement(By.xpath("//div/input[@type=\"text\" and @maxlength=\"80\"]")).sendKeys("New Deliverable 2");

        //Deliverable Description
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])[1]")).sendKeys(" Des");

        //Deliverable Type
        driver.findElement(By.xpath("//div/input[@title=\"Search Deliverable Types\" and @placeholder=\"Search Deliverable Types...\"]")).sendKeys("Extron DT ");
        driver.findElement(By.xpath("//div[@title=\"Extron  DT\"]")).click();

        //Associted Object
        driver.findElement(By.xpath("//div/input[@title=\"Search Objects\" and @role=\"combobox\"]")).sendKeys("Extron Object");
        driver.findElement(By.xpath("//div[@title=\"Extron Object\"]")).click();

        //Associted BP
        driver.findElement(By.xpath("//div/input[@title=\"Search Business Processes\" and @role=\"combobox\"]")).sendKeys("Extron Child BP");
        driver.findElement(By.xpath("//div[@title=\"Extron Child BP\"]")).click();

        //Measures & Metrics
        driver.findElement(By.xpath("//div/input[@placeholder=\"Search Measures and Metrics...\" and @title=\"Search Measures and Metrics\"]")).sendKeys("Extron Metrics");
        driver.findElement(By.xpath("//div[@title=\"Extron Metrics\"]")).click();

        //Capital or Expense
        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).sendKeys("Capital");


        //Scope Lever
        driver.findElement(By.xpath("(//a[@class=\"select\"])[3]")).sendKeys("Form");



        //Associated Project Team/Stakeholder Grp
        driver.findElement(By.xpath("//div/input[@title=\"Search Stakeholder Groups\"]")).sendKeys("Extron Stakeholder Group");
        driver.findElement(By.xpath("//div[@title=\"Extron Stakeholder Group\"]")).click();

        //Deliverable Narrative
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Save Deliverable
        driver.findElement(By.xpath("//div//button[@title=\"Save\"]")).click();
        Thread.sleep(5000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Deliverable \"New Deliverable 2\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

    }

    @Test(priority = 1)
    public void EditDeliverable() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Edit Deliverable
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class=\"slds-button slds-button_icon-border-filled\"]")));
        myDynamicElement.click();

        //click Edit Button
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name=\"PlatinumPMO__Deliverable__c.PlatinumPMO__Edit\"]")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Edit'])[4]")));
        myDynamicElement.click();

        //New Deliverable 2 Remove
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@type=\"text\" and @maxlength=\"80\"]")));
        myDynamicElement.clear();
        myDynamicElement.sendKeys("New Del 2");

        //Edit Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test salenium");

        //Save
        driver.findElement(By.xpath("//div//button[@title=\"Save\"]")).click();
        Thread.sleep(5000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Deliverable \" New Del 2 \" was saved.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);
    }


    @Test(priority = 2)
    public void AddWorkpackage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click Add Workpackage
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Deliverable__c.PlatinumPMO__Add_Work_Package\"]")));
        myDynamicElement.click();


        //Click Extron Program
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\" and text()='Extron Program']")));
        myDynamicElement.click();


        //Click Extron project
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[2]")).sendKeys("Extron Project");
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//li[@class=\"slds-listbox__item\"])[5]")));
        myDynamicElement.click();

        //Deliverable
        driver.findElement(By.xpath("(//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"])[3]")).sendKeys("Extron Deliverable Retest");
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[2]/div/div/div[1]/div/div[2]/div[10]/div/ul/li[1]/span/span[2]/span[1]")));
        myDynamicElement.click();


        //WP Name
        driver.findElement(By.xpath("//input[@name=\"WpName\"]")).sendKeys("WP 2");

        //URL Name
        driver.findElement(By.xpath("//input[@name=\"liveURL\"]")).sendKeys("salesforce.in");

        //WP Type
        driver.findElement(By.xpath("(//input[@type=\"text\"])[8]")).sendKeys("Extron WP Type");
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\" and text()='Extron WP Type']")));
        myDynamicElement.click();

        //WP Phase
        driver.findElement(By.xpath("//select[@class=\"slds-select\" and @name=\"wpPhase\"]")).sendKeys("ASAP: 1-Preparation");

        //Estimated Effort
        driver.findElement(By.xpath("//input[@inputmode=\"decimal\"]")).sendKeys("4 hours");

        //WP Narrative
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");

        //Work Package Narrative Overflow:
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Save
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();
        Thread.sleep(5000);

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The record was saved.";


        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);
    }

    /*@Test(priority = 3)
    public void AddTemplate() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Click Add Template
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Deliverable__c.PlatinumPMO__Add_Template\"]")));
        myDynamicElement.click();

        //Click Deliverable Template
        driver.findElement(By.xpath("//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"]")).sendKeys("DEMO FOR DELIVERABLE");
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='DEMO FOR DELIVERABLE']")));
        myDynamicElement.click();


        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys(" Test Templete");

        //Click Add Button
        driver.findElement(By.xpath("(//button[@class=\"slds-button slds-button--neutral uiButton\"])[1]")).click();
        Thread.sleep(5000);

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The Records Saved";


        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);

    }*/


    @Test(priority = 4)
    public void AddRaci() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Add Raci Chart
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name=\"PlatinumPMO__Deliverable__c.PlatinumPMO__RACI_Chart\"]")));
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

        //Historical Comment
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
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name=\"PlatinumPMO__Deliverable__c.PlatinumPMO__RACI_Chart\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //User Type
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class=\"slds-select\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()='Accountable']")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //User
        driver.findElement(By.xpath("//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"]")).sendKeys("Techcloud Developer\n");
        Thread.sleep(3000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Techcloud Developer']")));
        myDynamicElement.click();

        //Historical Comment
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
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);

        //Add Raci Chart
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name=\"PlatinumPMO__Deliverable__c.PlatinumPMO__RACI_Chart\"]")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //User Type
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@class=\"slds-select\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[text()='Consulted']")));
        myDynamicElement.click();

        Thread.sleep(2000);

        //User
        driver.findElement(By.xpath("//input[@class=\"slds-lookup__search-input slds-input inputSize input uiInput uiInputText uiInput--default uiInput--input\"]")).sendKeys("Subhajit Mishra\n");
        Thread.sleep(3000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Subhajit Mishra']")));
        myDynamicElement.click();

        //Historical Comment
        driver.findElement(By.xpath("//textarea[@name=\"HistoricalComment\"]")).sendKeys("Test Historical Comment");

        //Save
        driver.findElement(By.xpath("//button[text()='Save']")).click();

        Thread.sleep(4000);

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage2 = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval2 = "The record was successfully created.";


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