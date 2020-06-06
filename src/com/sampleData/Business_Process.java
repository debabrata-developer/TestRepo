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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Business_Process {
    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Business_Process() throws IOException, IOException {
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
        String sObject = sheet.getRow(19).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 0)
    public void CreateBusinessProcess() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" or @data-aura-rendered-by=\"659:0\"]")));
        myDynamicElement.click();

        //Associated Organization
        String OrgName = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder=\"search..\"])[1]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+OrgName+"']")));
        myDynamicElement.click();


        //Associated portfolio
        String PortName = sheet.getRow(12).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[2]")).sendKeys(PortName);
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+PortName+"']")));
        myDynamicElement.click();

        //Associated Program
        String progName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[3]")).sendKeys(progName);
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+progName+"']")));
        myDynamicElement.click();

        //Business Process Name
        String BusiName = sheet.getRow(19).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//div/input[@type=\"text\" and @maxlength=\"80\"]")).sendKeys(BusiName);

        //Business prefix
        driver.findElement(By.xpath("//select[@name=\"BpPrefix\"]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value=\"LTC: Lead to Cash\"]")));
        myDynamicElement.click();

        //BP Level
        driver.findElement(By.xpath("//input[@name=\"BpLevel\"]")).sendKeys("1");

        //Parent Process UID
        //driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[4]")).sendKeys("");
        //myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"tyr6t\"]")));
        //myDynamicElement.click();

        //BP Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Save BP
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();
        Thread.sleep(5000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Business Process \""+BusiName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);
        Thread.sleep(5000);

    }

    @AfterTest
    //Closing the Chrome
    public void terminateBrowser(){
        driver.close();
    }
}
