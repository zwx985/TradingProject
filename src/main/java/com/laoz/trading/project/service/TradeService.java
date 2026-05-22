package com.laoz.trading.project.service;

import com.laoz.trading.project.dto.TradeAddRequest;
import com.laoz.trading.project.dto.TradeAllListResponse;
import com.laoz.trading.project.dto.TradeQueryRequest;
import com.laoz.trading.project.dto.TradeSearchResponse;
import com.laoz.trading.project.dto.TradeUpdateRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Trade Service Interface
 * 交易服务接口
 */
public interface TradeService {

    /**
     * Query all trade records
     * 查询全部交易记录
     *
     * @return all list response including total count 包含总条数的全部记录响应
     */
    TradeAllListResponse allList();

    /**
     * Add a new trade record
     * 新增一条交易记录
     *
     * @param request trade add request 交易新增请求
     * @return ID of the newly created trade record 新增交易记录的 ID
     */
    String add(TradeAddRequest request);

    /**
     * Sum all trade amounts
     * 统计所有交易金额合计
     *
     * @return total amount 金额合计
     */
    BigDecimal sum();

    /**
     * Search trade records by dynamic conditions
     * 根据动态条件搜索交易记录
     *
     * @param request query conditions 查询条件
     * @return list of matched trade search responses 匹配的交易搜索响应列表
     */
    List<TradeSearchResponse> search(TradeQueryRequest request);

    /**
     * Update a trade record by ID
     * 根据 ID 修改交易记录
     *
     * @param request trade update request 交易修改请求
     * @return ID of the updated record 被修改记录的 ID
     */
    String update(TradeUpdateRequest request);

    /**
     * Delete a trade record by ID
     * 根据 ID 删除交易记录
     *
     * @param id primary key ID 主键ID
     */
    void delete(String id);

}
