package com.laoz.trading.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Maximum single-day profit snapshot.
 * 单日最大收益快照。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaxDailyProfit {

    /**
     * Single-day amount
     * 单日金额
     */
    private BigDecimal amount;

    /**
     * The date on which the maximum single-day profit occurred
     * 最大单日收益对应的日期
     */
    private LocalDate date;

}
