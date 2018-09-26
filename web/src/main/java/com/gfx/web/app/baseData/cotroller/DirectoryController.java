package com.gfx.web.app.baseData.cotroller;

import com.gfx.web.app.baseData.service.DirectoryService;
import com.gfx.web.base.dto.VMSResponse;
import com.gfx.web.base.dto.VMSResponseFactory;
import com.gfx.web.common.entity.Dir;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/18
 */
@RestController
@RequestMapping("/dir")
public class DirectoryController {

    private DirectoryService directoryService;
    @Autowired
    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping("/listDir")
    public Map<String,Object> getListDir(@RequestParam String cid){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        if (StringUtils.isNoneBlank(cid)){

            try {
                List<Dir> listDir = directoryService.getListDir(cid);
                vmsResponse.setResponseBodyData(listDir);
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
            }
        }else {
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        }
        return vmsResponse.generateResponseBody();
    }
}
