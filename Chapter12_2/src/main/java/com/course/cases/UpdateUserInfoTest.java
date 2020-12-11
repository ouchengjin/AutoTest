package com.course.cases;


import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
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

public class UpdateUserInfoTest {
    private Logger logger = Logger.getLogger(LoginTest.class);
    @Test(dependsOnGroups = "loginTrue",description = "更改用户信息")
    public void updateUserInfo() throws Exception {
        logger.info(TestConfig.updateUserInfoUrl);
        SqlSession session = MyBatisUtil.getSession();
        UpdateUserInfoCase updateUserInfoCase = (UpdateUserInfoCase)session.selectOne("updateUserInfoCase",1);
        MyBatisUtil.close();
        logger.info(updateUserInfoCase.toString());
        //访问接口
        int result = getResult(updateUserInfoCase);
        if (result==1){
            Thread.sleep(4000);
            //验证返回结果,查询用户看是否添加成功
            SqlSession session2 = MyBatisUtil.getSession();
            User user = session2.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
            MyBatisUtil.close();
            logger.info("预期:"+updateUserInfoCase+".实际:"+user);
            //处理结果，就是判断返回结果是否符合预期
            Assert.assertNotNull(user,"数据库查询无数据");
            logger.info("测试通过");
        } else {
            throw new Exception("接口返回失败");
        }

    }

    @Test(dependsOnGroups = "loginTrue",description = "删除用户")
    public void deleteUser() throws Exception {
        logger.info(TestConfig.updateUserInfoUrl);
        SqlSession session = MyBatisUtil.getSession();
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase",2);
        MyBatisUtil.close();
        logger.info(updateUserInfoCase.toString());



        //下边为写完接口的代码
        int result = getResult(updateUserInfoCase);

        if (result==1){
            Thread.sleep(4000);
            //验证返回结果,查询用户看是否添加成功
            SqlSession session2 = MyBatisUtil.getSession();
            User user = session2.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
            MyBatisUtil.close();
            logger.info("预期:"+updateUserInfoCase+".实际:"+user);
            //处理结果，就是判断返回结果是否符合预期
            Assert.assertNotNull(user,"数据库查询无数据");
            logger.info("测试通过");

        } else {
            throw new Exception("接口返回失败");
        }

    }


    private Integer getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",updateUserInfoCase.getUserId());
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
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
        return Integer.parseInt(result);

    }

}
