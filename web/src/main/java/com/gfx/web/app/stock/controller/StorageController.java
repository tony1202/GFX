package com.gfx.web.app.stock.controller;

import com.gfx.web.app.stock.service.StorageService;
import com.gfx.web.base.dto.VMSResponse;
import com.gfx.web.base.dto.VMSResponseFactory;
import com.gfx.web.common.entity.Storage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/27
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    private static final Logger log = LoggerFactory.getLogger(StorageController.class);

    private StorageService storageService;
    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * 更具条件查询库存
     * @param goodsId 货物di
     * @param goodsType 货物类型
     * @param goodsQuality 货物成色
     * @return
     */
    @GetMapping("/getStorageAjax")
    public Map<String,Object> getStorageAjax(@RequestParam("goodsId") String goodsId,
                                             @RequestParam("goodsType")String goodsType,
                                             @RequestParam("goodsQuality")String goodsQuality){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        if (StringUtils.isNoneBlank(goodsId,goodsType,goodsQuality)){
            List<Storage> list = storageService.getStorageAjax(goodsId,goodsType,goodsQuality);
            if (list.size()>0){
                vmsResponse.setResponseBodyData(list);
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        }
        return vmsResponse.generateResponseBody();
    }
}
