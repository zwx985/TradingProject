package com.laoz.trading.project.service.impl;

import com.laoz.trading.project.converter.TradeConverter;
import com.laoz.trading.project.dto.TradeAddRequest;
import com.laoz.trading.project.dto.TradeAllListResponse;
import com.laoz.trading.project.dto.TradeQueryRequest;
import com.laoz.trading.project.dto.TradeResponse;
import com.laoz.trading.project.dto.TradeSearchResponse;
import com.laoz.trading.project.dto.TradeUpdateRequest;
import com.laoz.trading.project.entity.TradeEntity;
import com.laoz.trading.project.mapper.TradeMapper;
import com.laoz.trading.project.service.TradeService;
import com.laoz.trading.project.util.IDUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public TradeAllListResponse allList() {
        log.info("Start querying all trade records");
        List<TradeEntity> entityList = tradeMapper.allList();
        if (entityList == null || entityList.isEmpty()) {
            log.warn("No trade records found");
            return new TradeAllListResponse(0L, BigDecimal.ZERO, Collections.emptyList());
        }
        List<TradeResponse> result = entityList.stream()
                .map(TradeConverter::toResponse)
                .toList();
        log.info("Found {} trade records", result.size());
        return new TradeAllListResponse(result.size(), sum(), result);
    }

    @Override
    public String add(TradeAddRequest request) {
        log.info("Start adding trade record");
        LocalDate createTime = request.getCreateTime() != null ? request.getCreateTime() : LocalDate.now();

        long count = tradeMapper.countByCreateTime(createTime);
        if (count > 0) {
            log.warn("Trade record already exists for date: {}", createTime);
            throw new IllegalStateException("A trade record already exists for this date");
        }

        TradeEntity entity = TradeConverter.toEntity(request);
        entity.setId(IDUtils.uuid());
        entity.setCreateTime(createTime);
        entity.setUpdateTime(LocalDate.now());
        tradeMapper.insert(entity);
        log.info("Trade record added successfully, id: {}", entity.getId());
        return entity.getId();
    }

    @Override
    public BigDecimal sum() {
        log.info("Start calculating total trade amount");
        BigDecimal total = tradeMapper.sum();
        if (total == null) {
            total = BigDecimal.ZERO;
        }
        log.info("Total trade amount: {}", total);
        return total;
    }

    @Override
    public List<TradeSearchResponse> search(TradeQueryRequest request) {
        log.info("Start searching trade records with conditions");
        List<TradeEntity> entityList = tradeMapper.search(request);
        if (entityList == null || entityList.isEmpty()) {
            log.warn("No trade records match the search criteria");
            return Collections.emptyList();
        }
        List<TradeSearchResponse> result = entityList.stream()
                .map(TradeConverter::toSearchResponse)
                .toList();
        log.info("Found {} trade records matching the criteria", result.size());
        return result;
    }

    @Override
    public String update(TradeUpdateRequest request) {
        log.info("Start updating trade record, id: {}", request.getId());
        TradeEntity entity = tradeMapper.selectById(request.getId());
        if (entity == null) {
            log.warn("Trade record not found, id: {}", request.getId());
            throw new IllegalArgumentException("Trade record not found");
        }
        entity.setAmount(new BigDecimal(request.getAmount()));
        entity.setUpdateTime(LocalDate.now());
        tradeMapper.updateById(entity);
        log.info("Trade record updated successfully, id: {}", entity.getId());
        return entity.getId();
    }

    @Override
    public void delete(String id) {
        log.info("Start deleting trade record, id: {}", id);
        tradeMapper.deleteById(id);
        log.info("Trade record deleted successfully, id: {}", id);
    }

    @Override
    public TradeResponse today() {
        LocalDate createTime = LocalDate.now();
        TradeQueryRequest tradeQueryRequest = new TradeQueryRequest();
        tradeQueryRequest.setCreateTime(createTime);
        List<TradeEntity> search = tradeMapper.search(tradeQueryRequest);
        if (search == null || search.isEmpty()) {
            log.warn("No trade records found");
            return null;
        }
        return TradeConverter.toResponse(search.getFirst());
    }

}
