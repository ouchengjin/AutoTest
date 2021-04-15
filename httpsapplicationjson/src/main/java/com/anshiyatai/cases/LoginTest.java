package com.anshiyatai.cases;


import com.anshiyatai.util.JsonUtils;
import com.mongodb.util.JSON;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static com.anshiyatai.util.HttpsUtil.post;

public class LoginTest {

    @Test
    public void  login(){
            try {

                //请求地址(我这里测试使用淘宝提供的手机号码信息查询的接口)
                String address = "https://www.fangzhenxiu.com/api/user/login";

                //请求参数
                Map<String, String> params = new HashMap<String, String>();
				
                params.put("username", "15011252382");//这是该接口需要的参数
                params.put("password", "123456");//这是该接口需要的参数
                params.put("senceCode", "300");//这是该接口需要的参数

               
                // 调用 get 请求
//                String res = get(address, params, null);
//                System.out.println(res);//打印返回参数
                String res = post(address, params, null);
                System.out.println(res);//打印返回参数
                /*Object parse = JSON.parse(res);
                System.out.println(parse);
                Map userIdTokenMsg = JsonUtils.getJsonList(res);
                Assert.assertEquals(userIdTokenMsg.get("msg"),"登录成功");*/


            } catch (Exception e) {
                // TODO 异常
                e.printStackTrace();
            }

    }

}
