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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class System_obj extends LoginClass{
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public System_obj() throws IOException, IOException {
    }

    @Test(priority = 0)
    public void CreateSystem() throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(33).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //Initiate Wait
        WebDriverWait wait = new WebDriverWait(driver, 50);

        //click New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Associated Organization
        String OrgName = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+OrgName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated portfolio
        String PortName = sheet.getRow(12).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Portfolios\"]")).sendKeys(PortName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+PortName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated Program
        String ProgName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Programs\"]")).sendKeys(ProgName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+ProgName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //System Name
        String SystemName = sheet.getRow(33).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@class=\" input\" and @maxlength=\"80\"]")).sendKeys(SystemName);
        Thread.sleep(1000);

        //System Type
        driver.findElement(By.xpath("(//a[@class=\"select\" and @role=\"button\"])[1]")).sendKeys("Future System");
        Thread.sleep(1000);

        //System Disposition
        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).sendKeys("Continue after go live");
        Thread.sleep(1000);

        //Description
        driver.findElement(By.xpath("(//textarea[@class=\" textarea\"])[1]")).sendKeys("New System Created");
        Thread.sleep(1000);

        //Decommission?
        driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
        Thread.sleep(1000);

        //Date
        driver.findElement(By.xpath("//a[@class=\"datePicker-openIcon display\"]")).click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='15']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Time
        driver.findElement(By.xpath("//a[@class=\"timePicker-openIcon display\"]")).click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-hours=\"00\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("(//textarea[@class=\" textarea\"])[2]")).sendKeys("test");
        Thread.sleep(1000);

        //Save System
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "System \""+SystemName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage, ExpectedValue);

        Thread.sleep(5000);

    }

    @AfterMethod
    public void afterMethod(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP)
        {
            System.out.println("result Fail--"+result.getStatus());
            driver.quit();
        }
    }
}
