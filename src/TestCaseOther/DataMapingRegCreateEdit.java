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
import java.util.concurrent.TimeUnit;

public class DataMapingRegCreateEdit {

    static WebDriver driver;
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public DataMapingRegCreateEdit() throws IOException, IOException {
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
        String sObject = sheet.getRow(36).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 0)
    public void CreateDataMappingReg() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //click New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" or @data-aura-rendered-by=\"659:0\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Associated Organization
        String OrgName = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder=\"search..\"])[1]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+OrgName+"\"]")).click();
        Thread.sleep(1000);

        //Associated portfolio
        String PortName = sheet.getRow(12).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[2]")).sendKeys(PortName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+PortName+"\"]")).click();
        Thread.sleep(1000);

        //Associated Program
        String ProgName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[3]")).sendKeys(ProgName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+ProgName+"\"]")).click();
        Thread.sleep(1000);

        //Associated Project
        String ProjectName = sheet.getRow(14).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[4]")).sendKeys(ProjectName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+ProjectName+"\"]")).click();
        Thread.sleep(1000);

        //Associated Deliverable
        String DelivName = sheet.getRow(15).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[5]")).sendKeys(DelivName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+DelivName+"\"]")).click();
        Thread.sleep(1000);

        //Associated Workpackage
        String WPName = sheet.getRow(16).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[6]")).sendKeys(WPName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+WPName+"\"]")).click();
        Thread.sleep(1000);


       //Source System
        String SysName = sheet.getRow(33).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[7]")).sendKeys(SysName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[text()=\""+SysName+"\"]")).click();
        Thread.sleep(1000);

        //Target System
        String TSysName = sheet.getRow(33).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[8]")).sendKeys(TSysName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//span[text()=\""+TSysName+"\"])[4]")).click();
        Thread.sleep(1000);


        //Source Data store
        String DataSName = sheet.getRow(46).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[9]")).sendKeys(DataSName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[text()=\""+DataSName+"\"])[1]")).click();
        Thread.sleep(1000);


        //Target Data Store
        String DataTName = sheet.getRow(46).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[10]")).sendKeys(DataTName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[text()=\""+DataTName+"\"])[4]")).click();
        Thread.sleep(1000);


        //Source Field
        String DataFName = sheet.getRow(47).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[11]")).sendKeys(DataFName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()=\""+DataFName+"\"]")).click();
        Thread.sleep(1000);

        //Target Field
        String DataF1Name = sheet.getRow(47).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[12]")).sendKeys(DataF1Name);
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[text()=\""+DataF1Name+"\"])[4]")).click();
        Thread.sleep(1000);

        //Source Data Attribute Type
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name=\"SourceDataAttributeType\"]")));
        myDynamicElement.click();
        Thread.sleep(3000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[text()='Key Field'])[1]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Target Data Attribute Type
        driver.findElement(By.xpath("//select[@name=\"TargetDataAttributeType\"]")).click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[text()='Key Field'])[2]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Source Screen Id
        driver.findElement(By.xpath("//input[@name=\"SourceScreenId\"]")).sendKeys("1");
        Thread.sleep(1000);

        //Target Screen Id
        driver.findElement(By.xpath("//input[@name=\"TargetScreenId\"]")).sendKeys("4");
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");
        Thread.sleep(1000);

        //Save DataMappingReg
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();
        Thread.sleep(1000);

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The record was successfully created.";


        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
        Thread.sleep(5000);

    }

    @Test(priority = 1)
    public void EditDataMappingReg() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Edit DMR
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"Edit\"]")));
        myDynamicElement.click();

        //Edit Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test DMR ");

        //Save
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();
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


    @Test(priority = 2)
    public void AddNewRule() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Add New Rule
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("name=\"PlatinumPMO_Data_Mapping_Registerc.PlatinumPMO_Add_New_Rule\"")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Data Mapping Rule Name
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"DMRName\"]")));
        myDynamicElement.sendKeys("Test Selenium Rule");
        Thread.sleep(1000);

        //Rule Definition
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");
        Thread.sleep(1000);

        //Save NEW RULE
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();
        Thread.sleep(1000);

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--info slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The record was saved.";


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
