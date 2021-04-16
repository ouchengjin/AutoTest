package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFileUtil;
import com.course.utils.MyBatisUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginTest {
    private Logger logger = Logger.getLogger(LoginTest.class);

    /**
     * 在xml配置文件的test标签运行前执行
     */
    @BeforeTest(groups = "loginTrue",description = "测试准备工作,获取HttpClient对象")
    public void beforeTest(){
        TestConfig.defaultHttpClient = new DefaultHttpClient();
        TestConfig.getUserInfoUrl = ConfigFileUtil.getUrl(InterfaceName.GETUSERIFO);
        TestConfig.getUserListUrl = ConfigFileUtil.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFileUtil.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFileUtil.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = ConfigFileUtil.getUrl(InterfaceName.ADDUSER);
    }


    @Test(groups = "loginTrue",description = "用户成功登陆接口")
    public void loginTrue() throws IOException {
        logger.info(TestConfig.loginUrl);
        SqlSession session = MyBatisUtil.getSession();
        LoginCase loginCase = (LoginCase)session.selectOne("loginCase",1);
        MyBatisUtil.close();

        //logger.info(loginCase.toString());

        LoginCase loginCase1 = new LoginCase();
        loginCase1.setUsername("15011252382");
        loginCase1.setPassword("JAa/f%2BWjuWRSUTHoPoIoksPvzACrlqAOhY9CoLUPKZjiV5Pf9c2Jyj8H49sbnRa7UBNtdXKhPfCnP5GsMxQ3Ml%2BbCmGo51lPutK3sVxqDAK4qiRiofmwIdVlYlIpfTZPUTNowdDEod6IdcOdN8MAhRYGEQ55pHptaRNdrgC/7H8=");
        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase1);
        //处理结果，就是判断返回结果是否符合预期
        //logger.info("预期:"+loginCase.getExpected()+".实际:"+result);
        //Assert.assertEquals(loginCase.getExpected(),result);
        logger.info(result);
        List<Cookie> cookies = TestConfig.store.getCookies();
        logger.info("cookies:\n");
        for (Cookie cookie:
        cookies) {
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }
    }

    @Test(description = "用户登陆失败接口")
    public void loginFalse() throws IOException {
        logger.info(TestConfig.loginUrl);
        SqlSession session = MyBatisUtil.getSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        MyBatisUtil.close();
        logger.info(loginCase.toString());

        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        logger.info("预期:"+loginCase.getExpected()+".实际:"+result);
        Assert.assertEquals(loginCase.getExpected(),result);

    }




    private String getResult(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("username",loginCase.getUsername());
        param.put("password",loginCase.getPassword());
        //设置请求头信息 设置header
        post.addHeader("Accept","application/json");
        post.setHeader("content-type","application/json;charset=UTF-8");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }


}
