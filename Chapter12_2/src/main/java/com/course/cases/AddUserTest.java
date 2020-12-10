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
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;



public class AddUserTest {


    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口接口")
    public void addUser() throws Exception {
        System.out.println(TestConfig.addUserUrl);
        SqlSession session = MyBatisUtil.getSession();
        AddUserCase addUserCase = (AddUserCase)session.selectOne("addUserCase",1);
        MyBatisUtil.close();
        System.out.println(addUserCase.toString());

        //发请求获取结果
        //下边的代码为写完接口的测试代码
        Integer result = getResult(addUserCase);

        if (result==1){
            Thread.sleep(4000);
            //验证返回结果,查询用户看是否添加成功
            SqlSession session2 = MyBatisUtil.getSession();
            User user = (User)session2.selectOne("addUser",addUserCase);
            MyBatisUtil.close();
            if (user==null){
                throw new Exception("数据库查询无数据");
            }
            User user2= new User(user.getId(),addUserCase.getUserName(),addUserCase.getPassword(),
                    addUserCase.getAge(),addUserCase.getSex(),addUserCase.getPermission(),addUserCase.getIsDelete());

            //处理结果，就是判断返回结果是否符合预期
            System.out.println("预期:"+user2+".实际:"+user);
            Assert.assertEquals(user2,user);

        } else {
            throw new Exception("接口返回失败");
        }




    }



    private Integer getResult(AddUserCase addUserCase) throws IOException {
        Logger logger = Logger.getLogger(AddUserTest.class);
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
        logger.debug("result:"+result);
        return Integer.parseInt(result);
    }



}
