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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Data_Field extends LoginClass{
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Data_Field() throws IOException {
    }

    @Test
    public void CreateData_Field() throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(47).getCell(2).getStringCellValue();
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

        //Data Table
        String DataTableName = sheet.getRow(46).getCell(3).getStringCellValue();
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Data Tables\"]")));
        myDynamicElement.sendKeys(DataTableName);
        Thread.sleep(5000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+DataTableName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Data Table Description
        driver.findElement(By.xpath("(//textarea[@class=\" textarea\"])[1]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Data Field Name
        String DataFieldName = sheet.getRow(47).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@class=\" input\"])[1]")).sendKeys(DataFieldName);
        Thread.sleep(1000);

        //Field Description
        driver.findElement(By.xpath("(//textarea[@class=\" textarea\"])[2]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Screen ID
        driver.findElement(By.xpath("(//input[@class=\" input\"])[2]")).sendKeys("1");
        Thread.sleep(1000);

        //Segment Name
        driver.findElement(By.xpath("(//input[@class=\" input\"])[3]")).sendKeys("Data Field Segment");
        Thread.sleep(1000);

        //Data Type
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"select\"])[1]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Alpha\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Field Length
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("10");
        Thread.sleep(1000);

        //Associated Object
        String ObjName = sheet.getRow(31).getCell(3).getStringCellValue();
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Objects\"]")));
        myDynamicElement.sendKeys(ObjName);
        Thread.sleep(5000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+ObjName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Attribute Type
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"select\"])[2]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"Key Field\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("(//textarea[@class=\" textarea\"])[3]")).sendKeys("Test Historical Comment");
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Data Field \""+DataFieldName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);
        Thread.sleep(5000);

    }
}
