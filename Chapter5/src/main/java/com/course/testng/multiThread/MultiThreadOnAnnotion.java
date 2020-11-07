package com.course.testng.multiThread;
import org.testng.annotations.Test;
public class MultiThreadOnAnnotion {
    /**
     * @Test(threadPoolSize = 3, invocationCount = 10,  timeOut = 10000)
     * 说明：该测试方法可在3个线程中并发执行，共被调用10次，执行超时10秒。
     */
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void test(){
        System.out.println(1);
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

}
