package com.laoz.trading.project.dto;

import lombok.Data;

/**
 * Trade Query Request DTO
 * 交易查询请求数据传输对象
 */
@Data
public class TradeQueryRequest {

    /**
     * Name for query condition
     * 查询名称条件
     */
    private String name;

}
