package com.lyzh.saas.console.controller;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyzh.msa.framework.common.entity.MsgInfo;
import com.lyzh.msa.framework.common.entity.console.MoniRule;
import com.lyzh.msa.framework.common.exception.TipException;
import com.lyzh.saas.console.service.MoniRuleService;

/**
 * 
 * @author yangrenzhi
 * @date  2018/10/26 17:17
 * @Description   模拟程序规则API接口
 * @since
  */
@RestController
@RequestMapping("/monirule")
public class MoniRuleController {

	@Autowired
	MoniRuleService moniRuleService;
	
	/**
	 * 列表
	 * 
	 * @return
	 * @throws TipException 
	 */
	@PostMapping("/l")
	public MsgInfo<List<MoniRule>> l() throws TipException {
		MsgInfo<List<MoniRule>> msgInfo = new MsgInfo<>();
		List<MoniRule> l = moniRuleService.find();
		msgInfo.setData(l);
		return msgInfo;
	}
	
	
	/**
	 * 获取模拟程序规则列表
	 * @param
	 * @return
	 */
	@PostMapping("/l")
	public PageDataInfo<MoniRuleDTO> l(@RequestBody MoniRuleDTO dto){
		PageDataInfo<MoniRuleDTO> dataInfo = new PageDataInfo<>();
		Integer pageNum = dto.getPageNum();
		Integer pageSize = dto.getPageSize();
		if(pageNum==null || pageNum.intValue()==0){
			dataInfo.setCode(CodeEnum.PARAMS_FAILTURE.getCode());
			dataInfo.setMessage("页码传入不正确");
			return dataInfo;
		}
		if(pageSize==null || pageSize.intValue()==0){
			dataInfo.setCode(CodeEnum.PARAMS_FAILTURE.getCode());
			dataInfo.setMessage("分页条数传入不正确");
			return dataInfo;
		}

		dataInfo = moniRuleService.pageList(dto);
		dataInfo.setCode(CodeEnum.SUCESS.getCode());
		dataInfo.setMessage("success");
		return dataInfo;
	}
	

	/**
	 * 新增
	 * 
	 * @return
	 * @throws TipException 
	 */
	@PostMapping("/c")
	public MsgInfo<String> c(@RequestBody MoniRule entity) throws TipException {
		MsgInfo<String> msgInfo = new MsgInfo<>();
		

		return msgInfo;
	}
	

	/**
	 * 修改
	 * 
	 * @return
	 * @throws TipException 
	 */
	@PostMapping("/u")
	public MsgInfo<String> u(@RequestBody MoniRule entity) throws TipException {
		MsgInfo<String> msgInfo = new MsgInfo<>();
		
		
		
		return msgInfo;
	}
	

	/**
	 * 删除
	 * 
	 * @return
	 * @throws TipException 
	 */
	@PostMapping("/d")
	public MsgInfo<String> d(@RequestBody MoniRule entity) throws TipException {
		MsgInfo<String> msgInfo = new MsgInfo<>();
		
		
		
		return msgInfo;
	}
}