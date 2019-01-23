package com.architect.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wenxiong.jia
 * @since 2018/12/21
 */
@Data
public class PersonDTO {
    private String firstName;
    private String lastName;
    private List<String> jobTitles;
    private long salary;
    private StudentDTO stu;
    private List<StudentDTO> stus;
}
