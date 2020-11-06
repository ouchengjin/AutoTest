package com.course.testng.basicannotation;


import org.testng.annotations.*;

public class BasicAnnotation {
    //最基本的注解,用来把方法标记为测试的一部分
    @Test
    public void TestCase1(){
        System.out.println("Test:This is testcase-1");
     }

    @Test
    public void TestCase2(){
        System.out.println("Test:This is testcase-2");
    }
     @BeforeMethod
     public void beforeMethod(){
        System.out.println("BeforeMethod:This is running before test method");
     }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod:This is running after test method");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass:This is running before test class");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("AfterClass:This is running after test class");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite:This is running before test suite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite:This is running after test suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("BeforeTest运行了");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("AfterTest运行了");
    }

}
