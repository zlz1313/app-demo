package com.opensource.demo.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProPageResponse<T> implements Serializable {

    private int total;

    private boolean success;

    private Integer current;

    private Integer pageSize;

    private List<T> data;
}
