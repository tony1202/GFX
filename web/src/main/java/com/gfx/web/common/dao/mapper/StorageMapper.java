package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface StorageMapper extends Mapper<Storage> {
    /**
     * 判断是否存
     * @param goodsId 货物id
     * @param goodsQuality 成色
     * @return
     */
    int checkExist(@Param("goodsId") String goodsId, @Param("goodsQuality") String goodsQuality);

    /**
     * 跟新库存
     * @param storage
     */
    void updateStorage(@Param("storage") Storage storage);
}