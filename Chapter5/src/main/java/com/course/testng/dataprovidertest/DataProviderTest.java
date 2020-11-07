package com.course.testng.dataprovidertest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

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



    @Test(dataProvider = "methodData")
    public void testDataProvider2(String name,int age){
        System.out.println("name:"+name+";age:"+age);
    }
    @Test(dataProvider = "methodData")
    public void testDataProvider3(String name,int age){
        System.out.println("name:"+name+";age:"+age);
    }
    @DataProvider(name="methodData")
    public Object[][] providerData(Method method){
        Object[][] o = null;
        if (method.getName().equals("testDataProvider2")){
            o=new Object[][]{
                    {"zhaoliu",20},
                    {"wuqi",21},
            };
        } else if(method.getName().equals("testDataProvider3")){
            o=new Object[][]{
                    {"ba",22},
                    {"jiu",23},
            };
        }
        return o;
    }
}
