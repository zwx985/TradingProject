package com.laoz.trading.project.common.request;

import lombok.Data;

/**
 * Generic pagination request wrapper
 * 通用分页请求包装类
 *
 * @param <T> request body type 请求体类型
 */
@Data
public class PageRequest<T> {

    /**
     * Request body (query conditions, sort parameters, etc.)
     * 请求体（查询条件、排序参数等）
     */
    private T request;

    /**
     * Current page index, default 1
     * 当前页码，默认 1
     */
    private Long pageIndex;

    /**
     * Page size, default 10
     * 每页大小，默认 10
     */
    private Long pageSize;

}
