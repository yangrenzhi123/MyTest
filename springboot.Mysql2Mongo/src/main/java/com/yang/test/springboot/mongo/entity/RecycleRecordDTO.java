package com.yang.test.springboot.mongo.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: lixiaofei
 * @Date: 2018-12-26T20:29:37.733
 * @Description:
 * @since: 4.0.3
 */
public class RecycleRecordDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String recyclerecordid;
	private String tenantid;
	private String productid;
	private String orderid;
	private String regionid;
	private String communityid;
	private String sbbh;
	private String equipmentid;
	private String tfbz;
	private String yhlx;
	private String tenantgroupid;
	private String operateuserid;
	private String ljlx;
	private Integer tdqzl;
	private Integer tdhzl;
	private Integer tdzl;
	private float tdzlf;
	private Integer hdjf;
	private Integer sdjf;
	private double tddj; //单价积分
	private String tffs;
	private Integer jlly;
	private Integer growthValue;
	private double growthValueSingleprice;
	private String createuser;
	private String phoneNum;
	private String dxfsrs;//短信发送数量

	private String yxxqid;
	private String yzqyid;
	private long recyclerecordzzid;//自增id
	private Integer active;
	private Date tfsj;
	private Integer tfsjh;
	private String tfsjym;
	private String sjly;
	private String hsms;
	private Integer cjlx;
	private Long gdspid;

	public Long getGdspid() {
		return gdspid;
	}

	public void setGdspid(Long gdspid) {
		this.gdspid = gdspid;
	}

	public String getDay() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(tfsj);
	}

	public String getYzqyid() {
		return yzqyid;
	}

	public void setYzqyid(String yzqyid) {
		this.yzqyid = yzqyid;
	}

	public Integer getCjlx() {
		return cjlx;
	}

	public void setCjlx(Integer cjlx) {
		this.cjlx = cjlx;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getHsms() {
		return hsms;
	}

	public void setHsms(String hsms) {
		this.hsms = hsms;
	}

	public String getTfsjym() {
		return tfsjym;
	}

	public void setTfsjym(String tfsjym) {
		this.tfsjym = tfsjym;
	}

	public Integer getTfsjh() {
		return tfsjh;
	}

	public void setTfsjh(Integer tfsjh) {
		this.tfsjh = tfsjh;
	}

	public Date getTfsj() {
		return tfsj;
	}

	public void setTfsj(Date tfsj) {
		this.tfsj = tfsj;
	}

	public String getRecyclerecordid() {
		return recyclerecordid;
	}

	public void setRecyclerecordid(String recyclerecordid) {
		this.recyclerecordid = recyclerecordid;
	}

	public String getTenantid() {
		return tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	public String getCommunityid() {
		return communityid;
	}

	public void setCommunityid(String communityid) {
		this.communityid = communityid;
	}

	public String getSbbh() {
		return sbbh;
	}

	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}

	public String getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getTfbz() {
		return tfbz;
	}

	public void setTfbz(String tfbz) {
		this.tfbz = tfbz;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	public String getTenantgroupid() {
		return tenantgroupid;
	}

	public void setTenantgroupid(String tenantgroupid) {
		this.tenantgroupid = tenantgroupid;
	}

	public String getOperateuserid() {
		return operateuserid;
	}

	public void setOperateuserid(String operateuserid) {
		this.operateuserid = operateuserid;
	}

	public String getLjlx() {
		return ljlx;
	}

	public void setLjlx(String ljlx) {
		this.ljlx = ljlx;
	}

	public Integer getTdqzl() {
		return tdqzl;
	}

	public void setTdqzl(Integer tdqzl) {
		this.tdqzl = tdqzl;
	}

	public Integer getTdhzl() {
		return tdhzl;
	}

	public void setTdhzl(Integer tdhzl) {
		this.tdhzl = tdhzl;
	}

	public Integer getTdzl() {
		return tdzl;
	}

	public void setTdzl(Integer tdzl) {
		this.tdzl = tdzl;
	}

	public float getTdzlf() {
		return tdzlf;
	}

	public void setTdzlf(float tdzlf) {
		this.tdzlf = tdzlf;
	}

	public Integer getHdjf() {
		return hdjf;
	}

	public void setHdjf(Integer hdjf) {
		this.hdjf = hdjf;
	}

	public Integer getSdjf() {
		return sdjf;
	}

	public void setSdjf(Integer sdjf) {
		this.sdjf = sdjf;
	}

	public double getTddj() {
		return tddj;
	}

	public void setTddj(double tddj) {
		this.tddj = tddj;
	}

	public String getTffs() {
		return tffs;
	}

	public void setTffs(String tffs) {
		this.tffs = tffs;
	}

	public Integer getJlly() {
		return jlly;
	}

	public void setJlly(Integer jlly) {
		this.jlly = jlly;
	}

	public Integer getGrowthValue() {
		return growthValue;
	}

	public void setGrowthValue(Integer growthValue) {
		this.growthValue = growthValue;
	}

	public double getGrowthValueSingleprice() {
		return growthValueSingleprice;
	}

	public void setGrowthValueSingleprice(double growthValueSingleprice) {
		this.growthValueSingleprice = growthValueSingleprice;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getDxfsrs() {
		return dxfsrs;
	}

	public void setDxfsrs(String dxfsrs) {
		this.dxfsrs = dxfsrs;
	}

	public String getYxxqid() {
		return yxxqid;
	}

	public void setYxxqid(String yxxqid) {
		this.yxxqid = yxxqid;
	}

	public long getRecyclerecordzzid() {
		return recyclerecordzzid;
	}

	public void setRecyclerecordzzid(long recyclerecordzzid) {
		this.recyclerecordzzid = recyclerecordzzid;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

}