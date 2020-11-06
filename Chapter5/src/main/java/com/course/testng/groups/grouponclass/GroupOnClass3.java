package com.course.testng.groups.grouponclass;

import org.testng.annotations.Test;

@Test(groups="teacher")
public class GroupOnClass3 {

    public void test1(){
        System.out.println("GroupOnClass3的test1运行了");
    }
    public void test2(){
        System.out.println("GroupOnClass3的test2运行了");
    }
}
