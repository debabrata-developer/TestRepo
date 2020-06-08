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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Data_Table extends LoginClass{
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Data_Table() throws IOException {
    }

    @Test
    public void CreateData_Table() throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(46).getCell(2).getStringCellValue();
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

        //Associated System
        String SystemName = sheet.getRow(33).getCell(3).getStringCellValue();
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search Systems\"]")));
        myDynamicElement.sendKeys(SystemName);
        Thread.sleep(5000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+SystemName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Data Table Name
        String DataTableName = sheet.getRow(46).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@class=\" input\"]")).sendKeys(DataTableName);
        Thread.sleep(1000);

        //Data Table Description
        driver.findElement(By.xpath("(//textarea[@class=\" textarea\"])[1]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Data Reliability Factor
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"select\"])[1]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"A\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Data consistency Factor
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class=\"select\"])[2]")));
        myDynamicElement.click();
        Thread.sleep(1000);
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title=\"2\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Business Domain Expert
        String userName = sheet.getRow(37).getCell(4).getStringCellValue();
        myDynamicElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title=\"Search People\"]")));
        myDynamicElement.sendKeys(userName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Historical Commnet
        driver.findElement(By.xpath("(//textarea[@class=\" textarea\"])[2]")).sendKeys("Test Historical Commnet");
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Data Table \""+DataTableName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);
        Thread.sleep(5000);
    }
}
