package com.laoz.trading.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Trade Response DTO
 * 交易响应数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeResponse {

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
     * Creation time
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * Update time
     * 更新时间
     */
    private LocalDate updateTime;

}
