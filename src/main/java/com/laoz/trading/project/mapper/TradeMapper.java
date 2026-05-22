package com.laoz.trading.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laoz.trading.project.entity.TradeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeMapper extends BaseMapper<TradeEntity> {

    /**
     * 查询全部交易记录
     *
     * @return 交易实体列表
     */
    List<TradeEntity> allList();

}
