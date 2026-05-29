package com.laoz.trading.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Maximum cumulative profit snapshot (prefix sum).
 * 最大累计收益快照（前缀和）。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaxCumulativeProfit {

    /**
     * Cumulative amount on the peak day
     * 峰值日的累计金额
     */
    private BigDecimal amount;

    /**
     * The date on which the maximum cumulative profit occurred
     * 最大累计收益对应的日期
     */
    private LocalDate date;

}
