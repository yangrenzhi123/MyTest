package com.lyzh.msa.framework.common.dto.console;

import java.io.Serializable;

/**
 * @Author: taohaifeng
 * @Date: 2019/8/2 15:04
 * @Description:
 */
public class ReceiveBagStatisticalResponseDTO implements Serializable {
	private static final long serialVersionUID = -2292756435741228800L;

	private Integer serialnumber;//序号

	private String orderid;
	private String ddmc;
	private String communityid;
	private String cjmc;
	private Integer familyCount;
	private Integer receiveBagFamilyCount;
	private Integer unReceiveBagFamilyCount;
	private Integer receiveBagCount;
	private Double receiveBagRate;

	private String yearMonth;

	public Integer getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(Integer serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getDdmc() {
		return ddmc;
	}

	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}

	public String getCommunityid() {
		return communityid;
	}

	public void setCommunityid(String communityid) {
		this.communityid = communityid;
	}

	public String getCjmc() {
		return cjmc;
	}

	public void setCjmc(String cjmc) {
		this.cjmc = cjmc;
	}

	public Integer getFamilyCount() {
		return familyCount;
	}

	public void setFamilyCount(Integer familyCount) {
		this.familyCount = familyCount;
	}

	public Integer getReceiveBagFamilyCount() {
		return receiveBagFamilyCount;
	}

	public void setReceiveBagFamilyCount(Integer receiveBagFamilyCount) {
		this.receiveBagFamilyCount = receiveBagFamilyCount;
	}

	public Integer getUnReceiveBagFamilyCount() {
		return unReceiveBagFamilyCount;
	}

	public void setUnReceiveBagFamilyCount(Integer unReceiveBagFamilyCount) {
		this.unReceiveBagFamilyCount = unReceiveBagFamilyCount;
	}

	public Integer getReceiveBagCount() {
		return receiveBagCount;
	}

	public void setReceiveBagCount(Integer receiveBagCount) {
		this.receiveBagCount = receiveBagCount;
	}

	public Double getReceiveBagRate() {
		return receiveBagRate;
	}

	public void setReceiveBagRate(Double receiveBagRate) {
		this.receiveBagRate = receiveBagRate;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	@Override
	public String toString() {
		return "ReceiveBagStatisticalResponseDTO{" +
				"serialnumber=" + serialnumber +
				", orderid='" + orderid + '\'' +
				", ddmc='" + ddmc + '\'' +
				", communityid='" + communityid + '\'' +
				", cjmc='" + cjmc + '\'' +
				", familyCount=" + familyCount +
				", receiveBagFamilyCount=" + receiveBagFamilyCount +
				", unReceiveBagFamilyCount=" + unReceiveBagFamilyCount +
				", receiveBagCount=" + receiveBagCount +
				", receiveBagRate=" + receiveBagRate +
				", yearMonth='" + yearMonth + '\'' +
				'}';
	}
}
