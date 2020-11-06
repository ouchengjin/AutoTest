package com.course.testng.suite;

import com.sun.deploy.net.proxy.ProxyUnavailableException;
import org.testng.annotations.Test;

public class PayTest {

    @Test
    public void paySuccess() throws ProxyUnavailableException {
        System.out.println("支付宝支付成功");
    }
}
