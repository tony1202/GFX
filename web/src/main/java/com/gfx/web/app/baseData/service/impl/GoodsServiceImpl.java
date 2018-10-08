package com.gfx.web.app.baseData.service.impl;

import com.gfx.web.app.baseData.dto.GoodsDto;
import com.gfx.web.app.baseData.service.GoodsService;
import com.gfx.web.app.constant.CommonConstant;
import com.gfx.web.base.dto.Pagination;
import com.gfx.web.base.operate.UserOperation;
import com.gfx.web.common.dao.mapper.GoodsMapper;
import com.gfx.web.common.entity.Goods;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/18
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    private final static Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);
    private GoodsMapper goodsMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    /**
     * 新增货物
     *
     * @param goods 货物
     */
    @Override
    @UserOperation(value = "新增货物")
    public String addGoods(Goods goods) {
        String result = "no";
        if (goods != null) {
            int insert = goodsMapper.insert(goods);
            if (insert > 0)
                result = "ok";
        }
        return result;
    }

    /**
     * 查询货物列表
     *
     * @param pagination 查询参数
     * @return 分页结果 key:data-货物列表;key:total-查询总数
     */
    @Override
    public Map<String, Object> getGoodsList(Pagination pagination) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        switch (pagination.getSearchType()) {
            //查询所有
            case CommonConstant.GoodsConstant.SEARCH_TYPE_ALL:
                break;
            //根据id查询
            case CommonConstant.GoodsConstant.SEARCH_TYPE_ID:
                params.put("id", pagination.getKeyWord().toUpperCase());
                break;
            //根据尺寸查询,支持模糊查询
            case CommonConstant.GoodsConstant.SEARCH_TYPE_SIZE:
                params.put("scale", pagination.getKeyWord());
                break;
            //其他
            default:
                break;
        }
        Page page = null;
        if (pagination.getLimit() >= 0 && pagination.getOffset() >= 0) {
            page = PageHelper.startPage(pagination.getOffset() + 1, pagination.getLimit(), true);
        }
        List<GoodsDto> list = goodsMapper.getGoodsByPage(params);
        if (page!=null){
            result.put("total", page.getTotal());
        }else {
            result.put("total",(long)list.size());
        }
        result.put("data", list);

        return result;
    }

    /**
     * 更新货物信息
     *
     * @param goods 货物
     * @return 更新结果
     */
    @Override
    public String updateGoods(Goods goods) {
        String result = "no";
        int i = goodsMapper.updateByPrimaryKeySelective(goods);
        if (i > 0)
            result = "ok";
        return result;
    }

    /**
     * 通过ajax请求获取货物列表
     *
     * @param goodsId   货物id
     * @param goodsType 货物类型
     * @return
     */
    @Override
    public List<GoodsDto> getGoodsListAjax(String goodsId, String goodsType) {
        if (goodsId!=null){
            //转大写
            goodsId = goodsId.toUpperCase();
        }
        return goodsMapper.getGoodsListAjax(goodsId,goodsType);
    }
}
