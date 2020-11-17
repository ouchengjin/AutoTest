package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User user){
        //获取cookies
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "cookies 不能为空";
        }
        //验证cookies是否合法
        for (Cookie cookie:
             cookies) {
            if (cookie.getName().equals("login")
                    &&cookie.getValue().equals("true")
                    &&user.getUserName().equals("zhangsan")
                    &&user.getPassWord().equals("123456")) {
                User user1 = new User();
                user1.setName("lisi");
                user1.setAge("18");
                user1.setSex("man");
                return user1.toString();
            }

        }
        return "参数不合法";

    }


}
