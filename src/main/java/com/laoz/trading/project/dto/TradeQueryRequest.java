package com.laoz.trading.project.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Trade Query Request DTO
 * 交易查询请求数据传输对象
 */
@Data
public class TradeQueryRequest {

    /**
     * Primary key ID for exact match query
     * 主键ID，精确匹配查询
     */
    private String id;

    /**
     * Amount for exact match query
     * 金额，精确匹配查询
     */
    private BigDecimal amount;

    /**
     * Creation date for exact match query
     * 创建日期，精确匹配查询
     */
    private LocalDate createTime;

    /**
     * Sort by amount, value: asc or desc
     * 根据金额排序，取值：asc（升序）或 desc（降序）
     */
    private String amountSort;

    /**
     * Sort by createTime, value: asc or desc
     * 根据创建时间排序，取值：asc（升序）或 desc（降序）
     */
    private String createTimeSort;

    /**
     * Sort by updateTime, value: asc or desc
     * 根据更新时间排序，取值：asc（升序）或 desc（降序）
     */
    private String updateTimeSort;
}
