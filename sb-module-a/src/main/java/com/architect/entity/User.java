package com.architect.entity;


import com.architect.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
/**
 * 
 * 
 * @author jiawenxiong
 * @date 2018/8/25
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@ExcelField(
			title = "编号",
			groups = {0})
	private Long id;
	/**
	 * 姓名
	 */
	@ExcelField(
			title = "姓名",
			groups = {0})
	private String name;
	/**
	 * 年龄
	 */
	@ExcelField(
			title = "年龄",
			groups = {0})
	private Integer age;
	/**
	 * 地址
	 */
	@ExcelField(
			title = "地址",
			groups = {0})
	private String address;
	/**
	 * 手机号
	 */
	@ExcelField(
			title = "手机号",
			groups = {0})
	private String phone;

	/**
	 * 错误描述
	 */
	@ExcelField(
			title = "错误描述",
			groups = {1})
	private String errorInfoDesc;
}

