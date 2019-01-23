package com.architect.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TargetBean {

    private String pk;

    private String name;

    private String binaryData;

    private BigDecimal salary;
}