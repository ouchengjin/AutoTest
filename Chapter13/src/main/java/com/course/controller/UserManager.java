package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("/v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        Boolean ret = true;
        User u = (User)template.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        log.info("查询到的结果是:"+u);

        if(u!=null){
            ret = true;
            log.info("登陆的用户是:"+u.getUserName());
        }else {
            ret = false;
        }


        return ret;
        
    }

}
