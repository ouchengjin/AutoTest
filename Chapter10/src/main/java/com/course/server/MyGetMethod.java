package com.course.server;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method=RequestMethod.GET)
    public String getCookies(){

        return "恭喜你获得了cookies信息";
    }
}
