package com.laoz.trading.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laoz.trading.project.entity.TradeEntity;
import org.apache.ibatis.annotations.Mapper;

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

}
