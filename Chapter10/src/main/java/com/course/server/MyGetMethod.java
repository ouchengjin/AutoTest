package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method=RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod="get")
    public String getCookies(HttpServletResponse response){
        //HttpServletRequest  装载请求信息的类
        //HttpServletResponse 装载响应信息的类
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获得了cookies信息";
    }

    /**
     * 这是需要携带cookies才能访问的get请求
     */
    @RequestMapping(value = "get/withCookies",method = RequestMethod.GET)
    @ApiOperation(value = "这是需要携带cookies才能访问的get请求",httpMethod = "get")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "您必须携带cookies访问";
        }
        for (Cookie cookie:
             cookies) {
            if (cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "恭喜您携带cookies访问成功";
            }
        }
        return "必须携带正确cookies访问";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url: ip:port/get/with/param?key=value&key=value
     * 我们来模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "一个需要携带参数才能访问的get请求方法一",httpMethod = "get")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋子",10);
        myList.put("帽子",20);
        myList.put("裤子",25);

        return myList;
    }

    /**
     * 第二种需要携带参数访问的get请求
     * 第二种实现方式 url: ip:port/get/with/param/{变量名1}/{变量名n}
     *
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "需要携带参数访问的get请求方法一",httpMethod = "get")
    public Map<String,Integer> getList2(@PathVariable Integer start,
                                        @PathVariable Integer end){
        Map<String,Integer> goodsList = new HashMap<>();
        goodsList.put("鞋子",100);
        goodsList.put("帽子",200);
        goodsList.put("裤子",250);
        return goodsList;
    }
}
