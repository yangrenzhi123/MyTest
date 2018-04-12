package com.yang.test.java.velocity;

import java.util.List;

public class ShuJuQianYiTable {

	private String tableName;
	private Boolean hsIdentity;
	private String columnLink;
	private List<Column> columns;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Boolean getHsIdentity() {
		return hsIdentity;
	}

	public void setHsIdentity(Boolean hsIdentity) {
		this.hsIdentity = hsIdentity;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getColumnLink() {
		return columnLink;
	}

	public void setColumnLink(String columnLink) {
		this.columnLink = columnLink;
	}
}