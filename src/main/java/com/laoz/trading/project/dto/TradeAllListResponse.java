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

    /**
     * Maximum cumulative profit snapshot (prefix sum) up to a certain day
     * 截止到某日的最大累计收益快照（前缀和）
     */
    private MaxCumulativeProfit maxCumulativeProfit;

    /**
     * Maximum single-day profit snapshot
     * 单日最大收益快照
     */
    private MaxDailyProfit maxDailyProfit;

    /**
     * Maximum subarray profit snapshot (Kadane's algorithm result)
     * 最大子数组收益快照（Kadane 算法结果）
     */
    private MaxSubarrayProfit maxSubarrayProfit;

    /**
     * Constructor with basic fields (totalCount, totalAmount, records).
     * Statistics fields are left null and set separately when needed.
     * 基础字段构造器（总条数、总金额、记录列表）。
     * 统计字段留空，需要时另行设置。
     *
     * @param totalCount total number of records / 记录总条数
     * @param totalAmount total sum of amounts / 金额总和
     * @param records trade record list / 交易记录列表
     */
    public TradeAllListResponse(long totalCount, BigDecimal totalAmount, List<TradeResponse> records) {
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
        this.records = records;
    }

}
