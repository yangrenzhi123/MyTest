package com.yang.test.java.validate.test;

import com.yang.test.java.validate.Notes;
import com.yang.test.java.validate.Validate;

/**
 *	支付转账 - 企业内部转账 - 请求
 */
public class PayInnerRequest {

	private String action="DLINETRN";
	/**
	 * 登录名
	 */
	@Notes(filedCn = "登录名")
	@Validate(requierd = true, maxLength = 30)
	private String userName;
	/**
	 * 客户流水号
	 */
	@Notes(filedCn = "客户流水号")
	@Validate(requierd = true, maxLength = 20)
	private String clientID;
	/**
	 * 预约支付标志 0：非预约交易；1：预约交易
	 */
	@Notes(filedCn = "预约支付标志")
	@Validate(requierd = true, maxLength = 1)
	private String preFlg;
	/**
	 * 延期支付日期char(8) 格式YYYYMMDD ，预约时非空
	 */
	@Notes(filedCn = "延期支付日期")
	@Validate(requierd = true, maxLength = 8)
	private String preDate;
	/**
	 * 延期支付时间char(6) 格式hhmmss ，预约时非空
	 */
	@Notes(filedCn = "延期支付时间")
	@Validate(maxLength = 6)
	private String preTime;
	/**
	 * 付款账号
	 */
	@Notes(filedCn = "付款账号")
	@Validate(requierd = true, maxLength = 19)
	private String payAccountNo;
	/**
	 * 收款账号
	 */
	@Notes(filedCn = "收款账号")
	@Validate(requierd = true, maxLength = 19)
	private String recAccountNo;
	/**
	 * 金额
	 */
	@Notes(filedCn = "金额")
	@Validate(requierd = true, maxLength = 15)
	private Double tranAmount;
	/**
	 * 备注
	 */
	@Notes(filedCn = "备注")
	@Validate(maxLength = 60)
	private String memo;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getPreFlg() {
		return preFlg;
	}

	public void setPreFlg(String preFlg) {
		this.preFlg = preFlg;
	}

	public String getPreDate() {
		return preDate;
	}

	public void setPreDate(String preDate) {
		this.preDate = preDate;
	}

	public String getPreTime() {
		return preTime;
	}

	public void setPreTime(String preTime) {
		this.preTime = preTime;
	}

	public String getPayAccountNo() {
		return payAccountNo;
	}

	public void setPayAccountNo(String payAccountNo) {
		this.payAccountNo = payAccountNo;
	}

	public String getRecAccountNo() {
		return recAccountNo;
	}

	public void setRecAccountNo(String recAccountNo) {
		this.recAccountNo = recAccountNo;
	}

	public Double getTranAmount() {
		return tranAmount;
	}

	public void setTranAmount(Double tranAmount) {
		this.tranAmount = tranAmount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}