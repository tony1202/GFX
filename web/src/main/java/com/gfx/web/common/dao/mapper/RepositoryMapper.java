package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.Repository;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface RepositoryMapper extends Mapper<Repository> {
    List<Repository> getRepositoryList(@Param("params") Map<String, Object> params);

    int isExists(@Param("repository") Repository repository);
}