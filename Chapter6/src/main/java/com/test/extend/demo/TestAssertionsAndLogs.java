package com.test.extend.demo;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
public class TestAssertionsAndLogs {
    @Test
    public void testAssertion(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void testAssertion2(){
        Assert.assertEquals(1,2);
    }

    @Test
    public void testAssertion3(){
        Assert.assertEquals("ss","ss");
    }

    @Test
    public void logDemo(){
        Reporter.log("这是我们自己写的日志",true);
        throw new RuntimeException("这是我自己的运行时异常");
    }
}
