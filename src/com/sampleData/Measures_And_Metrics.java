package com.sampleData;

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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Measures_And_Metrics extends LoginClass{
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Measures_And_Metrics() throws IOException {
    }

    @Test(priority = 1)
    public void CreateMeasures_And_Metrics() throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(43).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //Initiate Wait
        WebDriverWait wait = new WebDriverWait(driver, 50);

        //Click On New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
        myDynamicElement.click();
        Thread.sleep(4000);

        //Objectives
        String ObjectiveName = sheet.getRow(40).getCell(3).getStringCellValue();
        myDynamicElement= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Objectives\"]")));
        myDynamicElement.sendKeys(ObjectiveName);
        Thread.sleep(5000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+ObjectiveName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Measues And Metrics
        String MandMName = sheet.getRow(43).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@class=\" input\"]")).sendKeys(MandMName);
        Thread.sleep(1000);

        //Business Process
        String BPName = sheet.getRow(19).getCell(3).getStringCellValue();
        myDynamicElement= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Business Processes\"]")));
        myDynamicElement.sendKeys(BPName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Measure/Metrics Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Measures and Metrics \""+MandMName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

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
