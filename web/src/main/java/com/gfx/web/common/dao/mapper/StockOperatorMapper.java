package com.gfx.web.common.dao.mapper;

import com.gfx.web.app.stock.dto.StockRecordDto;
import com.gfx.web.common.entity.StockOperator;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@Repository
public interface StockOperatorMapper extends Mapper<StockOperator> {
    List<StockRecordDto> searchStockRecord(@Param("params") Map<String, Object> params);
}