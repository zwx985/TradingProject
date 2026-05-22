package com.laoz.trading.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

/**
 * Trade Add Request DTO
 * 交易新增请求数据传输对象
 */
@Data
public class TradeAddRequest {

    /**
     * Amount as string (supports decimals and negatives)
     * 金额字符串（支持小数、负数）
     */
    @NotBlank(message = "Amount cannot be empty")
    private String amount;

    /**
     * Creation time (optional, defaults to current time if not provided)
     * 创建时间（可选，未传则使用当前时间）
     */
    private LocalDate createTime;

}
