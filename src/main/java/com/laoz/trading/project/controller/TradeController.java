package com.laoz.trading.project.controller;

import com.laoz.trading.project.common.response.Response;
import com.laoz.trading.project.dto.TradeResponse;
import com.laoz.trading.project.service.TradeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Trade REST API Controller
 * 交易 REST API 控制器
 */
@Slf4j
@RestController
@RequestMapping("/v1/trade-project")
public class TradeController {

    @Resource
    private TradeService tradeService;

    /**
     * Query all trade records
     * 查询全部交易记录
     *
     * @return list of trade responses 交易响应列表
     */
    @GetMapping("/all-list")
    public Response<List<TradeResponse>> allList() {
        log.info("Query all trade records");
        return Response.success(tradeService.allList());
    }
}
