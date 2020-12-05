package com.course.controller;

import com.course.model.User;
import com.course.utils.UtilsVerifyCookies;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("/v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate template;


    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        Boolean ret = true;
        User u = (User)template.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");

        log.info("查询到的结果是:"+u);

        if(u!=null){
            ret = true;
            log.info("登陆的用户是:"+u.getUserName());
            response.addCookie(cookie);
        }else {
            ret = false;
            log.info("登陆失败");
        }


        return ret;
        
    }

    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public  int addUser(HttpServletRequest request,@RequestBody User user){
        int ret=0;
        if(UtilsVerifyCookies.verifyCookies(request)){
            log.info("cookies验证通过");
            ret = template.insert("addUser", user);
            if(ret>0){
                log.info("添加用户数量是:"+ret);
            }else {
                log.info("添加用户失败,插入数据库失败");
            }
        }else {
            log.info("cookies验证失败");
        }
        return ret;
    }

    @ApiOperation(value = "获取用户信息(列表)",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        List<User> userList = new ArrayList<>();
        if(UtilsVerifyCookies.verifyCookies(request)){
            userList = template.selectList("getUserInfo", user);
            log.info("获取的用户信息为:"+userList);
        }else{
            log.info("cookies验证失败");
        }
        return userList;

    }

    @ApiOperation(value="更新用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUserInfo(HttpServletRequest request,@RequestBody User user){
        int ret = 0;
        if(UtilsVerifyCookies.verifyCookies(request)){
            ret = template.update("updateUserInfo", user);
            if(ret>0){
                log.info("用户信息更新成功条数"+ret);
            }else{
                log.info("用户更新失败,数据库执行失败");
            }

        }else {
            log.info("cookies验证失败");
        }
        return ret;
    }

    @ApiOperation(value="删除用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/deleteUserInfo",method = RequestMethod.POST)
    public int deleteUserInfo(HttpServletRequest request,@RequestBody User user){
        int ret = 0;
        if(UtilsVerifyCookies.verifyCookies(request)){
            ret = template.delete("updateUserInfo", user);
            if(ret>0){
                log.info("用户信息删除成功条数"+ret);
            }else{
                log.info("用户删除失败,数据库执行失败");
            }

        }else {
            log.info("cookies验证失败");
        }
        return ret;
    }



}
