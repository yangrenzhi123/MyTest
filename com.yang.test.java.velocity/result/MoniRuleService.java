package com.lyzh.saas.console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;

import com.lyzh.msa.framework.common.entity.console.MoniRule;
import com.lyzh.msa.framework.common.dto.console.MoniRuleDTO;
import com.lyzh.saas.console.rpc.MoniRuleRPC;
import com.lyzh.msa.framework.common.entity.PageDataInfo;







/**
 * 模拟程序规则
 * 
 * @author yangrenzhi
 * @date  2018-11-14 17:58
 * @Description  
 * @since 4.0.1
 */
@Service
public class MoniRuleService {
	
	@Autowired
	MoniRuleRPC rpc;
	
	
	/**
	 * 分页查询
	 * @param 
	 * @return
	 */
	public PageDataInfo<MoniRuleDTO> pageList(MoniRuleDTO dto){
		PageInfo<MoniRuleDTO> pageInfo = rpc.page(dto);
		PageDataInfo<MoniRuleDTO> dataInfo = new PageDataInfo<>();
		dataInfo.setPageNum(pageInfo.getPageNum());
		dataInfo.setPageSize(pageInfo.getPageSize());
		dataInfo.setTotal(pageInfo.getTotal());
		dataInfo.setData(pageInfo.getList());
		return dataInfo;
	}
	
	/**
	 * 获取模拟程序规则列表
	 * @return
	 */
	public List<MoniRule> find() {
		return rpc.find();
	}

	/**
	 * 新增
	 * @return
	 */
	public void save(MoniRule entity) {
		rpc.insert(entity);
	}
	
	/**
	 * 修改
	 * @return
	 */
	public void update(MoniRule entity) {
		rpc.update(entity);
	}
	
	/**
	 * 删除
	 * @return
	 */
	public void delete(MoniRule entity) {
		rpc.delete(entity);
	}
}