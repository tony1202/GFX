package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.Repository;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository
public interface RepositoryMapper extends Mapper<Repository> {

    List<Repository> getRepositoryList(Map<String, Object> params);

    int isExists(@Param("repository") Repository repository);
}