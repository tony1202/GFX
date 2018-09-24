package com.gfx.web.common.dao.mapper;

import com.gfx.web.base.dto.GoodsDto;
import com.gfx.web.common.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@Repository
public interface GoodsMapper extends Mapper<Goods> {
    List<GoodsDto> getGoodsByPage(@Param("params") Map<String, Object> params);
}