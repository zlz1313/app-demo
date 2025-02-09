package com.opensource.demo.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest<T> implements Serializable {
    private static final Integer DEFAULT_PAGE_NUM = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private int current = DEFAULT_PAGE_NUM;
    private int pageSize = DEFAULT_PAGE_SIZE;

    private T data;
}
