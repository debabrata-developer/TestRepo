package TestCaseOther;
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
import java.security.Key;
import java.util.concurrent.TimeUnit;

public class TimeTrackingCreateEdit {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public TimeTrackingCreateEdit() throws IOException, IOException {
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
        String sObject = sheet.getRow(34).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 0)
    public void CreateTimeTracking() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Time Report
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder=\"search..\"])[1]")));
        myDynamicElement.sendKeys("TR");
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(2000);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //For Organization lookup
        String OrgName = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder=\"search..\"])[2]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+OrgName+"\"]")).click();
        Thread.sleep(1000);

        //Associated Portfolio
        String PortName = sheet.getRow(12).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[3]")).sendKeys(PortName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+PortName+"\"]")).click();
        Thread.sleep(1000);

       //Associated Program
        String ProgName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[4]")).sendKeys(ProgName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+ProgName+"\"]")).click();
        Thread.sleep(1000);

        //Associated Project
        String ProjectName = sheet.getRow(14).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[5]")).sendKeys(ProjectName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+ProjectName+"\"]")).click();
        Thread.sleep(1000);

        //Associated WorkPackage
        String WPName = sheet.getRow(16).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[6]")).sendKeys(WPName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+WPName+"\"]")).click();
        Thread.sleep(1000);

        //Bilable
        driver.findElement(By.xpath("//span[@class=\"slds-checkbox_faux\"]")).click();

        //Date
        driver.findElement(By.xpath("//input[@name=\"TimeDate\"]")).click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='24']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Hours
        driver.findElement(By.xpath("//input[@name=\"TimeHours\"]")).sendKeys("4 hours");
        Thread.sleep(1000);

        //Comments
        driver.findElement(By.xpath("//textarea[@class=\"slds-size--1-of-1 slds-p-horizontal_x-small textarea\"]")).sendKeys("test");
        Thread.sleep(1000);

        //Save TimeTracking
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The record was successfully created.";


        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);

    }
    @Test(priority = 2)
    public void EditTimeTracking() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Edit Timetracking
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//lightning-icon[@class=\"slds-button__icon slds-icon-utility-down slds-icon_container forceIcon\"])[2]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //click Edit Button
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"EDIT\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@class=\"slds-button slds-button--neutral slds-button slds-button_brand uiButton\"])[1]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Date
        driver.findElement(By.xpath("//a[@class=\"datePicker-openIcon display\"]")).click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='17']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Comments
        driver.findElement(By.xpath("(//textarea[@class=\" textarea\"])[2]")).sendKeys(" Test TimeTracking");
        Thread.sleep(1000);

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
        Thread.sleep(1000);

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The record was successfully saved.";


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
