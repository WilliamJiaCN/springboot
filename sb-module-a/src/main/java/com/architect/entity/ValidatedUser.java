package com.architect.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author wenxiong.jia
 * @since 2018/12/5
 */
@Data
@ToString
public class ValidatedUser {
    @Size(min = 1, max = 10, message = "姓名长度必须为1到10")
    private String name;

    @NotNull
    private String firstName;

    @Min(value = 10, message = "年龄最小为10")
    @Max(value = 100, message = "年龄最大为100")
    private Integer age;

    @Future
    @JSONField(format = "yyyy-MM-dd")
    private Date birth;
}
