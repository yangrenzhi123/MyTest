package com.yang.test.java.springcloud.all.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

@SpringBootApplication
public class SentinelStartup {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println(System.getProperty("csp.sentinel.dashboard.server"));
		
		initFlowRules();
		
		SpringApplication.run(SentinelStartup.class, args);
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