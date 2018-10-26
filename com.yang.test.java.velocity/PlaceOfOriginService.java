package com.lyzh.saas.console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyzh.msa.framework.common.entity.console.PlaceOfOrigin;
import com.lyzh.saas.console.rpc.PlaceOfOriginRPC;

/**
 * 角色管理
 * 
 * @author yangrenzhi
 * @date  2018-10-26 17:14
 * @Description  
 * @since 4.0.1
 */
@Service
public class PlaceOfOriginService {
	
	@Autowired
	PlaceOfOriginRPC rpc;
	
	/**
	 * 获取产品分组列表
	 * @return
	 */
	public List<PlaceOfOrigin> find() {
		return rpc.find();
	}
}