package com.architect.entity;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author wenxiong.jia
 * @since 2019/1/24
 */
@Getter
@Setter
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String countryname;
    private String countrycode;
}