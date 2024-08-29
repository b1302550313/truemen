package com.sysu.verto.common.result;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private int total;
    private List<T> list;

    /**
     * 分页
     * 
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageResult(List<T> list, long total) {
        this.list = list;
        this.total = (int) total;
    }
}