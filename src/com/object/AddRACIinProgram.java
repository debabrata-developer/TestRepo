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

public class AddRACIinProgram {
    static WebDriver driver;
    private static final String userName = "techcloud@platinumpmo.com";
    private static final String password = "tech@1234567$";

    public void openBrowserOnChrome(){
        System.setProperty("webdriver.chrome.driver","E:\\automation testing\\Jre\\Automation\\chromedriver_win32_80_0_3987_106\\chromedriver.exe");
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
    }

    public void loginIntoSalesforce(){
        WebElement userNameElement = driver.findElement(By.id("username"));
        WebElement passwordElement = driver.findElement(By.id("password"));
        userNameElement.sendKeys(userName);
        passwordElement.sendKeys(password);
        driver.findElement(By.id("Login")).click();
    }

    public void ClickOnAppLauncher(CharSequence obj){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"oneHeader\"]/div[3]/one-appnav/div/div/div/nav/one-app-launcher-header/button/div")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div[1]/div[1]/div[1]/one-app-launcher-menu/div/one-app-launcher-search-bar/lightning-input/div/input")));
        myDynamicElement.sendKeys(obj);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span/p/b[text()='"+ obj +"']")));
        myDynamicElement.click();
    }

    public void AddRaci() throws InterruptedException {
        //Initialize
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement;
        ClickOnAppLauncher("Programs");
        //-----------

        //Create Program
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"New\"]")));
        myDynamicElement.click();
        Thread.sleep(8000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/input[@title=\"Search Organization\"]")));
        myDynamicElement.sendKeys("Platinum PMO");
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Platinum PMO\"]")));
        myDynamicElement.click();
        driver.findElement(By.xpath("//div/input[@title=\"Search Portfolios\"]")).sendKeys("Platinum PMO Application Platform");
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[@title=\"Platinum PMO Application Platform\"]")));
        myDynamicElement.click();
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[2]/div/div/div[1]/div[1]/div/div/div/input")).sendKeys("Test Selenium Program");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/article/div[3]/div/div[4]/div/div/div/div/div/div/div/div/div[2]/div[1]")).sendKeys("Selenium Historical");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/button[3]/span")).click();
        Thread.sleep(6000);
        //--------------

        //Add RACI to Program
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a/div[@title=\"RACI Chart\"]")));
        myDynamicElement.click();
        Thread.sleep(6000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name=\"SourceDataType\"]")));
        myDynamicElement.sendKeys("Responsible");
        driver.findElement(By.xpath("//div/div/div/input[@placeholder=\"search..\"]")).sendKeys("Debasish Addy");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()=\"Debasish Addy\"]")).click();
        driver.findElement(By.xpath("//div/textarea[@name=\"HistoricalComment\"]")).sendKeys("Add RACI User");
        driver.findElement(By.xpath("//div/button[text()=\"Save\"]")).click();

        Thread.sleep(7000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a/div[@title=\"RACI Chart\"]")));
        myDynamicElement.click();
        Thread.sleep(6000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name=\"SourceDataType\"]")));
        myDynamicElement.sendKeys("Accountable");
        driver.findElement(By.xpath("//div/div/div/input[@placeholder=\"search..\"]")).sendKeys("Shoubhik Maity");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()=\"Shoubhik Maity\"]")).click();
        driver.findElement(By.xpath("//div/textarea[@name=\"HistoricalComment\"]")).sendKeys("Add RACI User");
        driver.findElement(By.xpath("//div/button[text()=\"Save\"]")).click();

        Thread.sleep(7000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a/div[@title=\"RACI Chart\"]")));
        myDynamicElement.click();
        Thread.sleep(6000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name=\"SourceDataType\"]")));
        myDynamicElement.sendKeys("Consulted");
        driver.findElement(By.xpath("//div/div/div/input[@placeholder=\"search..\"]")).sendKeys("Rajdeep Chakraborty");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()=\"Rajdeep Chakraborty\"]")).click();
        driver.findElement(By.xpath("//div/textarea[@name=\"HistoricalComment\"]")).sendKeys("Add RACI User");
        driver.findElement(By.xpath("//div/button[text()=\"Save\"]")).click();


        //-------------------
    }
}
