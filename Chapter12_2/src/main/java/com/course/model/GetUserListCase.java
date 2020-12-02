package com.course.model;

import lombok.Data;

@Data
public class GetUserListCase {
    private Integer id;
    private String userName;
    private Integer age;
    private Integer sex;
    private String expected;
}
