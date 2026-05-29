package com.laoz.trading.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Maximum subarray profit snapshot (Kadane's algorithm result).
 * 最大子数组收益快照（Kadane 算法结果）。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaxSubarrayProfit {

    /**
     * Maximum sum of a contiguous subarray
     * 连续子数组的最大和
     */
    private BigDecimal amount;

    /**
     * Start date of the maximum subarray interval
     * 最大子数组区间的起始日期
     */
    private LocalDate startDate;

    /**
     * End date of the maximum subarray interval
     * 最大子数组区间的结束日期
     */
    private LocalDate endDate;

}
