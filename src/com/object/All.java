package com.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class All {
    WebDriver driver;

    public void ClickOnAppLauncher(CharSequence obj,WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"oneHeader\"]/div[3]/one-appnav/div/div/div/nav/one-app-launcher-header/button/div")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/div[1]/div[1]/one-app-launcher-menu/div/one-app-launcher-search-bar/lightning-input/div/input")));
        myDynamicElement.sendKeys(obj);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span/p/b[text()='"+ obj +"']")));
        myDynamicElement.click();
    }

    public void CreateObjectAll(WebDriver driver) throws InterruptedException {
        driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement;
        ClickOnAppLauncher("Organization",driver);
        //-----------

        //Create Organization
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/ul/li[1]/a/div")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div[1]/div[1]/div/div/div/input")));
        myDynamicElement.sendKeys("Test Selenium Org");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div/div[1]/div/div/div/input")).sendKeys("3");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div/div[2]/div/div/div/input")).sendKeys("6");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[3]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("test Historical comment");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/button[3]/span")).click();
        //--------------

        //Create Portfolio From Organization
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Add Portfolio\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"Portfolio Name\"]")));
        myDynamicElement.sendKeys("Test Selenium Portfolio");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[9]/div/div/div/div[2]/input")).click();
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Selenium");
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand slds-button\"]")).click();
        //--------------

        //Create Program From Portfolio
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Add Program\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"Program Name\"]")));
        myDynamicElement.sendKeys("Test Selenium Program");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[11]/div/div[2]/div[1]")).sendKeys("Test Desccription");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[26]/div/div/div/select")).sendKeys("Red");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[29]/div/div[2]/div[1]")).sendKeys("Test Selenium");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[32]/button[1]")).click();
        //--------------

        //Create Project From Program
        /*myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Add Project\"]")));
        myDynamicElement.click();
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"Project Name\"]")));
        myDynamicElement.sendKeys("Test Selenium Project");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[18]/div/div[2]/div[1]")).sendKeys("Test Desccription");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[26]/div/div[2]/div[1]")).sendKeys("Test Selenium Historical");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[29]/button[1]")).click();*/
        //--------------



    }
}
