package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@Api(value = "v1",description = "这是我的第一个版本的demo")
@RequestMapping("/v1")
public class Demo {
    //首先获取一个执行SQL语句的对象
    @Autowired
    private SqlSessionTemplate template;


    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数",httpMethod = "GET")
    public int getUserCount(){
        int ret =0;
        ret = template.selectOne("getUserCount");
        return ret;
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "增加用户",httpMethod = "POST")
    public int addUser(@RequestBody User user){
        int ret =0;
        ret = template.insert("addUser",user);
        return ret;
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation(value = "",httpMethod = "GET")
    public int updateUser(@RequestBody User user){
        int ret = 0;
        ret = template.update("updateUser", user);
        return ret;
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    @ApiOperation(value = "删除用户",httpMethod = "GET")
    public int deleteUser(@RequestParam int id){
        int ret;
        ret = template.delete("deleteUser", id);
        return ret;
    }
}
