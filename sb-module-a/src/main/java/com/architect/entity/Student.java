package com.architect.entity;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private String sex;

    public Student(int id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}