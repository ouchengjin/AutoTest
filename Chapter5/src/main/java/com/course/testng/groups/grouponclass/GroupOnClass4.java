package com.course.testng.groups.grouponclass;

import org.testng.annotations.Test;

@Test(groups="teacher")
public class GroupOnClass4 {

    public void test1(){
        System.out.println("GroupOnClass4的test1运行了");
    }
    public void test2(){
        System.out.println("GroupOnClass4的test2运行了");
    }
}
