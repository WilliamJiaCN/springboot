package com.architect.dto;

import com.architect.annotation.ModelProp;
import com.architect.annotation.ModelTitle;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wenxiong.jia
 * @since 2018/9/26
 */
@Data
@ModelTitle(name = "人员列表")
public class EmployeeDTO extends ImportModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @ModelProp(name = "电话", colIndex = 1, nullable = false)
    private String telephone;
    @ModelProp(name = "名称", colIndex = 0, nullable = false)
    private String name;
    @ModelProp(name = "性别", colIndex = 2, nullable = false)
    private Integer sex;
}
