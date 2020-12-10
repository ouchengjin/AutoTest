package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.MyBatisUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {


    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口接口")
    public void addUser() throws IOException, InterruptedException {
        System.out.println(TestConfig.addUserUrl);
        SqlSession session = MyBatisUtil.getSession();
        AddUserCase addUserCase = (AddUserCase)session.selectOne("addUserCase",1);
        MyBatisUtil.close();
        System.out.println(addUserCase.toString());

        //发请求获取结果
        //下边的代码为写完接口的测试代码
        String result = getResult(addUserCase);

        /**
         * 可以先讲
         */
        //查询用户看是否添加成功
        Thread.sleep(4000);
        //验证返回结果
        SqlSession session2 = MyBatisUtil.getSession();
        User user = (User)session2.selectOne("addUser",addUserCase);
        MyBatisUtil.close();
        System.out.println("addUser: "+user);


        //处理结果，就是判断返回结果是否符合预期
        System.out.println("预期:"+addUserCase.getExpected()+".实际:"+result);
        Assert.assertEquals(addUserCase.getExpected(),result);


    }


    private String getResult(AddUserCase addUserCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("userName",addUserCase.getUserName());
        param.put("password",addUserCase.getPassword());
        param.put("sex",addUserCase.getSex());
        param.put("age",addUserCase.getAge());
        param.put("permission",addUserCase.getPermission());
        param.put("isDelete",addUserCase.getIsDelete());
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
        System.out.println("result:"+result);
        if(Integer.parseInt(result)>0){
            return "true";
        }else{
            return "false";
        }
    }



}
