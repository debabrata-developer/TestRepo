package com.testCase;


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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ProgramCreateEdit{
    public WebDriver driver;
    @BeforeTest
    public void Setup() throws IOException {
        // Read Excel File
        File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
        FileInputStream input = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(input);
        XSSFSheet sheet = wb.getSheetAt(0);
        //Get Data From Excel File
        String webpath = sheet.getRow(3).getCell(2).getStringCellValue();
        System.out.println(webpath);
        String username = sheet.getRow(1).getCell(2).getStringCellValue();
        System.out.println(username);
        String password = sheet.getRow(2).getCell(2).getStringCellValue();
        System.out.println(password);
        //Get sObject Url
        String Sobject = sheet.getRow(13).getCell(2).getStringCellValue();
        System.out.println(Sobject);



        System.setProperty("webdriver.chrome.driver", webpath);
        ChromeOptions opt = new ChromeOptions();
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("--disable-notifications");
        opt.addArguments(l1);
        driver = new ChromeDriver(opt);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com");

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.id("Login")).click();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        driver.get(Sobject);
    }

    @Test(priority = 1)
    public void CreateProgram() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"oneHeader\"]/div[3]/one-appnav/div/div/div/nav/one-app-launcher-header/button/div")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[1]/one-app-launcher-menu/div/one-app-launcher-search-bar/lightning-input/div/input")));
        myDynamicElement.sendKeys("Programs");

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[1]/one-app-launcher-menu/div/div[2]/one-app-launcher-menu-item/a/div/lightning-formatted-rich-text/span/p/b")));
        myDynamicElement.click();
        Thread.sleep(5000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/ul/li[1]/a/div")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div/div[1]/div/div/div/div/div/div[1]/div/input")));
        myDynamicElement.sendKeys("Test Selenium Organization");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a/div[2]/div[1]")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div/div[2]/div/div/div/div/div/div[1]/div/input ")).sendKeys("Test SElenium Portfolio");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[1]/div/div/div/div[2]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li/a/div[2]/div[1]")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[1]/div[1]/div/div/div/input")).sendKeys("New Selenium program");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[1]/div[2]/div/div/lightning-picklist/lightning-dual-listbox/div/div[2]/div/div[3]/div/ul/li[1]/div/span/span")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[1]/div[2]/div/div/lightning-picklist/lightning-dual-listbox/div/div[2]/div/div[4]/lightning-button-icon[1]/button")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Techcloud Developer");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[2]/div[2]/div/div/div/div/input")).click();
        driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/table/tbody/tr[4]/td[5]/span")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[3]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Techcloud Developer");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[3]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[3]/div[2]/div/div/div/div/input")).click();
        driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[1]/div[2]/a")).click();
        driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/table/tbody/tr[4]/td[4]/span")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[4]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Techcloud Developer");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[4]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[4]/div[2]/div/div/div/input")).sendKeys("5000000");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[5]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Techcloud Developer");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[5]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[6]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Debabrata Ghosh");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[6]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[5]/a")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[7]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Debabrata Ghosh");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[7]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[5]/a")).click();

        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[8]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("SI");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[9]/div/div/div/div/div/div[2]/div[1]")).sendKeys("Testing");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[3]/div/div/div[1]/div/div/div/div/div/div[2]/div[1]")).sendKeys("Program Charter Testing");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[3]/div/div/div[2]/div/div/div/div/div/div[2]/div[1]")).sendKeys("Testing");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[4]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("Test Historical Comment");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/button[3]")).click();

        String actual = "Test Historical Comment";
        String expected = driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[5]/div/div[1]/div/div[2]/div[1]/div/div/section/div[2]/div/table/tbody/tr[2]/td[3]/p")).getText();
        System.out.println(expected);
        Assert.assertEquals(actual, expected);
        System.out.println("Testcase Passed");


    }
    @Test(priority = 2)
    public void EditProgram() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[5]/div/div[1]/div/div[1]/div[1]/header/div[2]/div/div[2]/ul/li[4]/div/div[1]/div/div/a/lightning-icon/lightning-primitive-icon")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[9]/div/ul/li[3]/a")));
        myDynamicElement.click();

        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div/footer/button[1]/span")));
        myDynamicElement.click();
        Thread.sleep(3000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[2]/div/div/div[1]/div[2]/div/div/lightning-picklist/lightning-dual-listbox/div/div[2]/div/div[3]/div/ul/li[1]/div/span/span")));
        myDynamicElement.click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[2]/div/div/div[1]/div[2]/div/div/lightning-picklist/lightning-dual-listbox/div/div[2]/div/div[4]/lightning-button-icon[1]/button")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[2]/div/div/div[6]/div[2]/div/div/div/div/div/div/div/a")).click();
        driver.findElement(By.xpath("/html/body/div[10]/div/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/article/div[3]/div/div[5]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("Test Historical Comment 1");


        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[3]/div/button[3]/span")).click();
        String actual1 = "Test Historical Comment 1";
        String expected1 = driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[5]/div/div[1]/div/div[2]/div[1]/div/div/section/div[2]/div/table/tbody/tr[1]/td[3]/p")).getText();
        System.out.println(expected1);
        Assert.assertEquals(actual1, expected1);
        System.out.println("Testcase Passed");


    }

    /*@Test(priority = 3)
    public void ClickOnRoadMap(){
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[2]/div[1]/div[4]/ul/li/div/div/button/span")));
        myDynamicElement.click();
        myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[2]/div[1]/div[3]/div/div/table/tbody[1]/td[1]/div/ul/a[2]/li")));
        myDynamicElement.click();

    }*/
   /* @Test(priority = 4)
    public void AddProgramToPortfolio() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[2]/div[1]/div[4]/ul/li/div/div/button/span")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"brandBand_1\"]/div/div[1]/div[2]/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[1]/th/span/a")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"brandBand_1\"]/div/div[1]/div[4]/div/div[1]/div/div[1]/div/header/div[2]/div/div[2]/ul/li[2]/a/div")));
        myDynamicElement.click();

        Thread.sleep(3000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/lightning-input/div/input")));
        myDynamicElement.sendKeys("New Selenium Program");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[11]/div/div[2]/div[1]")).sendKeys("Testing Program");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[16]/div/lightning-dual-listbox/div/div[2]/div/div[3]/div/ul/li[1]/div/span/span")).click();

        driver.findElement(By.xpath("//*[@id=\"content_51:19953;a\"]/div/div/div/div[2]/section/div[2]/div[16]/div/lightning-dual-listbox/div/div[2]/div/div[4]/lightning-button-icon[1]/button")).click();

        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Techcloud Developer");
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[18]/div/div/input")).click();
        driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/table/tbody/tr[5]/td[3]/span")).click();

        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[3]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Techcloud Developer");
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[3]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[22]/lightning-input/div/input")).click();
        driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[1]/div[2]/a")).click();
        driver.findElement(By.xpath("/html/body/div[9]/div/div[2]/table/tbody/tr[3]/td[7]/span")).click();

        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[4]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Techcloud Developer");
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[4]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a")).click();

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[22]/lightning-input/div/input")).sendKeys("5000000");

        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[29]/div/div[2]/div[1]")).sendKeys("Test Historical comment");


        // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[5]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Techcloud Developer");
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[5]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[1]/a")).click();

       // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[6]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Debabrata Ghosh");
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[6]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[5]/a")).click();

        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[7]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("Debabrata Ghosh");
        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[7]/div[1]/div/div/div/div/div/div[1]/div/div/div[2]/ul/li[5]/a")).click();

       // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[8]/div[1]/div/div/div/div/div/div[1]/div/input")).sendKeys("SI");

       // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[9]/div/div/div/div/div/div[2]/div[1]")).sendKeys("Testing");

        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[3]/div/div/div[1]/div/div/div/div/div/div[2]/div[1]")).sendKeys("Program Charter Testing");

        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[3]/div/div/div[2]/div/div/div/div/div/div[2]/div[1]")).sendKeys("Testing");

       // driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[4]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("Test Historical Comment");

        //driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/button[3]")).click();



    }*/


}





