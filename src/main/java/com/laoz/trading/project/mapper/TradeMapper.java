package com.laoz.trading.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laoz.trading.project.dto.TradeQueryRequest;
import com.laoz.trading.project.entity.TradeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Trade Mapper Interface
 * 交易数据访问接口
 */
@Mapper
public interface TradeMapper extends BaseMapper<TradeEntity> {

    /**
     * Query all trade records
     * 查询全部交易记录
     *
     * @return list of trade entities 交易实体列表
     */
    List<TradeEntity> allList();

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
     * @return list of matched trade entities 匹配的交易实体列表
     */
    List<TradeEntity> search(@Param("request") TradeQueryRequest request);

    /**
     * Count records by creation date
     * 按创建日期统计记录数
     *
     * @param createTime creation date 创建日期
     * @return record count 记录数量
     */
    Long countByCreateTime(@Param("createTime") LocalDate createTime);

}
