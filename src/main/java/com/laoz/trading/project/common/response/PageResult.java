package com.laoz.trading.project.common.response;

import lombok.Data;

import java.util.List;

/**
 * Generic page result wrapper
 * 通用分页结果包装类
 *
 * @param <T> record type 记录类型
 */
@Data
public class PageResult<T> {

    /**
     * Record list of current page
     * 当前页记录列表
     */
    private List<T> records;

    /**
     * Total number of records
     * 总记录数
     */
    private long total;

    /**
     * Current page index
     * 当前页码
     */
    private long pageIndex;

    /**
     * Page size
     * 每页大小
     */
    private long pageSize;

    /**
     * Total number of pages
     * 总页数
     */
    private long pages;

    public PageResult(List<T> records, long total, long pageIndex, long pageSize, long pages) {
        this.records = records;
        this.total = total;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pages = pages;
    }

}
