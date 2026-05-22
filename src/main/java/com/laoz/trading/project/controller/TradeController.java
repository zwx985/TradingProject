package com.laoz.trading.project.controller;

import com.laoz.trading.project.common.response.Response;
import com.laoz.trading.project.dto.TradeAddRequest;
import com.laoz.trading.project.dto.TradeResponse;
import com.laoz.trading.project.service.TradeService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * Add a new trade record
     * 新增一条交易记录
     *
     * @param request trade add request 交易新增请求
     * @return ID of the newly created record 新增记录的 ID
     */
    @PostMapping("/add")
    public Response<String> add(@Valid @RequestBody TradeAddRequest request) {
        log.info("Add trade record");
        return Response.success(tradeService.add(request));
    }

}
