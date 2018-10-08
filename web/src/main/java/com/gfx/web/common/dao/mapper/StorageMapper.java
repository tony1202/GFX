package com.gfx.web.common.dao.mapper;

import com.gfx.web.app.stock.dto.StorageDto;
import com.gfx.web.common.entity.Storage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface StorageMapper extends Mapper<Storage> {
    /**
     * 判断是否存
     * @param goodsId 货物id
     * @param goodsQuality 成色
     * @return
     */
    List<Storage> checkExist(@Param("goodsId") String goodsId, @Param("goodsQuality") String goodsQuality,@Param("goodsType")String goodsType,@Param("repositoryId")String repositoryId);

    List<Storage> getStorageAjax(@Param("goodsId") String goodsId, @Param("goodsType") String goodsType, @Param("goodsQuality") String goodsQuality,@Param("repositoryId")String repositoryId);

    List<StorageDto> getStorageList(@Param("params") Map<String, Object> params);
}