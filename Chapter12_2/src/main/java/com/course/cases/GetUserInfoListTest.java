package com.course.cases;

import com.alibaba.fastjson.JSON;
import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.MyBatisUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoListTest {
    private Logger logger = Logger.getLogger(AddUserTest.class);


    @Test(dependsOnGroups="loginTrue",description = "获取性别为男的用户信息")
    public void getUserListInfo() throws IOException, InterruptedException {
        logger.info(TestConfig.getUserListUrl);
        SqlSession session = MyBatisUtil.getSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);
        MyBatisUtil.close();
        logger.info(getUserListCase.toString());
        //下边为写完接口的代码
        List<User> result = getJsonResult(getUserListCase);
        //验证
        Thread.sleep(2000);
        SqlSession session2 = MyBatisUtil.getSession();
        List<User> userList = session2.selectList(getUserListCase.getExpected(),getUserListCase);
        MyBatisUtil.close();
        logger.info("预期:"+result+".实际:"+userList);
        Assert.assertEquals(result,userList);
    }

    private List<User> getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        List<User> userList = JSON.parseArray(result, User.class);
        return userList;

    }

}
