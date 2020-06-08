package com.others;

//import org.junit.Test;
import org.testng.annotations.Test;

public class MyTestClass extends MyAbstractClass {
    @Test
    public void myTest() throws InterruptedException {
        this.driver.get("http://google.com");
        Thread.sleep(10000);
    }
}
