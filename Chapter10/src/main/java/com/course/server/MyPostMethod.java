package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是欧成金全部的post方法")
@RequestMapping("/v1")
public class MyPostMethod {
    //这个变量时用来装我们cookies信息的
    public static Cookie cookie;

    /**
     * 用户登陆成功获取cookies,然后再访问其他接口获取列表
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口,成功后,获取cookies",httpMethod = "POST")
    public String login(@RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "password",required = true) String password,
                        HttpServletResponse response){
        if (userName.equals("zhangsan")&&password.equals("123456")) {
            Cookie cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登陆成功";
        }
        return "用户名或密码错误";
    }


}
