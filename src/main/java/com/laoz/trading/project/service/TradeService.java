package com.laoz.trading.project.service;

import com.laoz.trading.project.dto.TradeResponse;

import java.util.List;

public interface TradeService {

    /**
     * 查询全部交易记录
     *
     * @return 交易记录列表
     */
    List<TradeResponse> allList();

}
