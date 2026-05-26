package com.laoz.trading.project.controller;

import com.laoz.trading.project.common.request.PageRequest;
import com.laoz.trading.project.common.response.PageResult;
import com.laoz.trading.project.common.response.Response;
import com.laoz.trading.project.dto.TradeAddRequest;
import com.laoz.trading.project.dto.TradeAllListResponse;
import com.laoz.trading.project.dto.TradeQueryRequest;
import com.laoz.trading.project.dto.TradeResponse;
import com.laoz.trading.project.dto.TradeSearchResponse;
import com.laoz.trading.project.dto.TradeUpdateRequest;
import com.laoz.trading.project.service.TradeService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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
     * Query all trade records with total count
     * 查询全部交易记录（包含总条数）
     *
     * @return all list response including total count 包含总条数的全部记录响应
     */
    @GetMapping("/all-list")
    public Response<TradeAllListResponse> allList() {
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

    /**
     * Sum all trade amounts
     * 统计所有交易金额合计
     *
     * @return total amount 金额合计
     */
    @GetMapping("/sum")
    public Response<BigDecimal> sum() {
        log.info("Query total trade amount");
        return Response.success(tradeService.sum());
    }

    /**
     * Search trade records by dynamic conditions with pagination
     * 根据动态条件搜索交易记录（支持分页）
     *
     * @param pageRequest page request wrapping query conditions and pagination parameters
     *                    分页请求，包含查询条件和分页参数
     * @return paged trade search responses 分页后的交易搜索响应
     */
    @PostMapping("/search")
    public Response<PageResult<TradeSearchResponse>> search(@RequestBody PageRequest<TradeQueryRequest> pageRequest) {
        log.info("Search trade records, pageIndex: {}, pageSize: {}", pageRequest.getPageIndex(), pageRequest.getPageSize());
        return Response.success(tradeService.search(pageRequest));
    }

    /**
     * Update a trade record by ID
     * 根据 ID 修改交易记录
     *
     * @param request trade update request 交易修改请求
     * @return ID of the updated record 被修改记录的 ID
     */
    @PostMapping("/update")
    public Response<String> update(@Valid @RequestBody TradeUpdateRequest request) {
        log.info("Update trade record");
        return Response.success(tradeService.update(request));
    }

    /**
     * Delete a trade record by ID
     * 根据 ID 删除交易记录
     *
     * @param id primary key ID 主键ID
     * @return success response 成功响应
     */
    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable String id) {
        log.info("Delete trade record, id: {}", id);
        tradeService.delete(id);
        return Response.success();
    }

    /**
     * Query today trade record
     * 获取当日交易
     *
     * @return response 响应
     */
    @GetMapping("/today")
    public Response<TradeResponse> today() {
        log.info("get today trade records");
        return Response.success(tradeService.today());
    }
}
