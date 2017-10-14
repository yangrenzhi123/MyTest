package com.yang.test.java.JasperReports;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class App {

	public static void main(String[] args) throws JRException, Exception {
		JasperReport r = JasperCompileManager.compileReport(
				"D:/Workspaces/test/com.yang.test.java.JasperReports/src/main/java/com/yang/test/java/JasperReports/jasperreports_demo.jrxml");

		JasperPrint p = JasperFillManager.fillReport(r, null, new JREmptyDataSource());

		JasperExportManager.exportReportToPdfFile(p, "c:/1.pdf");
		JasperExportManager.exportReportToHtmlFile(p, "c:/1.html");
	}
}