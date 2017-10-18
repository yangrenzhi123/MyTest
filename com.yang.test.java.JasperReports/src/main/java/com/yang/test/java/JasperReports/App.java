package com.yang.test.java.JasperReports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class App {

	public static void main(String[] args) throws JRException, Exception {
		JasperReport r = JasperCompileManager.compileReport(
				"C:/Users/yangrenzhi/Documents/GitHub/MyTest/com.yang.test.java.JasperReports/src/main/java/com/yang/test/java/JasperReports/default_cover.jrxml");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tenantCode", 111);
		params.put("seqNumber", "13654");
		params.put("logoPath", "/upload/reportTemplate/20170704201606461/logo.jpg");
		params.put("ip", "http://192.168.6.240:8080");
//		params.put("id", 15);

		Class.forName("net.sourceforge.jtds.jdbc.Driver");
//		Connection connection = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433/Test", "sa", "1qazxcvbnm,./");
		Connection connection = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.6.225:1433/zhengyuandb1", "hb6debug", "hb6debug");

		JasperPrint p = JasperFillManager.fillReport(r, params, connection);

		JasperExportManager.exportReportToPdfFile(p, "c:/1.pdf");
	}
}