package com.architect.entity;

import lombok.Data;

import java.util.List;

/**
 * @author wenxiong.jia
 * @since 2018/12/21
 */
@Data
public class Person {
    private String firstName;
    private String lastName;
    private List<String> jobTitles;
    private long salary;
    private Student stu;
    private List<Student> stus;
}
