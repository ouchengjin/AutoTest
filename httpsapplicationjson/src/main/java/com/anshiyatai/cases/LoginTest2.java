package com.anshiyatai.cases;

import com.alibaba.fastjson.JSONObject;
import com.anshiyatai.util.applicationjson.SSLClient;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.cookie.Cookie;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginTest2 {
    private String url = "https://www.fangzhenxiu.com/api/user/login";
    private String charset = "utf-8";
    private SSLClient httpClientUtil = new SSLClient();

    public LoginTest2() throws Exception {
    }


    @Test
    public void HttpsPostTest() throws Exception {
        int senceCode = 200;
        String username = "15011252382";
        String password = "123456";
        //Map<String,String> params= new HashMap<>();
        JSONObject params = new JSONObject();


        //String sign = md5(ver + appId + msgId + timestamp + appKey).toUpperCase();
        password = md5(password).toUpperCase();

        params.put("senceCode", senceCode);
        params.put("username", username);
        params.put("password", "JAa/f%2BWjuWRSUTHoPoIoksPvzACrlqAOhY9CoLUPKZjiV5Pf9c2Jyj8H49sbnRa7UBNtdXKhPfCnP5GsMxQ3Ml%2BbCmGo51lPutK3sVxqDAK4qiRiofmwIdVlYlIpfTZPUTNowdDEod6IdcOdN8MAhRYGEQ55pHptaRNdrgC/7H8=");

        String encryptStr = params.toString();
        System.out.println("encryptStr：" + encryptStr);
        String httpOrgCreateTestRtn = httpClientUtil.doPostJson(url, encryptStr, charset);
        CookieStore cookieStore1 = httpClientUtil.getCookieStore();
        List<Cookie> cookies = cookieStore1.getCookies();
        if (cookies.isEmpty()){
            System.out.println("没有cookies返回");
        }
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }

        System.out.println("result:" + httpOrgCreateTestRtn);

    }
    public static String md5(String text) {
        String result="";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
//       System.out.println("result: " + buf.toString());// 32位的加密
//       System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

}
