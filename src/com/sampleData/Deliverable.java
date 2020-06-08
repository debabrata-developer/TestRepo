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

public class Deliverable extends LoginClass{
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Deliverable() throws IOException {
    }

    @Test(priority = 1)
    public void CreateDeliverable() throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(15).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //Initiate Wait
        WebDriverWait wait = new WebDriverWait(driver, 50);

        //click New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" or @data-aura-rendered-by=\"659:0\"]")));
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

        //Associated Project
        String ProjName = sheet.getRow(14).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Projects\"]")).sendKeys(ProjName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+ProjName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Deliverable Name
        String DelivName = sheet.getRow(15).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//div/input[@type=\"text\" and @maxlength=\"80\"]")).sendKeys(DelivName);
        Thread.sleep(1000);

        //Deliverable Description
        driver.findElement(By.xpath("(//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"])[1]")).sendKeys(" Des");
        Thread.sleep(1000);

        //Deliverable Type
        String DelivTypeName = sheet.getRow(44).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//div/input[@title=\"Search Deliverable Types\" and @placeholder=\"Search Deliverable Types...\"]")).sendKeys(DelivTypeName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@title=\""+DelivTypeName+"\"]")).click();
        Thread.sleep(1000);

        //Associted Object
        String ObjName = sheet.getRow(31).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//div/input[@title=\"Search Objects\" and @role=\"combobox\"]")).sendKeys(ObjName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@title=\""+ObjName+"\"]")).click();
        Thread.sleep(1000);

        //Associted BP
        String BPName = sheet.getRow(19).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//div/input[@title=\"Search Business Processes\" and @role=\"combobox\"]")).sendKeys(BPName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@title=\""+BPName+"\"]")).click();
        Thread.sleep(1000);

        //Measures & Metrics
        String MandMName = sheet.getRow(43).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//div/input[@placeholder=\"Search Measures and Metrics...\" and @title=\"Search Measures and Metrics\"]")).sendKeys(MandMName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@title=\""+MandMName+"\"]")).click();
        Thread.sleep(1000);

        //Capital or Expense
        driver.findElement(By.xpath("(//a[@class=\"select\"])[2]")).sendKeys("Capital");
        Thread.sleep(1000);

        //Scope Lever
        driver.findElement(By.xpath("(//a[@class=\"select\"])[3]")).sendKeys("Form");
        Thread.sleep(1000);

        //Associated Project Team/Stakeholder Grp
        String StakeName = sheet.getRow(42).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//div/input[@title=\"Search Stakeholder Groups\"]")).sendKeys(StakeName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@title=\""+StakeName+"\"]")).click();
        Thread.sleep(1000);

        //Deliverable Narrative
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");
        Thread.sleep(1000);

        //Save Deliverable
        driver.findElement(By.xpath("//div//button[@title=\"Save\"]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Deliverable \""+DelivName+"\" was created.";

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
