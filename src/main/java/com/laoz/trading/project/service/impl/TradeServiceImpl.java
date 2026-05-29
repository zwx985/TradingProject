package com.laoz.trading.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoz.trading.project.common.request.PageRequest;
import com.laoz.trading.project.common.response.PageResult;
import com.laoz.trading.project.converter.TradeConverter;
import com.laoz.trading.project.dto.MaxCumulativeProfit;
import com.laoz.trading.project.dto.MaxDailyProfit;
import com.laoz.trading.project.dto.MaxSubarrayProfit;
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

        MaxCumulativeProfit maxCumulative = calculateMaxCumulative(entityList);
        MaxDailyProfit maxDaily = calculateMaxDailyProfit(entityList);
        MaxSubarrayProfit maxSubarray = calculateMaxSubarrayProfit(entityList);
        TradeAllListResponse response = new TradeAllListResponse(
                result.size(), sum(), result, maxCumulative, maxDaily, maxSubarray);
        log.info("Found {} trade records, max cumulative: {} on {}, max daily: {} on {}, max subarray: {} from {} to {}",
                result.size(),
                maxCumulative.getAmount(), maxCumulative.getDate(),
                maxDaily.getAmount(), maxDaily.getDate(),
                maxSubarray.getAmount(), maxSubarray.getStartDate(), maxSubarray.getEndDate());
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
    private MaxCumulativeProfit calculateMaxCumulative(List<TradeEntity> entityList) {
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
        return new MaxCumulativeProfit(maxCumulative, maxCumulativeDate);
    }

    /**
     * Calculate maximum single-day profit from trade records.
     * 计算单日最大收益。
     *
     * @param entityList trade entity list 交易实体列表
     * @return max single-day amount and its corresponding date 最大单日金额及对应日期
     */
    private MaxDailyProfit calculateMaxDailyProfit(List<TradeEntity> entityList) {
        TradeEntity maxEntity = entityList.stream()
                .max(Comparator.comparing(TradeEntity::getAmount))
                .orElse(null);
        if (maxEntity == null) {
            return new MaxDailyProfit(null, null);
        }
        return new MaxDailyProfit(maxEntity.getAmount(), maxEntity.getCreateTime());
    }

    /**
     * Calculate maximum subarray profit using Kadane's algorithm.
     * Records are sorted by createTime ascending before processing.
     * 使用 Kadane 算法计算最大子数组收益。记录会按创建日期升序排序后处理。
     *
     * @param entityList trade entity list 交易实体列表
     * @return max subarray sum and its interval dates 最大子数组和及起止日期
     */
    private MaxSubarrayProfit calculateMaxSubarrayProfit(List<TradeEntity> entityList) {
        List<TradeEntity> sorted = entityList.stream()
                .sorted(Comparator.comparing(TradeEntity::getCreateTime))
                .toList();

        BigDecimal maxSum = null;
        LocalDate startDate = null;
        LocalDate endDate = null;

        BigDecimal currentSum = BigDecimal.ZERO;
        LocalDate currentStart = null;

        for (TradeEntity entity : sorted) {
            BigDecimal amount = entity.getAmount();
            LocalDate date = entity.getCreateTime();

            if (currentSum.compareTo(BigDecimal.ZERO) < 0) {
                currentSum = amount;
                currentStart = date;
            } else {
                currentSum = currentSum.add(amount);
            }

            if (maxSum == null || currentSum.compareTo(maxSum) > 0) {
                maxSum = currentSum;
                startDate = currentStart;
                endDate = date;
            }
        }

        return new MaxSubarrayProfit(maxSum, startDate, endDate);
    }

}
