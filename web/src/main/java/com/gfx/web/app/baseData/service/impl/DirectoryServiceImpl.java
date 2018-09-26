package com.gfx.web.app.baseData.service.impl;

import com.gfx.web.app.baseData.service.DirectoryService;
import com.gfx.web.common.dao.mapper.DirMapper;
import com.gfx.web.common.entity.Dir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tony
 * @date 2018/9/18
 */
@Service
public class DirectoryServiceImpl implements DirectoryService {

    private DirMapper dirMapper;
    @Autowired
    public DirectoryServiceImpl(DirMapper dirMapper) {
        this.dirMapper = dirMapper;
    }

    /**
     * 根据字典类型获取字典数据集合
     *
     * @param cid 字典集合
     * @return
     */
    @Override
    public List<Dir> getListDir(String cid) {

        return dirMapper.getListDirByType(cid);
    }
}
