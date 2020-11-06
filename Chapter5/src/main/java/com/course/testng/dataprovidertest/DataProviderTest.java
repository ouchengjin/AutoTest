package com.course.testng.dataprovidertest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name:"+name+";age:"+age);
    }

    @DataProvider(name = "data")
    public Object[][] ProviderData(){
        Object[][] o=new Object[][]{
                {"zhangsan",18},
                {"lisi",17},
                {"wangwu",19}
        };
        return o;
    }
}
