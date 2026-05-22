package com.laoz.trading.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Trade Update Request DTO
 * 交易修改请求数据传输对象
 */
@Data
public class TradeUpdateRequest {

    /**
     * Primary key ID of the record to update
     * 待修改记录的主键ID
     */
    @NotBlank(message = "ID cannot be empty")
    private String id;

    /**
     * New amount (supports decimals and negatives)
     * 新金额（支持小数、负数）
     */
    @NotBlank(message = "Amount cannot be empty")
    private String amount;

}
