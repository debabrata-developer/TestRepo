package com.others;

//import org.junit.Test;
import org.testng.annotations.Test;

public class MyTestClass_A extends MyAbstractClass {
    @Test
    public void myTest() {
        this.driver.get("http://Facebook.com");
    }
}
