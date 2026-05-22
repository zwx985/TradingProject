package com.laoz.trading.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Trade Search Response DTO
 * 交易搜索响应数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeSearchResponse {

    /**
     * Primary key ID
     * 主键ID
     */
    private String id;

    /**
     * Amount (supports decimals and negatives)
     * 金额（支持小数、负数）
     */
    private BigDecimal amount;

    /**
     * Creation date
     * 创建日期
     */
    private LocalDate createTime;

    /**
     * Update date
     * 更新日期
     */
    private LocalDate updateTime;

}
