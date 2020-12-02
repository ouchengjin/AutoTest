package com.course.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {
    private Integer id;
    private Integer userId;
    private String userName;
    private Integer sex;
    private Integer age;
    private Integer permission;
    private Integer isDelete;
    private String expected;
}
