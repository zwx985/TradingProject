package com.laoz.trading.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.laoz.trading.project.common.request.PageRequest;
import com.laoz.trading.project.common.response.PageResult;
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
import java.util.Comparator;
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

    /**
     * Result holder for maximum cumulative profit calculation
     * 最大累计收益计算结果容器
     */
    private record MaxCumulativeResult(BigDecimal amount, LocalDate date) {
    }

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

        MaxCumulativeResult maxResult = calculateMaxCumulative(entityList);
        TradeAllListResponse response = new TradeAllListResponse(result.size(), sum(), result, maxResult.amount(), maxResult.date());
        log.info("Found {} trade records, max cumulative profit: {} on {}", result.size(), maxResult.amount(), maxResult.date());
        return response;
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
    public PageResult<TradeSearchResponse> search(PageRequest<TradeQueryRequest> pageRequest) {
        log.info("Start searching trade records with conditions");
        long current = pageRequest.getPageIndex() != null ? pageRequest.getPageIndex() : 1;
        long size = pageRequest.getPageSize() != null ? pageRequest.getPageSize() : 10;
        Page<TradeEntity> page = new Page<>(current, size);

        Page<TradeEntity> resultPage = tradeMapper.search(page, pageRequest.getRequest());
        List<TradeSearchResponse> records = resultPage.getRecords().stream()
                .map(TradeConverter::toSearchResponse)
                .toList();

        log.info("Found {} trade records matching the criteria", resultPage.getTotal());
        return new PageResult<>(records, resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), resultPage.getPages());
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
        Page<TradeEntity> page = new Page<>(1, 1);
        Page<TradeEntity> result = tradeMapper.search(page, tradeQueryRequest);
        if (result.getRecords() == null || result.getRecords().isEmpty()) {
            log.warn("No trade records found");
            return null;
        }
        return TradeConverter.toResponse(result.getRecords().getFirst());
    }

    /**
     * Calculate maximum cumulative profit (prefix sum) from trade records.
     * Records are sorted by createTime ascending before accumulation.
     * 根据交易记录计算最大累计收益（前缀和）。记录会按创建日期升序排序后累加。
     *
     * @param entityList trade entity list 交易实体列表
     * @return max cumulative amount and its corresponding date 最大累计金额及对应日期
     */
    private MaxCumulativeResult calculateMaxCumulative(List<TradeEntity> entityList) {
        BigDecimal cumulative = BigDecimal.ZERO;
        BigDecimal maxCumulative = null;
        LocalDate maxCumulativeDate = null;
        List<TradeEntity> sorted = entityList.stream()
                .sorted(Comparator.comparing(TradeEntity::getCreateTime))
                .toList();
        for (TradeEntity entity : sorted) {
            cumulative = cumulative.add(entity.getAmount());
            if (maxCumulative == null || cumulative.compareTo(maxCumulative) > 0) {
                maxCumulative = cumulative;
                maxCumulativeDate = entity.getCreateTime();
            }
        }
        return new MaxCumulativeResult(maxCumulative, maxCumulativeDate);
    }

}
