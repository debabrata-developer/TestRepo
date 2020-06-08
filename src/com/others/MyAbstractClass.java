package com.others;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MyAbstractClass
{
    public static WebDriver driver;

    @BeforeSuite
    public void beforeSuite() throws IOException {
        //...
        File src = new File("C:\\AMIGO Selenium Excel Sheet.xlsx");
        FileInputStream input = new FileInputStream(src);
        XSSFWorkbook workbook = new XSSFWorkbook(input);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String webDriverPath = sheet.getRow(3).getCell(2).getStringCellValue();
        System.setProperty("webdriver.chrome.driver", webDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        //all other arguments(if need then add)
        //options.addArguments("--always-authorize-plugins"); options.addArguments("--no-sandbox"); options.addArguments("--disable-dev-shm-usage"); options.addArguments("--aggressive-cache-discard"); options.addArguments("--disable-cache"); options.addArguments("--disable-application-cache"); options.addArguments("--disable-offline-load-stale-cache"); options.addArguments("--disk-cache-size=0"); options.addArguments("--headless"); options.addArguments("--disable-gpu"); options.addArguments("--dns-prefetch-disable"); options.addArguments("--no-proxy-server"); options.addArguments("--log-level=3"); options.addArguments("--silent"); options.addArguments("--disable-browser-side-navigation"); options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        this.driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //this.driver = new ChromeDriver();
        //...
    }

    @AfterSuite
    public void afterSuite() {
        //...
        //this.driver.quit();
        //...
    }
}
