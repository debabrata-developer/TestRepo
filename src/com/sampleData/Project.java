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

public class Project extends LoginClass{
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Project() throws IOException {
    }

    @Test(priority = 1)
    public void CreateNewProject () throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(14).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //Initiate Wait
        WebDriverWait wait = new WebDriverWait(driver, 50);

        //Clicking on New Button
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a/div[@title=\"New\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Clicking on Associated Organization
        String OrgName = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Search Organization...\"]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@title=\""+OrgName+"\"]")).click();
        Thread.sleep(1000);

        //clicking on Associated Portfolio
        String PortName = sheet.getRow(12).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@placeholder=\"Search Portfolios...\"]")).sendKeys(PortName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@title=\""+PortName+"\"]")).click();
        Thread.sleep(1000);

        //clicking on associated program
        String ProgramName = sheet.getRow(13).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@placeholder=\"Search Programs...\"]")).sendKeys(ProgramName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\""+ProgramName+"\"]")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Name of Project
        String ProjName = sheet.getRow(14).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@class=\" input\"])[1]")).sendKeys(ProjName);
        Thread.sleep(1000);

        //project description
        driver.findElement(By.xpath("//textarea[@class=\" textarea\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //target start date
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()='14']")).click();
        Thread.sleep(1000);

        //target completion date
        driver.findElement(By.xpath("(//a[@class=\"datePicker-openIcon display\"])[2]")).click();
        driver.findElement(By.xpath("//a[@title=\"Go to next month\"]")).click();
        driver.findElement(By.xpath("//span[text()='20']")).click();
        Thread.sleep(1000);

        //Budget
        driver.findElement(By.xpath("//input[@class=\"input uiInputSmartNumber\"]")).sendKeys("5000000");
        Thread.sleep(1000);

        //capital
        //driver.findElement(By.xpath("(//a[@class=\"select\"and@role=\"button\"])[5]")).click();
        //driver.findElement(By.xpath("//a[@title=\"Capital\"]")).click();
        //Thread.sleep(2000);

        //timesheet approver
        String UserName = sheet.getRow(37).getCell(4).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[3]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //project condition
        //driver.findElement(By.xpath("(//a[@class=\"select\"])[3]")).click();
        //driver.findElement(By.xpath("//a[@title=\"Yellow\"]")).click();
        //Thread.sleep(1000);

        //project manager
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[1]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Business Process Manager
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[2]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Solution Integrator
        String SolutionName = sheet.getRow(39).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@title=\"Search Solution Integrator\"]")).sendKeys(SolutionName);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@title=\""+SolutionName+"\"]")).click();
        Thread.sleep(1000);

        //Expense Approver
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@title=\"Search People\"])[4]")));
        myDynamicElement.sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        myDynamicElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //Project Charter
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Project Charter Overflow
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Testing");
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Historical Comment");
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("//button[@title=\"Save\"]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Project \""+ProjName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(5000);


    }
}

