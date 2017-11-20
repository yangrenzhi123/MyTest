package com.yang.test.java.JasperReports;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JsonDataSource;

public class App {

	public static void main(String[] args) throws JRException, Exception {
//		JasperReport r = JasperCompileManager.compileReport("C:/Users/yangrenzhi/JaspersoftWorkspace/MyReports/Blank_A4.jrxml");
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("JSON_INPUT_STREAM", is);
//		params.put("id", "15");
//		Class.forName("net.sourceforge.jtds.jdbc.Driver");
//		Connection connection = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433/Test", "sa", "1qazxcvbnm,./");
//		Connection connection = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.6.225:1433/zhengyuandb1", "hb6debug", "hb6debug");
//		JasperPrint p2 = JasperFillManager.fillReport(r, params, connection);
//		JasperPrint p = JasperFillManager.fillReport(r, params, new JREmptyDataSource());
//		JasperPrint p3 = JasperFillManager.fillReport(r, params, new JsonDataSource(new File("C:/Users/yangrenzhi/Desktop/test2.json")));
		

		String json = "{\"aa\":\"1中\",\"a\":{\"b\":[\"5\", \"6\", \"3\", \"4\"]}}";
		InputStream is = new ByteArrayInputStream(json.getBytes());
		Map<String, Object> params = new HashMap<String, Object>();
		JasperPrint p4 = JasperFillManager.fillReport("C:/Users/yangrenzhi/Documents/GitHub/MyTest/MyReports/Blank_A4.jasper", params, new JsonDataSource(is));
		JasperExportManager.exportReportToHtmlFile(p4, "c:/1.html");
		JasperExportManager.exportReportToPdfFile(p4, "c:/1.pdf");
	}
}