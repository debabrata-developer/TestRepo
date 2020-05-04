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
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PortfolioCreateEdit {

    public WebDriver driver;

    @BeforeTest
    public void Setup() throws IOException {
        // Read Excel File
        File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
        FileInputStream input = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(input);
        XSSFSheet sheet = wb.getSheetAt(0);
        //Get Data From Excel File
        String webpath = sheet.getRow(3).getCell(2).getStringCellValue();
        System.out.println(webpath);
        String username = sheet.getRow(1).getCell(2).getStringCellValue();
        System.out.println(username);
        String password = sheet.getRow(2).getCell(2).getStringCellValue();
        System.out.println(password);
        //Get sObject Url
        String Sobject = sheet.getRow(12).getCell(2).getStringCellValue();
        System.out.println(Sobject);


        System.setProperty("webdriver.chrome.driver", webpath);
        ChromeOptions opt = new ChromeOptions();
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("--disable-notifications");
        opt.addArguments(l1);
        driver = new ChromeDriver(opt);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com");

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.id("Login")).click();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        driver.get(Sobject);
    }

    @Test(priority = 1)
    public void clickOnRoadMap(){

        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[2]/div[1]/div[4]/ul/li/div/div/button/span")));
        myDynamicElement.click();
        myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[2]/div[1]/div[3]/div/div/table/tbody[1]/td[1]/div/ul/a[2]/li")));
        myDynamicElement.click();

        // myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[1]/one-app-launcher-menu/div/one-app-launcher-search-bar/lightning-input/div/input")));
        //myDynamicElement.sendKeys("Portfolios");

        // myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[1]/one-app-launcher-menu/div/div[2]/one-app-launcher-menu-item[1]/a/div/lightning-formatted-rich-text/span/p/b")));
        //myDynamicElement.click();
    }
    @Test(priority = 2)
    public void CreatePortfolio(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/ul/li[1]/a")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[1]/div[1]/div/div/div/div/div/div[1]/div/input")));
        myDynamicElement.sendKeys("Selenium Org Test");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[1]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li/a")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[2]/div[1]/div/div/div/input")).sendKeys("Selenium Portfolio Test");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[2]/div[2]/div/div/div/div/div/div[1]/div/input")).sendKeys("Techcloud Developer");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[2]/div[2]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("Test Historical Comment");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/button[3]")).click();


    }
    @Test(priority = 3)
    public void EditPortfolio() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[3]/div/div[1]/div/div[1]/div/header/div[2]/div/div[2]/ul/li[3]/a")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[1]/div/div/div[2]/div[1]/div/div/div/input")));
        myDynamicElement.clear();
        myDynamicElement.sendKeys("Test Selenium Portfolio");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[3]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("Historical Comment Test");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[3]/div/button[3]")).click();
        Thread.sleep(5000);
    }
    @Test(priority = 4)
    public void ClickOnAppLauncher(){
        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"oneHeader\"]/div[3]/one-appnav/div/div/div/nav/one-app-launcher-header/button/div")));

        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[1]/one-app-launcher-menu/div/lightning-button/button")));

        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/one-app-launcher-modal/div/div[1]/div/div/one-app-launcher-search-bar/lightning-input/div/input")));
        myDynamicElement.sendKeys("Org");

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/one-app-launcher-modal/div/div[2]/lightning-accordion/slot/lightning-accordion-section[2]/section/div[2]/slot/ul/li[2]/one-app-launcher-tab-item/a/span/lightning-formatted-rich-text/span/p")));
        myDynamicElement.click();

    }
    @Test(priority = 5)
    public void CreateOrganization(){
        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/ul/li[1]/a/div")));

        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[1]/div[1]/div/div/div/input")));
        myDynamicElement.sendKeys("Test Selenium Organization");

        driver.findElement(By.xpath(" /html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div/div[1]/div/div/div/input")).sendKeys("4");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div/div[2]/div/div/div/input")).sendKeys("6");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[3]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("Test Historical Comment");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/button[3]/span")).click();

        String Text = "Test Historical Comment";
        String Text2 = driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[3]/div/div[1]/div/div[2]/div[1]/div/div/section/div[2]/div/table/tbody/tr/td[3]/p")).getText();
        System.out.println(Text2);
        Assert.assertEquals(Text, Text2);
        System.out.println("Testcase Passed");

    }
    @Test(priority = 6)
    public void AddPortfolio() throws InterruptedException {


        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[3]/div/div[1]/div/div[1]/div/header/div[2]/div/div[2]/ul/li[2]/a/div")));

        myDynamicElement.click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/lightning-input/div/input")).sendKeys("Test Selenium Portfolio");
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[9]/div/div/div/div[2]/input")).sendKeys("Techcloud Developer");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[14]/div/div[2]/div[1]")).sendKeys("Test Historical Comment");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[17]/button[1]")).click();
        Thread.sleep(3000);

        String ac = "Test Historical Comment";
        String ex = driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[4]/div/div[1]/div/div[2]/div[1]/div/div/section/div[2]/div/table/tbody/tr/td[3]/p")).getText();
        System.out.println(ex);
        Assert.assertEquals(ac, ex);
        System.out.println("Testcase Passed");
    }

    @AfterTest
    public void terminateBrowser(){

        driver.close();
    }



}
