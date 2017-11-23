package com.yang.test.java.poi.test;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Test {

	public static void main(String[] args) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.getSheetAt(0);
		sheet.createRow(0);
	}
}