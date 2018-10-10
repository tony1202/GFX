package com.gfx.web.app.stock.controller;

import com.gfx.web.app.stock.dto.StorageDto;
import com.gfx.web.app.stock.dto.StoragePagination;
import com.gfx.web.app.stock.service.StorageService;
import com.gfx.web.base.dto.VMSResponse;
import com.gfx.web.base.dto.VMSResponseFactory;
import com.gfx.web.base.util.UUIDUtils;
import com.gfx.web.common.entity.Storage;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
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
    @RequiresAuthentication
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

    /**
     * 库存分页查询
     * @param pagination 分页条件
     * @return
     */
    @GetMapping("/getStorageList")
    @RequiresAuthentication
    public Map<String,Object> getStorageList(StoragePagination pagination){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        Map<String,Object> map = storageService.getStorageList(pagination);
        if (MapUtils.isNotEmpty(map)){
            vmsResponse.setResponseBodyTotal((Long) map.get("total"));
            vmsResponse.setCustomerInfo("rows",(List<StorageDto>)map.get("data"));
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
        }
        return vmsResponse.generateResponseBody();
    }

    /**
     * 库存导出
     */
    @GetMapping("/exportStorageRecord")
    public void exportStorageRecord(StoragePagination pagination, HttpServletResponse response) throws IOException {
        String fileName = UUIDUtils.timeUUID()+".xlsx";
        File file = null;
        FileInputStream in = null;
        try {
            file = storageService.exportStorageRecord(pagination,StorageDto.class);
             in = new FileInputStream(file);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            FileCopyUtils.copy(in,response.getOutputStream());
        } catch (Exception e) {
            log.warn("export is error ->",e);
        } finally {
            if (file!=null && file.exists()){
                file.delete();
            }
            if (in!=null){
                in.close();
            }
        }
    }

    /**
     * 跟新库存
     * @param storage 库存信息
     * @return
     */
    @PostMapping("/updateStorage")
    @RequiresPermissions("storage:update")
    public Map<String,Object> updateStorage(@RequestBody Storage storage){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        boolean res =  storageService.updateStorage(storage);
        if (res){
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
        }
        return vmsResponse.generateResponseBody();
    }

    /**
     * 删除库存记录
     * @param storageId 库存id
     * @return
     */
    @DeleteMapping("/deleteStorage/{storageId}")
    @RequiresPermissions("storage:delete")
    public Map<String,Object> deleteStorage(@PathVariable String storageId){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        if (StringUtils.isNotBlank(storageId)){
            boolean res = storageService.deleteStorage(storageId);
            if (res){
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        }
        return vmsResponse.generateResponseBody();
    }
}
