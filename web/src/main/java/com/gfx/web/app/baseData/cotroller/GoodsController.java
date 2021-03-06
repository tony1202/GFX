package com.gfx.web.app.baseData.cotroller;

import com.gfx.web.app.baseData.dto.GoodsDto;
import com.gfx.web.app.baseData.service.GoodsService;
import com.gfx.web.app.constant.CommonConstant;
import com.gfx.web.base.dto.Pagination;
import com.gfx.web.base.dto.VMSResponse;
import com.gfx.web.base.dto.VMSResponseFactory;
import com.gfx.web.base.util.UUIDUtils;
import com.gfx.web.common.entity.Goods;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/18
 * @Description:
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);
    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 查询商品列表
     * @return 分页数据
     */
    @GetMapping("/getGoodsList")
    @RequiresAuthentication
    public Map<String,Object> getGoodsList(Pagination pagination){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            Map<String, Object> goodsList = goodsService.getGoodsList(pagination);
            if (goodsList!=null){
                List<GoodsDto> rows = (List<GoodsDto>) goodsList.get("data");
                vmsResponse.setCustomerInfo("rows",rows);
                vmsResponse.setResponseBodyTotal((Long) goodsList.get("total"));
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("goodsList is error -->{}",e.getMessage());
        }
        return vmsResponse.generateResponseBody();
    }

    /**
     * 新增
     * @param goods 货物
     * @return 响应
     */
    @PostMapping("/addGoods")
    @RequiresPermissions("goods:add")
    public Map<String, Object> addGoods(@RequestBody Goods goods){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        if (StringUtils.equalsIgnoreCase(goods.getType(), CommonConstant.GoodsConstant.GOOD_TYPE_TAPE)){
            goods.setId(UUIDUtils.uuid("G"));
        }
        try {
            String res = goodsService.addGoods(goods);
            if (StringUtils.equalsIgnoreCase("ok",res)){
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }else {
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
            }
        } catch (Exception e) {
            log.info("add goods error ->{}",e);
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        }

        return vmsResponse.generateResponseBody();
    }

    /**
     * 更新货物
     * @param goods 货物
     * @return
     */
    @PostMapping("/updateGoods")
    @RequiresPermissions("goods:update")
    public Map<String,Object> updateGoods(@RequestBody Goods goods){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        if (goods!=null){
            String res = goodsService.updateGoods(goods);
            if (StringUtils.equalsIgnoreCase("ok",res)){
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }else {

                vmsResponse.setResponseBodyMsg("货物已存在");
            }
        }
        return vmsResponse.generateResponseBody();
    }

    /**
     * 通过ajax获取货物列表
     * @param goodsId 货物id
     * @param goodsType 货物类型
     * @return
     */
    @GetMapping("/getGoodsListAjax")
    public Map<String,Object> getGoodsListAjax(@RequestParam("goodsId")String goodsId,@RequestParam("goodsType")String goodsType){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        if (StringUtils.isNotBlank(goodsType)){
            //货物id支持模糊查询,不区分大小写
            List<GoodsDto> list = goodsService.getGoodsListAjax(goodsId,goodsType);
            if (list.size()>0){
                vmsResponse.setResponseBodyTotal((long)list.size());
                vmsResponse.setCustomerInfo("rows",list);
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        }

        return vmsResponse.generateResponseBody();
    }
}
