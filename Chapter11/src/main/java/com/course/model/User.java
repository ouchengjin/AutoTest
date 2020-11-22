package com.course.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Integer age;
    private String sex;
    private Integer permission;
    private Integer isDelete;
}
