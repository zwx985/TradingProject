package com.laoz.trading.project.converter;

import com.laoz.trading.project.dto.TradeAddRequest;
import com.laoz.trading.project.dto.TradeResponse;
import com.laoz.trading.project.entity.TradeEntity;

import java.math.BigDecimal;

/**
 * Trade Object Converter
 * Utility class for converting between Entity and DTO
 * 交易对象转换器 - 实体与 DTO 之间的转换工具类
 */
public class TradeConverter {

    private TradeConverter() {
        // utility class, prevent instantiation
        // 工具类，禁止实例化
    }

    /**
     * Convert entity to response DTO
     * 将实体对象转换为响应 DTO
     *
     * @param entity trade entity 交易实体
     * @return trade response DTO, returns null when entity is null 交易响应 DTO，entity 为 null 时返回 null
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

    /**
     * Convert add request DTO to entity
     * 将新增请求 DTO 转换为实体对象
     *
     * @param request trade add request 交易新增请求
     * @return trade entity 交易实体
     */
    public static TradeEntity toEntity(TradeAddRequest request) {
        if (request == null) {
            return null;
        }
        return TradeEntity.builder()
                .amount(new BigDecimal(request.getAmount()))
                .createTime(request.getCreateTime())
                .build();
    }

}
