package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MycookiesForPost {
    private String url;
    private CookieStore store;
    //读配置文件用
    private ResourceBundle bundle;
    @BeforeTest
    public void beforeTest(){
        //连接配置文件(写文件名前缀即可),因为文件直接在resources下,所以没写路径,
        //若文件在resources深层次目录下,写路径,
        bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");

    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri;
        uri= bundle.getString("getCookies.uri");
        String testUrl=url+uri;

        HttpGet get = new HttpGet(testUrl);
//      HttpClient httpClient = new DefaultHttpClient();
        //DefaultHttpClient可以执行getCookieStore()
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //获取cookies信息
        store = client.getCookieStore();
        List<Cookie> cookies = store.getCookies();
        for (Cookie cookie:
                cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name:"+name+",value:"+value);
        }

    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        String uri = bundle.getString("test.post.with.cookies");
        //拼接最终的测试地址
        String testUrl=this.url+uri;
        //声明一个client对象
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法,此时就是post方法
        HttpPost post = new HttpPost(testUrl);
        //添加参数 重点
        JSONObject param = new JSONObject();
        param.put("name","zhangsan");
        param.put("age","19");

        //设置请求头信息 设置header
        post.setHeader("Content-Type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象进行响应结果的存储
        String result;
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取相应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("result:"+result);
        //处理结果,就是判断返回结果是否符合预期
        //将返回的响应结果字符串转化为json对象
        JSONObject resultJson = new JSONObject(result);

        //获取到结果值
        String success = (String) resultJson.get("huitailang");
        String status = (String) resultJson.get("status");
        //具体的判断返回结果的值
        Assert.assertEquals("success",success);
        Assert.assertEquals("2",status);




    }

}
