package com.lyzh.saas.console.rpc;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyzh.msa.framework.common.entity.console.PlaceOfOrigin;

/**
 * @Auther: yrz
 * @Date: 2018-10-26 17:14
 * @Description: 获取实体中文 DAO 接口数据
 */
@FeignClient("com-lyzh-msa-dao")
public interface PlaceOfOriginRPC {
	/**
	 * 新增
	 */
	@RequestMapping("/dao/console/PlaceOfOrigin/insert")
	String insert(PlaceOfOrigin entity);
	

	/**
	 * 修改
	 */
	@RequestMapping("/dao/console/PlaceOfOrigin/update")
	String update(PlaceOfOrigin entity);
	

	/**
	 * 列表
	 */
	@RequestMapping("/dao/console/PlaceOfOrigin/find")
	List<PlaceOfOrigin> find();
}