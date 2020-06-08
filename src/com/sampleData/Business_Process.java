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

public class Business_Process extends LoginClass{

    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Business_Process() throws IOException, IOException {
    }

    @Test(priority = 0)
    public void CreateBusinessProcess() throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(19).getCell(2).getStringCellValue();
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
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder=\"search..\"])[1]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+OrgName+"']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated portfolio
        String PortName = sheet.getRow(12).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[2]")).sendKeys(PortName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+PortName+"']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Associated Program
        String progName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[3]")).sendKeys(progName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+progName+"']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Business Process Name
        String BusiName = sheet.getRow(19).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//div/input[@type=\"text\" and @maxlength=\"80\"]")).sendKeys(BusiName);
        Thread.sleep(1000);

        //Business prefix
        driver.findElement(By.xpath("//select[@name=\"BpPrefix\"]")).click();
        Thread.sleep(1000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//option[@value=\"LTC: Lead to Cash\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //BP Level
        driver.findElement(By.xpath("//input[@name=\"BpLevel\"]")).sendKeys("1");
        Thread.sleep(1000);

        //Parent Process UID
        //driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[4]")).sendKeys("");
        //myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"tyr6t\"]")));
        //myDynamicElement.click();

        //BP Description
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");
        Thread.sleep(1000);

        //Save BP
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "The record was successfully created.";

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
