package com.course.testng.timeout;

import org.testng.annotations.Test;

public class TimeOutTest {
    /**
     * timeOut单位为毫秒值
     * 测试通过模拟
     * @throws InterruptedException
     */
    @Test(timeOut = 2000)
    public void timeOutTest() throws InterruptedException {
        Thread.sleep(1000);
    }

    /**
     * timeOut单位为毫秒值
     * 测试失败模拟
     * @throws InterruptedException
     */
    @Test(timeOut = 1000)
    public void timeOutTestFailed() throws InterruptedException {
        Thread.sleep(2000);
    }
}
