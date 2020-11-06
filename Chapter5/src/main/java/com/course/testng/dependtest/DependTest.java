package com.course.testng.dependtest;

import org.testng.annotations.Test;

public class DependTest {
    @Test
    public void test1() throws RuntimeException {
        System.out.println("test1 run");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods = "test1")
    public void test2(){
        System.out.println("test2 run");
    }
}
