package com.gfx.web.base.config;


import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author tony
 * @date 2018/9/4
 * @Description:
 */
@Configuration
@MapperScan(basePackages = {"com.gfx.web.common.dao.mapper"})
public class MybatisConfig {
}
