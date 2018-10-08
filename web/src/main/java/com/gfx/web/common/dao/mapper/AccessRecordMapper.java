package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.AccessRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface AccessRecordMapper extends Mapper<AccessRecord> {
    List<AccessRecord> getAccessRecords(@Param("params") Map<String, Object> params);
}