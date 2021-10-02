package com.luoyingmm.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> list;

    private Long total;
}
