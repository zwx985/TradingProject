package com.laoz.trading.project.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Trade Entity - Maps to T_TRADE table
 * 交易实体类 - 映射 T_TRADE 数据表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_TRADE")
public class TradeEntity {

    /**
     * Primary key ID
     * 主键ID
     */
    @TableId(value = "id")
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
    private LocalDateTime createTime;

    /**
     * Update time
     * 更新时间
     */
    private LocalDateTime updateTime;

}
