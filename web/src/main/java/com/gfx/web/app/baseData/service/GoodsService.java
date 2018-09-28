package com.gfx.web.app.baseData.service;

import com.gfx.web.app.baseData.dto.GoodsDto;
import com.gfx.web.base.dto.Pagination;
import com.gfx.web.common.entity.Goods;

import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/18
 */
public interface GoodsService {

    /**
     * 新增货物
     * @param goods 货物
     */
    String addGoods(Goods goods);

    /**
     * 查询货物列表
     * @param pagination 查询参数
     * @return 分页结果 key:data-货物列表;key:total-查询总数
     */
    Map<String,Object> getGoodsList(Pagination pagination);

    /**
     * 更新货物信息
     * @param goods 货物
     * @return 更新结果
     */
    String updateGoods(Goods goods);

    /**
     * 通过ajax请求获取货物列表
     * @param goodsId 货物id
     * @param goodsType 货物类型
     * @return
     */
    List<GoodsDto> getGoodsListAjax(String goodsId, String goodsType);
}
