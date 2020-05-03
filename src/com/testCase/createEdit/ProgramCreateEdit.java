package com.testCase.createEdit;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void CreateProgram() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" and text()='New']")));
        myDynamicElement.click();

        //Select Organization
        String OrgLookup = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@placeholder=\"Search Organization...\"]")));
        myDynamicElement.sendKeys(OrgLookup);
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+OrgLookup+"\"]")));
        myDynamicElement.click();

        //Select Portfolio
        String PortLookup = sheet.getRow(12).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@placeholder=\"Search Portfolios...\"]")));
        myDynamicElement.sendKeys(PortLookup);
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+PortLookup+"\"]")));
        myDynamicElement.click();

        //Program Name
        driver.findElement(By.xpath("(//div/input[@class=\" input\" and @type=\"text\"])[1]")).sendKeys("Selenium Test Program");

        //Target Start Date
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
        Date date = new Date();
        String cdate= dateFormat.format(date);
        driver.findElement(By.xpath("(//div/input[@class=\" input\" and @type=\"text\"])[2]")).sendKeys(cdate);

        //Target Completion Date
        date = new Date(5);
        cdate= dateFormat.format(date);
        driver.findElement(By.xpath("(//div/input[@class=\" input\" and @type=\"text\"])[3]")).sendKeys(cdate);

        //Sensitive Data
        driver.findElement(By.xpath("//div[@data-value=\"HR Sensitive Data\"]")).click();
        driver.findElement(By.xpath("//button[@title=\"Move selection to Chosen\"]/lightning-primitive-icon")).click();

        //Program Sponsor
        String PSponsor = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[1]")).sendKeys(PSponsor);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+PSponsor+"\"]")).click();

        //Business Lead
        String BLead = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[2]")).sendKeys(BLead);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+BLead+"\"]")).click();

        //Technology Lead
        String TLead = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[3]")).sendKeys(TLead);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+TLead+"\"]")).click();

        //Program Manager
        String PManager = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[4]")).sendKeys(PManager);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+PManager+"\"]")).click();

        //Financial Advisor
        String FAdvisor = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[5]")).sendKeys(FAdvisor);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+FAdvisor+"\"]")).click();

        //Administrator
        String Administrator = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[6]")).sendKeys(Administrator);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+Administrator+"\"]")).click();

        //Estimated Budget
        driver.findElement(By.xpath("//input[@data-aura-class=\"uiInputSmartNumber\"]")).sendKeys("50");

        //Program PhaseIf
        driver.findElement(By.xpath("(//a[@role=\"button\" and @class=\"select\"])[1]")).sendKeys("ASAP: 1- Preparation");

        //Program Condition
        driver.findElement(By.xpath("(//a[@role=\"button\" and @class=\"select\"])[2]")).sendKeys("Yellow");

        //Solution Integrator
        String SIntegrator = sheet.getRow(38).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Solution Integrator\" and @placeholder=\"Search Solution Integrator...\"]")).sendKeys(SIntegrator);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(text(),'"+SIntegrator+"')]")).click();

        //Program Description
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])")).sendKeys("Test Selenium Description");

        //Program Charter
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])")).sendKeys("Test Selenium Charter");

        //Program Charter (overflow)
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])")).sendKeys("Test Selenium Charter (overflow)");

        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Selenium Test Historical Comment");

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\" ]//span[text()='Save']")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Program \"Selenium Test Program\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);
    }

    /*@Test(priority = 2)
    public void EditProgram() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" and text()='New']")));
        myDynamicElement.click();

        //Select Organization
        String OrgLookup = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@placeholder=\"Search Organization...\"]")));
        myDynamicElement.sendKeys(OrgLookup);
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+OrgLookup+"\"]")));
        myDynamicElement.click();

        //Select Portfolio
        String PortLookup = sheet.getRow(12).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@placeholder=\"Search Portfolios...\"]")));
        myDynamicElement.sendKeys(PortLookup);
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+PortLookup+"\"]")));
        myDynamicElement.click();

        //Program Name
        driver.findElement(By.xpath("(//div/input[@class=\" input\" and @type=\"text\"])[1]")).sendKeys("Selenium Test Program");

        //Target Start Date
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
        Date date = new Date();
        String cdate= dateFormat.format(date);
        driver.findElement(By.xpath("(//div/input[@class=\" input\" and @type=\"text\"])[2]")).sendKeys(cdate);

        //Target Completion Date
        date = new Date(5);
        cdate= dateFormat.format(date);
        driver.findElement(By.xpath("(//div/input[@class=\" input\" and @type=\"text\"])[3]")).sendKeys(cdate);

        //Sensitive Data
        driver.findElement(By.xpath("//div[@data-value=\"HR Sensitive Data\"]")).click();
        driver.findElement(By.xpath("//button[@title=\"Move selection to Chosen\"]/lightning-primitive-icon")).click();

        //Program Sponsor
        String PSponsor = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[1]")).sendKeys(PSponsor);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+PSponsor+"\"]")).click();

        //Business Lead
        String BLead = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[2]")).sendKeys(BLead);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+BLead+"\"]")).click();

        //Technology Lead
        String TLead = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[3]")).sendKeys(TLead);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+TLead+"\"]")).click();

        //Program Manager
        String PManager = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[4]")).sendKeys(PManager);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+PManager+"\"]")).click();

        //Financial Advisor
        String FAdvisor = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[5]")).sendKeys(FAdvisor);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+FAdvisor+"\"]")).click();

        //Administrator
        String Administrator = sheet.getRow(37).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[6]")).sendKeys(Administrator);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\""+Administrator+"\"]")).click();

        //Estimated Budget
        driver.findElement(By.xpath("//input[@data-aura-class=\"uiInputSmartNumber\"]")).sendKeys("50");

        //Program PhaseIf
        driver.findElement(By.xpath("(//a[@role=\"button\" and @class=\"select\"])[1]")).sendKeys("ASAP: 1- Preparation");

        //Program Condition
        driver.findElement(By.xpath("(//a[@role=\"button\" and @class=\"select\"])[2]")).sendKeys("Yellow");

        //Solution Integrator
        String SIntegrator = sheet.getRow(38).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Solution Integrator\" and @placeholder=\"Search Solution Integrator...\"]")).sendKeys(SIntegrator);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(text(),'"+SIntegrator+"')]")).click();

        //Program Description
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])")).sendKeys("Test Selenium Description");

        //Program Charter
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])")).sendKeys("Test Selenium Charter");

        //Program Charter (overflow)
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])")).sendKeys("Test Selenium Charter (overflow)");

        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Selenium Test Historical Comment");

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\" ]//span[text()='Save']")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Program \"Selenium Test Program\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);
    }*/

    @AfterTest
    public void close(){
        //closing the chrome
        driver.quit();
    }
}
