package com.architect.entity;


import lombok.Data;
import lombok.ToString;

/**
 * @author wenxiong.jia
 * @since 2018/7/17
 */
@Data
@ToString
public class User {
    private long id;
    private String name;
    private int age;
}
