package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.OperationRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@Repository
public interface OperationRecordMapper extends Mapper<OperationRecord> {
    List<OperationRecord> getUserOperationRecords(@Param("params") Map<String, Object> params);
}