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
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Objectives extends LoginClass{
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Objectives() throws IOException {
    }

    @Test(priority = 1)
    public void CreateObjectives() throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(40).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

        //Initiate Wait
        WebDriverWait wait = new WebDriverWait(driver, 50);

        //Click On New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
        myDynamicElement.click();
        Thread.sleep(4000);

        //Set Goals
        String GoalName = sheet.getRow(41).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Search Goals...\"]")));
        myDynamicElement.sendKeys(GoalName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+GoalName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Objectives Name
        String ObjectiveName = sheet.getRow(40).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@class=\" input\"]")).sendKeys(ObjectiveName);
        Thread.sleep(1000);

        //Objective Description
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])")).sendKeys("Test Description");
        Thread.sleep(1000);

        //HistoricalComment
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])")).sendKeys("Test Historical comment");
        Thread.sleep(1000);

        //Click Save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Objectives \""+ObjectiveName+"\" was created.";

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

