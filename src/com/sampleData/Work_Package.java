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

public class Work_Package extends LoginClass{
    // Read Excel File
    File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
    FileInputStream input = new FileInputStream(src);
    XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet sheet = workbook.getSheetAt(0);

    public Work_Package() throws IOException {
    }

    @Test(priority = 0)
    public void CreateWorkpackage() throws InterruptedException {
        //get sObject URL
        String sObject = sheet.getRow(16).getCell(2).getStringCellValue();
        System.out.println(sObject);

        //redirect to sObject
        driver.get(sObject);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        //Initiate Wait
        WebDriverWait wait = new WebDriverWait(driver, 50);

        //Create Workpackage
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\" or @role=\"button\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);

        //Click Program
        String ProgName = sheet.getRow(13).getCell(3).getStringCellValue();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder=\"search..\"])[1]")));
        myDynamicElement.sendKeys(ProgName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\" and text()='"+ProgName+"']")));
        myDynamicElement.click();
        Thread.sleep(1000);


        //Click project
        String ProjName = sheet.getRow(14).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@placeholder=\"search..\"])[2]")).sendKeys(ProjName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+ProjName+"']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //Deliverable
        String DelijName = sheet.getRow(15).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@class=\"slds-lookup__search-input slds-input leftPaddingClass input uiInput uiInputText uiInput--default uiInput--input\"])[3]")).sendKeys(DelijName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+DelijName+"']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //WP Name
        String WPName = sheet.getRow(16).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@name=\"WpName\"]")).sendKeys(WPName);
        Thread.sleep(1000);

        //URL Name
        driver.findElement(By.xpath("//input[@name=\"liveURL\"]")).sendKeys("salesforce.in");
        Thread.sleep(1000);

        //WP Type
        String WPTypeName = sheet.getRow(45).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("(//input[@type=\"text\"])[8]")).sendKeys(WPTypeName);
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"slds-listbox__option-text slds-listbox__option-text_entity\" and text()='"+WPTypeName+"']")));
        myDynamicElement.click();
        Thread.sleep(1000);

        //WP Phase
        driver.findElement(By.xpath("//select[@class=\"slds-select\" and @name=\"wpPhase\"]")).sendKeys("ASAP: 1-Preparation");
        Thread.sleep(1000);

        //Estimated Effort
        driver.findElement(By.xpath("//input[@inputmode=\"decimal\"]")).sendKeys("4 hours");
        Thread.sleep(1000);

        //WP Narrative
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");
        Thread.sleep(1000);

        //Work Package Narrative Overflow:
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("ok");
        Thread.sleep(1000);

        //Historical Comment
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("test");
        Thread.sleep(1000);

        //Save
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand\"]")).click();
        Thread.sleep(1000);

        //get Toast Message
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"toastMessage forceActionsText\"]")));
        String ToastMessage = myDynamicElement.getAttribute("innerHTML");

        //Expected Toast Message Value Set
        String ExpectedValue = "The record was saved.";

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
