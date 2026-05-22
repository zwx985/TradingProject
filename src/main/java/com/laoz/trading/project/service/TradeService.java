package com.laoz.trading.project.service;

import com.laoz.trading.project.dto.TradeResponse;

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
     * @return list of trade responses 交易响应列表
     */
    List<TradeResponse> allList();
}
