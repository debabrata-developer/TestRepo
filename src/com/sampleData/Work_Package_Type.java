package com.sampleData;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Work_Package_Type extends LoginClass{
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Work_Package_Type() throws IOException {
    }

    @Test(priority = 1)
    public void CreateWPType()throws InterruptedException{
        //get sObject URL
        String sObject = sheet.getRow(45).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //Initiate Wait
        WebDriverWait wait = new WebDriverWait(driver, 50);

        //Click On New Button
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
        String DeliTypeName = sheet.getRow(44).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Deliverable Types\"]")).sendKeys(DeliTypeName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+DeliTypeName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Name Of Work Package Types
        String WPTypeName = sheet.getRow(45).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@class=\" input\"]")).sendKeys(WPTypeName);
        Thread.sleep(1000);

        //Varient Name
        driver.findElement(By.xpath("(//input[@class=\" input\"])[2]")).sendKeys("Test Varient");
        Thread.sleep(1000);

        //Work Package Template
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Test Template");
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Test Historical Comment");
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Work Package Type \""+WPTypeName+"\" was created.";

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



