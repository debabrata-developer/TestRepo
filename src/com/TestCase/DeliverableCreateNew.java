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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DeliverableCreateNew {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public DeliverableCreateNew() throws IOException, IOException {
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
    public void CreateDeliverable() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" or @data-aura-rendered-by=\"659:0\"]")));
        myDynamicElement.click();

        //For Organization lookup
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/Input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys(" Extron Organization");
        driver.findElement(By.xpath("//div[@title=\"Extron Organization\"]")).click();

        driver.findElement(By.xpath("//div/Input[@title=\"Search Portfolios\"]")).sendKeys("Extron Portfolio");
        driver.findElement(By.xpath("//div[@title=\"Extron Portfolio\"]")).click();


        driver.findElement(By.xpath("//div/input [@role= \"combobox\" and @title =\"Search Programs\"]")).sendKeys("Extron Program");
        driver.findElement(By.xpath("//div[@title=\"Extron Program\"]")).click();


        driver.findElement(By.xpath("//div/input[@role=\"combobox\" and @title=\"Search Projects\"]")).sendKeys("Extron Project");
        driver.findElement(By.xpath("//div[@title=\"Extron Project\"]")).click();

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
        //driver.findElement(By.xpath("")).sendKeys("");
        //driver.findElement(By.xpath("")).click();

        //Associated Project Team/Stakeholder Grp
        driver.findElement(By.xpath("//div/input[@title=\"Search Stakeholder Groups\"]")).sendKeys("Extron Stakeholder Group");
        driver.findElement(By.xpath("//div[@title=\"Extron Stakeholder Group\"]")).click();

        //Deliverable Narrative
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Save Deliverable
        driver.findElement(By.xpath("//div//button[@title=\"Save\"]")).click();

    }
    @Test(priority =1)
    public void EditDeliverable() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Edit Deliverable
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/header/div[2]/div/div[2]/ul/li[4]/div/div[1]/div/div/a/lightning-icon/lightning-primitive-icon")));
        myDynamicElement.click();

        //click Edit Button
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class=\"forceActionLink\" and @title=\"Edit\"])[1]")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class=\"slds-button slds-button--neutral slds-button slds-button_brand uiButton\"]")));
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
    }
    @AfterTest
    //Closing the Chrome
    public void terminateBrowser(){
        driver.close();
    }


}
