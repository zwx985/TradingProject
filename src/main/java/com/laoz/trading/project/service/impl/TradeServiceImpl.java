package com.laoz.trading.project.service.impl;

import com.laoz.trading.project.converter.TradeConverter;
import com.laoz.trading.project.dto.TradeResponse;
import com.laoz.trading.project.entity.TradeEntity;
import com.laoz.trading.project.mapper.TradeMapper;
import com.laoz.trading.project.service.TradeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

}
