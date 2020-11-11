package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MycookiesForGet {

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
    public void testGetWithCookies() throws IOException {
        String uri;
        uri= bundle.getString("test.get.with.cookies");
        String testUrl=url+uri;
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(this.store);

        HttpResponse response = client.execute(get);
        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode:"+statusCode);
        if (statusCode==200){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }
    }
}
