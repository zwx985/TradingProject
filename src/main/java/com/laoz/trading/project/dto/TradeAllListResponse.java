package com.laoz.trading.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * Trade record list
     * 交易记录列表
     */
    private List<TradeResponse> records;

    /**
     * Total number of records
     * 记录总条数
     */
    private long totalCount;

}
