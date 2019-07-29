package com.yang.test.java.springboot.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.google.common.collect.Lists;

@Configuration
@AutoConfigureBefore
@MapperScan("com.yang.test.java.springboot.dao")
public class MybatisPlusConfig {

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		ITableNameHandler h = new ITableNameHandler() {
			public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
				return "tt0"; //切片表名，可依据条件设置值
			}
		};

		Map<String, ITableNameHandler> m = new HashMap<>();
		m.put("tt", h); //逻辑表名

		DynamicTableNameParser d = new DynamicTableNameParser();
		d.setTableNameHandlerMap(m);

		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setSqlParserList(Lists.newArrayList(d));
		return paginationInterceptor;
	}
}