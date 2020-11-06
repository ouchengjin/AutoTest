package com.course.testng.groups.grouponclass;

import org.testng.annotations.Test;

@Test(groups="stu")
public class GroupOnClass1 {

    public void test1(){
        System.out.println("GroupOnClass1的test1运行了");
    }
    public void test2(){
        System.out.println("GroupOnClass1的test2运行了");
    }
}
