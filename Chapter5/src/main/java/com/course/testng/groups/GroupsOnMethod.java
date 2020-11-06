package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.io.PrintStream;

public class GroupsOnMethod {

    private PrintStream out;

    @Test(groups = "server")
    public void server1(){
        System.out.println("这是服务端1运行的方法");
    }

    @Test(groups = "server")
    public void server2(){
        System.out.println("这是服务端2运行的方法");
    }

    @Test(groups = "client")
    public void client1(){
        System.out.println("这是客户端1运行的方法");
    }

    @Test(groups = "client")
    public void client2(){
        System.out.println("这是客户端2运行的方法");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer(){
        System.out.println("这是在服务端组前运行的方法");
    }

    @AfterGroups("server")
    public void afterGroupsOnServer(){
        System.out.println("这是在服务端组后运行的方法");
    }

    @BeforeGroups("client")
    public void beforeGroupsOnClient(){
        System.out.println("这是在客户端组前运行的方法");
    }

    @AfterGroups("client")
    public void afterGroupsOnClient(){
        System.out.println("这是在客户端组后运行的方法");
    }
}
