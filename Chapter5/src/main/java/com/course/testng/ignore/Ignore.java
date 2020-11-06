package com.course.testng.ignore;

import org.testng.annotations.Test;

public class Ignore {

    @Test
    public void ignore1(){
        System.out.println("ignore1 running");
    }

    @Test(enabled = false)
    public void ignore2(){
        System.out.println("ignore2 running");
    }

    @Test(enabled = true)
    public void ignore3(){
        System.out.println("ignore3 running");
    }
}
