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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ProjectCreateEdit {
    public WebDriver driver;

    @BeforeTest
    public void Setup() throws IOException, InterruptedException {
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
        String Sobject = sheet.getRow(14).getCell(2).getStringCellValue();
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
    public void CreateNewProject () throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //Clicking on New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a/div[@title=\"New\"]")));
        myDynamicElement.click();
        //Clicking on Associated Organization
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Search Organization...\"]")));
        myDynamicElement.sendKeys("Extron Organization");
       Thread.sleep(2000);
        //clicking  on Extron organization
        driver.findElement(By.xpath("//div[@title=\"Extron Organization\"]")).click();
        Thread.sleep(2000);
        //clicking on Associated Portfolio
        driver.findElement(By.xpath("//input[@placeholder=\"Search Portfolios...\"]")).sendKeys("Extron Portfolio");
        Thread.sleep(2000);
        //clicking on extron portfolio
        driver.findElement(By.xpath("//div[@title=\"Extron Portfolio\"]")).click();
        //clicking on associated program
        driver.findElement(By.xpath("//input[@placeholder=\"Search Programs...\"]")).sendKeys("Extron Program");
        driver.findElement(By.xpath("//div[@title=\"Extron Program\"]")).click();
        //Merge Current Status
        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).click();
        driver.findElement(By.xpath("//a[@title=\"Initialize\"]")).click();
      Thread.sleep(1000);
        //Name of Project
        driver.findElement(By.xpath("(//input[@class=\" input\"])[1]")).sendKeys("New Selenium Project");
        //project description
        driver.findElement(By.xpath("//textarea[@class=\" textarea\"]")).sendKeys("Testing");
        //target start date
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[1]")).click();
        //select date
        driver.findElement(By.xpath("//span[text()='14']")).click();
        //target completion date
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[2]")).click();
        //arrow of datepicker
        driver.findElement(By.xpath("//a[@title=\"Go to next month\"]")).click();
        //select date
        driver.findElement(By.xpath("//span[text()='20']")).click();
        Thread.sleep(1000);
        //Budget
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("5000000");
        //capital
        driver.findElement(By.xpath("(//a[@class=\"select\"and@role=\"button\"])[5]")).click();
        driver.findElement(By.xpath("//a[@title=\"Capital\"]")).click();
       Thread.sleep(2000);
        //timesheet approver
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[3]")).sendKeys("Techcloud Developer");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@title=\"Techcloud Developer\"]")).click();
        Thread.sleep(2000);
        //project condition
        driver.findElement(By.xpath("(//a[@class=\"select\"])[3]")).click();
        driver.findElement(By.xpath("//a[@title=\"Yellow\"]")).click();
        //project manager
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[1]")).sendKeys("Techcloud Developer");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@title=\"Techcloud Developer\"]")).click();
        //Business Process Manager
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[2]")).sendKeys("Techcloud Developer");
       Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@title=\"Techcloud Developer\"])[2]")).click();
        Thread.sleep(2000);
        //Solution Integrator
        driver.findElement(By.xpath("//input[@title=\"Search Solution Integrator\"]")).sendKeys("Extron Solution Integrator");
       Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@title=\"Extron Solution Integrator\"]")).click();
        //Expense Approver
        driver.findElement(By.xpath("(//input[@title=\"Search People\"])[4]")).sendKeys("Techcloud Developer");
       Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@title=\"Techcloud Developer\"])[4]")).click();
        //Project Charter
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");

        //Project Charter Overflow
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Project \"New Selenium Project\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);


    }

    /*@Test(priority = 2)
    public void EditProject () throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //drop down to edit
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class=\"slds-grid slds-grid--vertical-align-center slds-grid--align-center sldsButtonHeightFix\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        // edit
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Edit\"]")));
        myDynamicElement.click();

        //edit popup
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div/footer/button[1]")));
        myDynamicElement.click();

        //Name of Project
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[2]/div/div/div[1]/div/div/div/div/input")));
        myDynamicElement.clear();
        myDynamicElement.sendKeys("Test Selenium Project");

        //project phase
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[2]/div/div/div[4]/div[2]/div/div/div/div/div[1]/div/div/a")).click();
        driver.findElement(By.xpath("//a[@title=\"ASAP: 1- Preparation\"]")).click();

        //Historical Comment
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[5]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("Test Historical Comment for Project");

        //save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();


    }*/



}










