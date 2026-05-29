package com.laoz.trading.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
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
     * Maximum cumulative profit (prefix sum) up to a certain day
     * 截止到某日的最大累计收益（前缀和）
     */
    private BigDecimal maxCumulativeAmount;

    /**
     * The date on which the maximum cumulative profit occurred
     * 最大累计收益对应的日期
     */
    private LocalDate maxCumulativeDate;

    /**
     * Constructor with basic fields (totalCount, totalAmount, records).
     * maxCumulativeAmount and maxCumulativeDate are left null and set separately when needed.
     * 基础字段构造器（总条数、总金额、记录列表）。
     * 最大累计收益及其日期留空，需要时另行设置。
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
