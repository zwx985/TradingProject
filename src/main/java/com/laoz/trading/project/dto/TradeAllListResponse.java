package com.laoz.trading.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * All trade records response including total count
 * 全部交易记录响应，包含总条数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeAllListResponse {

    /**
     * Total number of records
     * 记录总条数
     */
    private long totalCount;

    /**
     * Total sum
     * 总和
     */
    private BigDecimal totalAmount;

    /**
     * Trade record list
     * 交易记录列表
     */
    private List<TradeResponse> records;

}
