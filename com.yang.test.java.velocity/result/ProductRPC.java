package com.lyzh.saas.console.rpc;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyzh.msa.framework.common.entity.console.Product;

/**
 * @Auther: yrz
 * @Date: 2018-10-29 16:27
 * @Description: 获取产品 DAO 接口数据
 */
@FeignClient("com-lyzh-msa-dao")
public interface ProductRPC {
	/**
	 * 新增
	 */
	@RequestMapping("/dao/console/Product/insert")
	String insert(Product entity);
	

	/**
	 * 修改
	 */
	@RequestMapping("/dao/console/Product/update")
	String update(Product entity);
	

	/**
	 * 列表
	 */
	@RequestMapping("/dao/console/Product/find")
	List<Product> find();
}