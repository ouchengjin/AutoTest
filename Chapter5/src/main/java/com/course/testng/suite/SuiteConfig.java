package com.course.testng.suite;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.testng.annotations.*;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite运行了");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite运行了");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("BeforeTest运行了");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("AfterTest运行了");
    }


//为什么不能使用BeforeMethod,AfterMethod
   /* @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod:This is running before test method");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod:This is running after test method");
    }*/
}
