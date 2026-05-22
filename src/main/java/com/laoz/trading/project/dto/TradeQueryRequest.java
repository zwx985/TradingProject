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

}
