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

public class Cutover_Plan_Change_Request {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Cutover_Plan_Change_Request() throws IOException, IOException {
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
        String sObject = sheet.getRow(35).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 0)
    public void CreateCutoverPlanCR() throws InterruptedException {
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


        //Associated Environment
        driver.findElement(By.xpath("//input[@title=\"Search Environments\"]")).sendKeys("Extron Environment");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Environment\"]")));
        myDynamicElement.click();


        //Associated Cutover Task
        driver.findElement(By.xpath("//input[@title=\"Search Cutover Task\"]")).sendKeys("Extron Cutover Task");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Cutover Task\"]")));
        myDynamicElement.click();


        //CutoverPlanCR Name
        driver.findElement(By.xpath("//div/input[@type=\"text\" and @maxlength=\"80\"]")).sendKeys("NEW CUTOVER PLAN CR 2");


        //Requestor
        driver.findElement(By.xpath("(//input[@title=\"Search People\" and @role=\"combobox\"])[1]")).sendKeys("Riya Samanta");
        driver.findElement(By.xpath("(//div[@title=\"Riya Samanta\" and @class=\"primaryLabel slds-truncate slds-lookup__result-text\"])[1]")).click();

        //Summary of Change
        driver.findElement(By.xpath("//textarea[@class=\" textarea\" and @maxlength=\"32768\"]")).sendKeys("CUTOVER PLAN CR ");

        //Associted Project Team
        driver.findElement(By.xpath("(//input[@title=\"Search Stakeholder Groups\" and @class=\" default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup\"])[1]")).sendKeys("Extron Stakeholder Group");
        driver.findElement(By.xpath("//div[@title=\"Extron Stakeholder Group\"]")).click();

        //Plan Unique Id
        driver.findElement(By.xpath("//input[@maxlength=\"6\"]")).sendKeys("4567");

        //What Type of Change
        driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).sendKeys("Add task");

        //Cutover task type
        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Manual\"]")));
        myDynamicElement.click();

        //Phase
        driver.findElement(By.xpath("(//a[@class=\"select\"])[3]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"ASAP: 0-Initiation\"]")));
        myDynamicElement.click();

        //Primary Task Owner
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[2]")).sendKeys("Techcloud Developer");
        driver.findElement(By.xpath("(//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\"Techcloud Developer\"])[2]")).click();

        //Cutover Execution Cycle
        driver.findElement(By.xpath("(//a[@role=\"button\" and @class=\"select\"])[4]")).sendKeys("Trial Cutover 2");

        //Deliverable
        driver.findElement(By.xpath("//input[@title=\"Search Deliverables\"]")).sendKeys("Extron Deliverable Retest ");
        driver.findElement(By.xpath("//div[@title=\"Extron Deliverable Retest\"]")).click();

        //Estimated Dueration
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("3");

        //informed
        driver.findElement(By.xpath("(//input[@class=\" default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup\" and @title=\"Search People\"])[5]")).sendKeys("Shoubhik Maity ");
        driver.findElement(By.xpath("(//div[@title=\"Shoubhik Maity\" and @class=\"primaryLabel slds-truncate slds-lookup__result-text\"])[5]")).click();

        //Project Teams & Stakeholder grp
        driver.findElement(By.xpath("(//input[@title=\"Search Stakeholder Groups\" and @class=\" default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup\"])[2]")).sendKeys("Extron Stakeholder Group");
        driver.findElement(By.xpath("(//div[@title=\"Extron Stakeholder Group\"])[2]")).click();


        //Unique ID Predecessors
        driver.findElement(By.xpath("(//textarea[@maxlength=\"255\" and @class=\" textarea\"])[1]")).sendKeys("ok");

        //Start Requirements
        driver.findElement(By.xpath("//input[@class=\" input\" and @maxlength=\"15\"]")).sendKeys("Right");

        //Unique ID Succcessors
        driver.findElement(By.xpath("(//textarea[@maxlength=\"255\" and @class=\" textarea\"])[2]")).sendKeys("success");


        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Save CPCR
        driver.findElement(By.xpath("//div//button[@title=\"Save\"]")).click();
        Thread.sleep(5000);


        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");
        System.out.println("the toast message value--->" + ToastMessage);

        //Expected Toast Message Value Set
        String ExpectedValue = "Cutover Plan Change Request \"NEW CUTOVER PLAN CR 2\" was created.";
        System.out.println("the ExpectedValue--->" + ExpectedValue);

        //Check
        Assert.assertEquals(ToastMessage, ExpectedValue);

        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void EditCutoverPlanCR() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Click Edit Button
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Cutover_Plan_Change_Request__c.PlatinumPMO__Edit\"]")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@class=\"slds-button slds-button--neutral slds-button slds-button_brand uiButton\"])[1]")));
        myDynamicElement.click();

        //CPCR Name Change
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class=\" input\" and @maxlength=\"80\"]")));
        myDynamicElement.clear();
        myDynamicElement.sendKeys(" New CPCR 2");

        //Cutover task type
        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Extract\"]")));
        myDynamicElement.click();

        //Phase
        driver.findElement(By.xpath("(//a[@class=\"select\"])[3]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"ASAP: 1-Preparation\"]")));
        myDynamicElement.click();


        //Cutover Execution Cycle
        driver.findElement(By.xpath("(//a[@role=\"button\" and @class=\"select\"])[4]")).sendKeys("Trial Cutover 3");


        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys(" Test CPCR");

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
        Thread.sleep(5000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = " Cutover Plan Change Request \"New CPCR 2\" was saved.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);
    }


    @Test(priority = 2)
    public void AddRaci() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Add Raci Chart
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Cutover_Plan_Change_Request__c.PlatinumPMO__RACI_Chart\"]")));
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
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Cutover_Plan_Change_Request__c.PlatinumPMO__RACI_Chart\"]")));
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
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Cutover_Plan_Change_Request__c.PlatinumPMO__RACI_Chart\"]")));
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
    public void terminateBrowser() {
        driver.close();

    }
}
