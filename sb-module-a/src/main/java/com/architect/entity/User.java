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
    private Long id;
    private String name;
    private Integer age;
    private String address;
    private String phone;
}
