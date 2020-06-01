package TestCaseOther;


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

public class IssueLogCreateEdit {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public IssueLogCreateEdit() throws IOException, IOException {
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
        String sObject = sheet.getRow(18).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 0)
    public void CreateIssueLog() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
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



        //Issue Log Name
        driver.findElement(By.xpath("//input[@class=\" input\" and @maxlength=\"80\"]")).sendKeys("New Issue Log 2");


        //Priority
        driver.findElement(By.xpath("(//a[@class=\"select\"])")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"3-Low\"]")));
        myDynamicElement.click();


        //Risk Register
        driver.findElement(By.xpath("//input[@title=\"Search Risk Register\"]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Risk Test Rupak\"]")));
        myDynamicElement.click();


        //Due Date
        driver.findElement(By.xpath("//a[@class=\"datePicker-openIcon display\"]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='17']")));
        myDynamicElement.click();


        //Issue Coordinate
        driver.findElement(By.xpath("(//input[@class=\" default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup\" and @title=\"Search People\"])[1]")).sendKeys("Techcloud Developer");
        driver.findElement(By.xpath("//div[@title=\"Techcloud Developer\"]")).click();

        //Issue Impacts
        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"The business\"]")));
        myDynamicElement.click();

        //Issue Owner
        driver.findElement(By.xpath("(//input[@class=\" default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input uiInput uiAutocomplete uiInput--default uiInput--lookup\" and @title=\"Search People\"])[2]")).sendKeys("Riya Samanta");
        driver.findElement(By.xpath("(//div[@title=\"Riya Samanta\"])[2]")).click();

        //Associted Change Request
        driver.findElement(By.xpath("//input[@placeholder=\"Search Change Request Log...\"]")).sendKeys("Extron Change Request");
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Change Request\"]")));
        myDynamicElement.click();
        Thread.sleep(2000);

        //issue Signoff Resolution
        driver.findElement(By.xpath("(//input[@placeholder=\"Search People...\"])[3]")).sendKeys("Subhajit Mishra");
        driver.findElement(By.xpath("(//div[@class=\"primaryLabel slds-truncate slds-lookup__result-text\" and @title=\"Subhajit Mishra\"])[2]")).click();



        //Issue Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");

        //Final Resolution
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");

        //Recommended Resolution
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");


        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Save IssueLog
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
        Thread.sleep(5000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Issue Log \" New Issue Log 2 \" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void EditIssueLog() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);


        //Edit WP
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@class=\"slds-button slds-button_icon-border-filled\"])[4]")));
        myDynamicElement.click();

        //click Edit Button
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@name=\"PlatinumPMO_Issue_Logc.PlatinumPMO_Edit\"]")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[text()='Edit'])[3]")));
        myDynamicElement.click();


        //Issue Log Name Change
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class=\" input\" and @maxlength=\"80\"]")));
        myDynamicElement.clear();
        myDynamicElement.sendKeys(" TEST ISSUE LOG 2");

        //Priority
        driver.findElement(By.xpath("(//a[@class=\"select\"])")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"2-Medium\"]")));
        myDynamicElement.click();


        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys(" Test IL");

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
        Thread.sleep(5000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Issue Log \" TEST ISSUE LOG 2 \" was saved.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);

    }


    @Test(priority = 3)
    public void AddProject() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Add Project
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO_Issue_Logc.PlatinumPMO_Add_Project\"]")));
        myDynamicElement.click();

        // Project Name
        driver.findElement(By.xpath("//input[@placeholder=\"search..\"]")).sendKeys("Extron Project");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"Extron Project\"]")));
        myDynamicElement.click();

        //Click Save Button
        driver.findElement(By.xpath("//span[text()=\"Add Project\"]")).click();
        Thread.sleep(5000);

    }

    @Test(priority = 4)
    public void AddDeliverable() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Add Deliverable
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO_Issue_Logc.PlatinumPMO_Add_Deliverable\"]")));
        myDynamicElement.click();

        // Deliverable Name
        driver.findElement(By.xpath("//input[@placeholder=\"search..\"]")).sendKeys("Extron Deliverable Retest");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Extron Deliverable Retest']")));
        myDynamicElement.click();

        //Click Save Button
        driver.findElement(By.xpath("//span[text()='Add Deliverable']")).click();
        Thread.sleep(5000);

    }

    @Test(priority = 5)
    public void AddCRL() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@class=\"slds-button slds-button_icon-border-filled\"])[2]")));
        myDynamicElement.click();

        //click Add CRL
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Add Change Request Log']")));
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


        //CRL Name
        driver.findElement(By.xpath("//div/input[@type=\"text\" and @maxlength=\"80\"]")).sendKeys("NEW CRL 2");

        //Program Phase
        driver.findElement(By.xpath("(//a[@class=\"select\"])[1]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"ASAP: 0- Initiation\"]")));
        myDynamicElement.click();


        //Change Request Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Business Junction
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");


        //Change Request Type
        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Business Process\"]")));
        myDynamicElement.click();


        //Associated Deliverable
        driver.findElement(By.xpath("//input[@title=\"Search Deliverables\"]")).sendKeys("Extron Deliverable Retest");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Deliverable Retest\"]")));
        myDynamicElement.click();


        //Associated Systems
        driver.findElement(By.xpath("//input[@title=\"Search Systems\"]")).sendKeys("Extron System 1");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron System 1\"]")));
        myDynamicElement.click();


        //Associated Defect Log
        driver.findElement(By.xpath("//input[@title=\"Search Defect Log\"]")).sendKeys("Extron Defect 2");
        Thread.sleep(2000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Extron Defect 2\"]")));
        myDynamicElement.click();

        //Change Request Sponsor
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[1]")).sendKeys("Techcloud Developer");
        driver.findElement(By.xpath("//div[@title=\"Techcloud Developer\"]")).click();

        //Change Review Board
        driver.findElement(By.xpath("//input[@title=\"Search Stakeholder Groups\"]")).sendKeys("Extron Stakeholder");
        driver.findElement(By.xpath("//div[@title=\"Extron Stakeholder Group\"]")).click();

        //Impact Assessment Owner
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[2]")).sendKeys("Riya Samanta");
        driver.findElement(By.xpath("(//div[@title=\"Riya Samanta\"])[1]")).click();


        //Impact Assessment Due Date
        driver.findElement(By.xpath("//a[@class=\"datePicker-openIcon display\"]")).click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='15']")));
        myDynamicElement.click();


        //Impact Assessment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Estimated Project Hours- Breakdown
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");


        //Overall Project Impact
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Potential Workaround
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Estimated Hours to Complete Change
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("3");

        //Estimated Cost for Change
        driver.findElement(By.xpath("(//input[@data-aura-class=\"uiInputSmartNumber\"])[2]")).sendKeys("$50");

        //System Core Modification
        driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[9]")).click();

        //Change Request Delivery Manager
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[3]")).sendKeys("Debabrata Ghosh");
        driver.findElement(By.xpath("//div[@title=\"Debabrata Ghosh\"]")).click();

        //Development Details
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");

        //Transport Number
        driver.findElement(By.xpath("//textarea[@role=\"textbox\"]")).sendKeys("323123");


        //Implementation Notes
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("implemented");

        //Implementation Environment
        driver.findElement(By.xpath("//input[@maxlength=\"50\"]")).sendKeys("ok");


        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");

        //Save CRL
        driver.findElement(By.xpath("//div//button[@title=\"Save\"]")).click();
        Thread.sleep(5000);
    }

   /* @AfterTest
    //Closing the Chrome
    public void terminateBrowser(){
        driver.close();
    }*/
}
