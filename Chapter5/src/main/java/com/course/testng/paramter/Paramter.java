package com.course.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Paramter {

    @Test
    @Parameters({"name","age"})
    public void paramterTest(String name,int age ){
        System.out.println("姓名:"+name+";年龄:"+age);
    }
}
