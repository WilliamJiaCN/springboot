package com.architect.entity;

import com.github.dozermapper.core.Mapping;
import lombok.Data;

@Data
public class SourceBean {

    private Long id;

    private String name;

    @Mapping("binaryData")
    private String data;

    @Mapping("pk")
    public Long getId() {
        return this.id;
    }

    private Integer age;
}