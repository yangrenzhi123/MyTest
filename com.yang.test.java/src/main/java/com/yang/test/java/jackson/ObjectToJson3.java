package com.yang.test.java.jackson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ObjectToJson3 {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		Map<String, RowChangeRule> rules = new HashMap<>();

		RowChangeRule htsaRule = new RowChangeRule();
		htsaRule.setColumnZzid("socreaccountzzid");
		htsaRule.setColumnUuid("scoreaccountid");
		htsaRule.setColumnTime("createtime");
		rules.put("h_tenant_socre_account", htsaRule);

		RowChangeRule hsrRule = new RowChangeRule();
		hsrRule.setColumnZzid("scorerecordzzid");
		hsrRule.setColumnUuid("scorerecordid");
		hsrRule.setColumnTime("createtime");
		rules.put("h_score_record", hsrRule);

		RowChangeRule hrlRule = new RowChangeRule();
		hrlRule.setColumnZzid("recycleloseweightzzid");
		hrlRule.setColumnUuid("recycleloseweightid");
		hrlRule.setColumnTime("createtime");
		rules.put("h_recycle_loseweight", hrlRule);

		RowChangeRule hirRule = new RowChangeRule();
		hirRule.setColumnZzid("inspectrecordzzid");
		hirRule.setColumnUuid("inspectrecordid");
		hirRule.setColumnTime("createtime");
		rules.put("h_inspect_record", hirRule);

		RowChangeRule hesrRule = new RowChangeRule();
		hesrRule.setColumnZzid("exchangescorerecordzzid");
		hesrRule.setColumnUuid("exchangescorerecordid");
		hesrRule.setColumnTime("createtime");
		rules.put("h_exchange_score_record", hesrRule);

		RowChangeRule hdrtRule = new RowChangeRule();
		hdrtRule.setColumnZzid("dispenserreplenishzzid");
		hdrtRule.setColumnUuid("dispenserreplenishid");
		hdrtRule.setColumnTime("createtime");
		rules.put("h_dispenser_replenish", hdrtRule);

		RowChangeRule hdotRule = new RowChangeRule();
		hdotRule.setColumnZzid("dispenserorderzzid");
		hdotRule.setColumnUuid("dispenserorderid");
		hdotRule.setColumnTime("createtime");
		rules.put("h_dispenser_order_third", hdotRule);

		RowChangeRule hgpRule = new RowChangeRule();
		hgpRule.setColumnZzid("garbagebagpullzzid");
		hgpRule.setColumnUuid("garbagebagpullid");
		hgpRule.setColumnTime("createtime");
		rules.put("h_garbagebag_pull", hgpRule);

		RowChangeRule htgRule = new RowChangeRule();
		htgRule.setColumnZzid("tenantgroupzzid");
		htgRule.setColumnUuid("tenantgroupid");
		rules.put("h_tenant_group", htgRule);
		
		RowChangeRule hrrRule = new RowChangeRule();
		hrrRule.setColumnZzid("recyclerecordzzid");
		hrrRule.setColumnUuid("recyclerecordid");
		hrrRule.setColumnTime("tfsj");
		rules.put("h_recycle_record", hrrRule);

		String grades = mapper.writeValueAsString(rules);
		System.out.println(grades);
	}
}