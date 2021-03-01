package com.yang.test.java.springcloud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

@SpringBootApplication
@MapperScan("com.yang.test.java.springboot.dao")
public class AllProviderStartup {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println(System.getProperty("csp.sentinel.dashboard.server"));
		
		initFlowRules();
		
		SpringApplication.run(AllProviderStartup.class, args);
	}

	private static void initFlowRules() {
		List<FlowRule> rules = new ArrayList<>();
		FlowRule rule = new FlowRule();
		rule.setResource("HelloWorld");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// Set limit QPS to 20.
		rule.setCount(3);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}
}