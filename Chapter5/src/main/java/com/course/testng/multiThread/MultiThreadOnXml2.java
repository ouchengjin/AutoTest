package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml2 {
    @Test
    public void test1(){
        System.out.printf("MultiThreadOnXml2test1()Thread id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.printf("MultiThreadOnXml2test2()Thread id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("MultiThreadOnXml2test3()Thread id : %s%n",Thread.currentThread().getId());
    }
}
