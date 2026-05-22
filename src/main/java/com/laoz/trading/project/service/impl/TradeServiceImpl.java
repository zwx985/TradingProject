package com.laoz.trading.project.service.impl;

import com.laoz.trading.project.converter.TradeConverter;
import com.laoz.trading.project.dto.TradeAddRequest;
import com.laoz.trading.project.dto.TradeResponse;
import com.laoz.trading.project.entity.TradeEntity;
import com.laoz.trading.project.mapper.TradeMapper;
import com.laoz.trading.project.service.TradeService;
import com.laoz.trading.project.util.IDUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Trade Service Implementation
 * 交易服务实现类
 */
@Slf4j
@Service
public class TradeServiceImpl implements TradeService {

    @Resource
    private TradeMapper tradeMapper;

    @Override
    public List<TradeResponse> allList() {
        log.info("Start querying all trade records");
        List<TradeEntity> entityList = tradeMapper.allList();
        if (entityList == null || entityList.isEmpty()) {
            log.warn("No trade records found");
            return Collections.emptyList();
        }
        List<TradeResponse> result = entityList.stream()
                .map(TradeConverter::toResponse)
                .toList();
        log.info("Found {} trade records", result.size());
        return result;
    }

    @Override
    public String add(TradeAddRequest request) {
        log.info("Start adding trade record");
        TradeEntity entity = TradeConverter.toEntity(request);
        entity.setId(IDUtils.uuid());
        entity.setCreateTime(entity.getCreateTime() != null ? entity.getCreateTime() : LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        tradeMapper.insert(entity);
        log.info("Trade record added successfully, id: {}", entity.getId());
        return entity.getId();
    }

}
