package com.course.bean;

import lombok.Data;


@Data//可以代替setget方法
public class User {
    private String name;
    private String age;
    private String sex;
    private String userName;
    private String passWord;

}
