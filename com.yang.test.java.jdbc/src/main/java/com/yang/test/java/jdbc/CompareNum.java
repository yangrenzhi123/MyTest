package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompareNum {

	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL1 = "jdbc:mysql://192.168.10.20:3306/lyzhhw4?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String DB_URL2 = "jdbc:mysql://192.168.10.22:3306/lyzhhw4?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
	static final String USER = "root";
	static final String PASS = "Lenovo@@7788";

	static final int num = 10;
	static final List<Connection> l = new ArrayList<Connection>();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String[] ss = new String[] {
				"h_account",
				"h_account_role",
				"h_community",
				"h_device",
				"h_device_file",
				"h_device_type",
				"h_dictionary",
				"h_dispenser_goods",
				"h_dispenser_order",
				"h_dispenser_replenish",
				"h_equip_bin",
				"h_equip_bin_info",
				"h_equip_dispenser",
				"h_equip_electbalance",
				"h_equip_fixedvideo",
				"h_equipment",
				"h_equipment_update",
				"h_equipment_version",
				"h_exchange_record",
				"h_fee_name",
				"h_file",
				"h_free_bag_rule",
				"h_garbage_class",
				"h_garbagebag_pull",
				"h_group_account",
				"h_highest_rule",
				"h_inspect_record",
				"h_inspect_record_image",
				"h_inspect_rule",
				"h_menu",
				"h_merchant",
				"h_message_sender",
				"h_message_sender_community",
				"h_message_sender_module",
				"h_message_sms",
				"h_message_template",
				"h_notice",
				"h_operate_user",
				"h_order",
				"h_order_detail",
				"h_order_garbage",
				"h_order_menu",
				"h_order_menu_actions",
				"h_order_region",
				"h_order_store",
				"h_product",
				"h_product_detail",
				"h_product_detail_promotion",
				"h_product_device",
				"h_product_group",
				"h_product_menu",
				"h_product_module",
				"h_recycle_abnormal",
				"h_recycle_change_record",
				"h_recycle_record",
				"h_recycle_rule",
				"h_region",
				"h_replenish_content",
				"h_result",
				"h_role",
				"h_role_menu",
				"h_score_change_record",
				"h_score_record",
				"h_script_version",
				"h_script_version_record",
				"h_store",
				"h_store_order",
				"h_tenant",
				"h_tenant_account",
				"h_tenant_consult",
				"h_tenant_group",
				"h_tenant_role",
				"h_tenant_role_pro_menu",
				"h_tenant_role_pro_menu_actions",
				"h_tenant_role_sys_menu",
				"h_tenant_user",
				"h_tenant_user_community",
				"h_tenant_user_role",
				"h_threshold_rule",
				"h_threshold_rule_info",
				"h_throw_rule",
				"h_throw_rule_info"
		};
		
		Class.forName(DRIVER);

		Connection conn2 = DriverManager.getConnection(DB_URL1, USER, PASS);
		conn2.setAutoCommit(true);

		Connection conn = DriverManager.getConnection(DB_URL2, USER, PASS);
		conn.setAutoCommit(true);
		
		PreparedStatement stmt2 = null;
		PreparedStatement stmt = null;
		for(String s : ss) {
			stmt2 = conn2.prepareStatement("select count(1) as num from "+ s);
			ResultSet rs2 = stmt2.executeQuery();
			rs2.next();
			int a = rs2.getInt("num");
			stmt = conn.prepareStatement("select count(1) as num from "+ s);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int b = rs.getInt("num");
			
			System.out.println((a == b ? "一致": "不一致") + ",表"+s+","+a+"-"+b+"，差值：("+(a-b)+")");
		}
		
		stmt2.close();
		stmt.close();
	}
}