package com.TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class testClass {

    @Test
    void setup() {
        System.out.println("this is setup method");
    }

    @Test
    void login() {
        System.out.println("this is login");
        String ex = "Avro";
        String ac = "Avr";
        Assert.assertEquals(ex,ac);
    }

    @Test
    void exit() {
        System.out.println("this is exit");
    }
}

