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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Organization extends LoginClass{

    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Organization() throws IOException {
    }

    @Test(priority = 1)
    public void CreateOrganization() throws InterruptedException {

        //get sObject URL
        String sObject = sheet.getRow(11).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);

        //Initiate Wait
        WebDriverWait wait = new WebDriverWait(driver, 50);

        //click New Button
        WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" and text()='New']")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Organization Name
        String OrgName = sheet.getRow(11).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@class=\" input\" and @type=\"text\"]")));
        myDynamicElement.sendKeys(OrgName);
        Thread.sleep(1000);

        //Timesheet Auto Approval Days
        driver.findElement(By.xpath("(//*[@data-aura-class=\"uiInputSmartNumber\"])[1]")).sendKeys("7");
        Thread.sleep(1000);

        //Expense Auto Approval Days
        driver.findElement(By.xpath("(//*[@data-aura-class=\"uiInputSmartNumber\"])[2]")).sendKeys("9");
        Thread.sleep(1000);

        //Historical Comments
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\" ]")).sendKeys("Selenium Test Historical Comment");
        Thread.sleep(1000);

        //Click Save Button
        driver.findElement(By.xpath("//button[@title=\"Save\" ]//span[text()='Save']")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "Organization \""+OrgName+"\" was created.";

        //Check
        Assert.assertEquals(ToastMessage,ExpectedValue);

        Thread.sleep(6000);
    }

    @Test(priority = 2)
    public void InviteUser() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //Click Invite User
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name=\"PlatinumPMO__Organization__c.PlatinumPMO__Add_User\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //User
        String UserName = sheet.getRow(37).getCell(4).getStringCellValue();
        driver.findElement(By.xpath("//input[@placeholder=\"search..\"]")).sendKeys(UserName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+UserName+"']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();
        Thread.sleep(1000);

        //Get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //checking Toast Message Value Set
        String Chechval = "The Record was Saved";

        //Check
        Assert.assertTrue(ToastMessage.contains(Chechval));
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


