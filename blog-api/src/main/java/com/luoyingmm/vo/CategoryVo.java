package com.luoyingmm.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class CategoryVo {
    private String id;

    private String avatar;

    private String categoryName;

    private String description;
}