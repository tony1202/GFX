package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.Dir;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DirMapper extends Mapper<Dir> {
    List<Dir> getListDirByType(@Param("type") String type);

}