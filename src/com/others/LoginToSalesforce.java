package com.others;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LoginToSalesforce extends AddRACIinProgram {

    static WebDriver driver;
    private static final String userName = "techcloud@platinumpmo.com";
    private static final String password = "tech@1234567$";

    public void openBrowserOnChrome(){
        System.setProperty("webdriver.chrome.driver","E:\\automation testing\\Jre\\Automation\\chromedriver_win32_80_0_3987_106\\chromedriver.exe");
        ChromeOptions opt = new ChromeOptions();
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("--disable-notifications");
        opt.addArguments(l1);
        driver = new ChromeDriver(opt);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://login.salesforce.com");
    }

    public void openBrowserOnFirefox(){
        System.setProperty("webdriver.gecko.driver","E:\\automation testing\\Jre\\Automation\\geckodriver-v0.26.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
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

    public void clickOnRoadMap(CharSequence obj){
        WebElement myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"brandBand_1\"]/div/div[2]/div[1]/div[4]/ul/li[1]/div/div/button/span")));
        myDynamicElement.click();
        myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul/a/li[contains(text(),'"+ obj +"')]")));
        myDynamicElement.click();
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

    public void AllWork() throws InterruptedException {
        //Initialize
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement myDynamicElement;
        ClickOnAppLauncher("Organization");
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
        Thread.sleep(6000);
        String Organizationurl = driver.getCurrentUrl();
        //--------------

        //Create Portfolio From Organization
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Add Portfolio\"]")));
        myDynamicElement.click();
        Thread.sleep(8000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"Portfolio Name\"]")));
        myDynamicElement.sendKeys("Test Selenium Portfolio");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[9]/div/div/div/div[2]/input")).click();
        driver.findElement(By.xpath("//div[@class=\"ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow\"]")).sendKeys("Test Selenium");
        driver.findElement(By.xpath("//button[@class=\"slds-button slds-button_brand slds-button\"]")).click();
        Thread.sleep(6000);
        String Portfoliourl = driver.getCurrentUrl();
        //--------------

        //Create Program From Portfolio
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@title=\"Add Program\"]")));
        myDynamicElement.click();
        Thread.sleep(8000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"Program Name\"]")));
        myDynamicElement.sendKeys("Test Selenium Program");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[11]/div/div[2]/div[1]")).sendKeys("Test Desccription");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[26]/div/div/div/select")).sendKeys("Red");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[29]/div/div[2]/div[1]")).sendKeys("Test Selenium");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[2]/div/div/div/div[2]/section/div[2]/div[32]/button[1]")).click();
        Thread.sleep(6000);
        String Programurl = driver.getCurrentUrl();
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

        //Delete Project

        //------------

        //Delete Program
        driver.get(Programurl);
        Thread.sleep(6000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[1]/div/div[1]/div/div[1]/div[1]/header/div[2]/div/div[2]/ul/li[4]/div/div[1]/div/div/a/lightning-icon/lightning-primitive-icon")));////*[@id="brandBand_1"]/div/div[1]/div[5]/div/div[1]/div/div[1]/div[1]/header/div[2]/div/div[2]/ul/li[4]
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[7]/div/ul/li[4]")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[3]/div/button[2]/span")));
        myDynamicElement.click();
        Thread.sleep(5000);
        //------------

        //Delete Portfolio
        driver.get(Portfoliourl);
        Thread.sleep(6000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[1]/div/div[1]/div/div[1]/div[1]/header/div[2]/div/div[2]/ul/li[4]/div/div[1]/div/div/a/lightning-icon/lightning-primitive-icon")));////*[@id="brandBand_1"]/div/div[1]/div[5]/div/div[1]/div/div[1]/div[1]/header/div[2]/div/div[2]/ul/li[4]
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[7]/div/ul/li")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[3]/div/button[2]/span")));
        myDynamicElement.click();
        Thread.sleep(5000);
        //------------------------

        //Delete Organization
        driver.get(Organizationurl);
        Thread.sleep(6000);
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[1]/section/div/div/div[1]/div[1]/div/div[1]/div/div[1]/div[1]/header/div[2]/div/div[2]/ul/li[4]/div/div[1]/div/div/a/lightning-icon/lightning-primitive-icon")));////*[@id="brandBand_1"]/div/div[1]/div[5]/div/div[1]/div/div[1]/div[1]/header/div[2]/div/div[2]/ul/li[4]
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[7]/div/ul/li")));
        myDynamicElement.click();
        myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/div[3]/div/button[2]/span")));
        myDynamicElement.click();
        Thread.sleep(5000);
        //------------------------





    }

    public static void main(String[] args) {
        try {
            com.others.LoginToSalesforce obj = new com.others.LoginToSalesforce();
            AddRACIinProgram obj1 = new AddRACIinProgram();

            obj.openBrowserOnChrome();
            obj.loginIntoSalesforce();
            //obj.AllWork();
            //driver.quit();

            //obj1.openBrowserOnChrome();
            //obj1.loginIntoSalesforce();
            //obj1.AddRaci();

        }
        catch(Exception e){
            System.out.println("Exception: "+e.toString());
            System.out.println("details: "+e.getMessage());
            System.out.println("Exception Line number: "+e.getStackTrace()[0].getLineNumber());
        }
    }

}


