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

@Slf4j
@RestController
@RequestMapping("/v1/trade-project")
public class TradeController {

    @Resource
    private TradeService tradeService;

    @GetMapping("/all-list")
    public Response<List<TradeResponse>> allList() {
        log.info("Query all trade records");
        return Response.success(tradeService.allList());
    }
}
