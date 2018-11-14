package com.lyzh.saas.console.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lyzh.msa.framework.common.entity.console.MoniRule;
import com.lyzh.saas.console.ConsoleApplication;
import com.lyzh.saas.console.service.MoniRuleService;

@SpringBootTest(classes = ConsoleApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MoniRuleServiceTest {

	@Autowired
	MoniRuleService moniRuleService;


	@Test
	public void find() {
		String id = UUID.randomUUID().toString();
		
		MoniRule entity = new MoniRule();
		moniRuleService.save(entity);
		
		moniRuleService.update(entity);
		
		
		
		List<MoniRule> l = moniRuleService.find();
		System.out.println(l.size());
		
		MoniRuleDTO dto = new MoniRuleDTO();
		dto.setPageNum(1);
		dto.setPageSize(10);
		PageDataInfo<MoniRuleDTO> p = moniRuleService.page(dto);
		System.out.println(p);
		
		
		moniRuleService.delete(entity);
	}
}