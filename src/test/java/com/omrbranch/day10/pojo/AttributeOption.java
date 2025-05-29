package com.omrbranch.day10.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttributeOption{
    private int id;
    private int attribute_id;
    private String value;
    private String status;
    private String created_at;
    private String updated_at;
}

