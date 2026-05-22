package com.laoz.trading.project.converter;

import com.laoz.trading.project.dto.TradeResponse;
import com.laoz.trading.project.entity.TradeEntity;

/**
 * 交易对象转换器
 */
public class TradeConverter {

    private TradeConverter() {
        // 工具类，禁止实例化
    }

    /**
     * 将实体对象转换为响应DTO
     *
     * @param entity 交易实体
     * @return 交易响应DTO，entity 为 null 时返回 null
     */
    public static TradeResponse toResponse(TradeEntity entity) {
        if (entity == null) {
            return null;
        }
        return TradeResponse.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .createTime(entity.getCreateTime())
                .updateTime(entity.getUpdateTime())
                .build();
    }

}
