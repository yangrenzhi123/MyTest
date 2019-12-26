package com.yang.test.springboot.mongo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RecycleRecordDTO {
	private String zsxm;
	private String sjhm;
	private String sblx;
	private String ddmc;
	private String cjmc;
	private String flmc;
	private String zhnc;
	private String sbmc;
	private String qylx;
	private String ryxm;
	private Integer sjczfs;// 数据操作方式
	private Integer tzhzl;
	private String yzm;// 验证码
	private String sjd;
	private String sblxmc;
	private double tzhzlkg;
	private Double jzTotalWeight;// 减重总重量
	private long jzTotalRecord;// 减重总记录数
	private long tdzzl;// 查询总重量
	private String sjly;
	private long tdzts;// 查询总记录数
	private Long gdspid;
	private String fllx;
	private String recycleloseweightid;
	private Long recycleloseweightzzid;
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
	private double tddj; // 单价积分
	private String tffs;
	private Integer jlly;
	private Integer growthValue;
	private double growthValueSingleprice;
	private String createuser;
	private String phoneNum;
	private String dxfsrs;// 短信发送数量
	private Date createtime;
	private String yxxqid;
	private String spip;
	private Integer spdk;
	private String sptd;
	private String spid;
	private String yhm;
	private String yhmm;
	private String qrcode;
	private String cyewm;
	private String qtewm;
	private String zhhh;
	private String queryType;
	private Integer cjlx;
	private Long maxCursor;// 最大游标值
	private Long recyclerecordzzid;// 游标值
	private List<Long> recyclerecordzzids;// 游标值集合
	private String zh;
	private String yzmc;
	private String tenantaccountid;
	private String cxsj;
	private String tdzlstr;
	private String appid;// AppID 用来存储商户关系
	private Integer hsms;// 回收模式
	private String zldw;// 重量单位
	private String djdw;// 单价单位
	private String fzbh;// 箱体放置编号
	private BigDecimal tddj2;
	private Integer dxfssl;
	private Date tfsj;
	private Integer active;
	private String yzqyid;
	private String tenantuserid;
	private String ismanager;

	public String getFzbh() {
		return fzbh;
	}

	public void setFzbh(String fzbh) {
		this.fzbh = fzbh;
	}

	public String getAppid() {
		return appid;
	}

	public List<Long> getRecyclerecordzzids() {
		return recyclerecordzzids;
	}

	public void setRecyclerecordzzids(List<Long> recyclerecordzzids) {
		this.recyclerecordzzids = recyclerecordzzids;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getTdzlstr() {
		return tdzlstr;
	}

	public void setTdzlstr(String tdzlstr) {
		this.tdzlstr = tdzlstr;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getGdspid() {
		return gdspid;
	}

	public void setGdspid(Long gdspid) {
		this.gdspid = gdspid;
	}

	public String getCxsj() {
		return cxsj;
	}

	public void setCxsj(String cxsj) {
		this.cxsj = cxsj;
	}

	public String getTenantaccountid() {
		return tenantaccountid;
	}

	public void setTenantaccountid(String tenantaccountid) {
		this.tenantaccountid = tenantaccountid;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

	public Integer getSdjf() {
		return sdjf;
	}

	public void setSdjf(Integer sdjf) {
		this.sdjf = sdjf;
	}

	public void setTdzzl(long tdzzl) {
		this.tdzzl = tdzzl;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public Integer getCjlx() {
		return cjlx;
	}

	public void setCjlx(Integer cjlx) {
		this.cjlx = cjlx;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getCyewm() {
		return cyewm;
	}

	public void setCyewm(String cyewm) {
		this.cyewm = cyewm;
	}

	public String getQtewm() {
		return qtewm;
	}

	public void setQtewm(String qtewm) {
		this.qtewm = qtewm;
	}

	public String getZhhh() {
		return zhhh;
	}

	public void setZhhh(String zhhh) {
		this.zhhh = zhhh;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getSpip() {
		return spip;
	}

	public void setSpip(String spip) {
		this.spip = spip;
	}

	public Integer getSpdk() {
		return spdk;
	}

	public void setSpdk(Integer spdk) {
		this.spdk = spdk;
	}

	public String getSptd() {
		return sptd;
	}

	public void setSptd(String sptd) {
		this.sptd = sptd;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public String getYhmm() {
		return yhmm;
	}

	public void setYhmm(String yhmm) {
		this.yhmm = yhmm;
	}

	public Integer getTzhzl() {
		return tzhzl;
	}

	public double getTzhzlkg() {
		return tzhzlkg;
	}

	public void setTzhzlkg(double tzhzlkg) {
		this.tzhzlkg = tzhzlkg;
	}

	public void setTzhzl(Integer tzhzl) {
		this.tzhzl = tzhzl;
	}

	public String getSblxmc() {
		return sblxmc;
	}

	public void setSblxmc(String sblxmc) {
		this.sblxmc = sblxmc;
	}

	public String getYzm() {
		return yzm;
	}

	public void setYzm(String yzm) {
		this.yzm = yzm;
	}

	public float getTdzzl() {
		return tdzzl;
	}

	public long getTdzts() {
		return tdzts;
	}

	public void setTdzts(long tdzts) {
		this.tdzts = tdzts;
	}

	public Integer getSjczfs() {
		return sjczfs;
	}

	public void setSjczfs(Integer sjczfs) {
		this.sjczfs = sjczfs;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSjd() {
		return sjd;
	}

	public void setSjd(String sjd) {
		this.sjd = sjd;
	}

	public float getTdzlf() {
		return tdzlf;
	}

	public void setTdzlf(float tdzlf) {
		this.tdzlf = tdzlf;
	}

	public String getRyxm() {
		return ryxm;
	}

	public void setRyxm(String ryxm) {
		this.ryxm = ryxm;
	}

	public String getTenantuserid() {
		return tenantuserid;
	}

	public void setTenantuserid(String tenantuserid) {
		this.tenantuserid = tenantuserid;
	}

	public String getIsmanager() {
		return ismanager;
	}

	public void setIsmanager(String ismanager) {
		this.ismanager = ismanager;
	}

	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}

	public String getSbmc() {
		return sbmc;
	}

	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	public Double getjzTotalWeight() {
		return jzTotalWeight;
	}

	public void setjzTotalWeight(Double jzTotalWeight) {
		this.jzTotalWeight = jzTotalWeight;
	}

	public long getJzTotalRecord() {
		return jzTotalRecord;
	}

	public void setJzTotalRecord(long jzTotalRecord) {
		this.jzTotalRecord = jzTotalRecord;
	}

	public String getZhnc() {
		return zhnc;
	}

	public void setZhnc(String zhnc) {
		this.zhnc = zhnc;
	}

	public String getFlmc() {
		return flmc;
	}

	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	public String getFllx() {
		return fllx;
	}

	public void setFllx(String fllx) {
		this.fllx = fllx;
	}

	public String getDdmc() {
		return ddmc;
	}

	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}

	public String getCjmc() {
		return cjmc;
	}

	public void setCjmc(String cjmc) {
		this.cjmc = cjmc;
	}

	public String getZsxm() {
		return zsxm;
	}

	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}

	public String getSjhm() {
		return sjhm;
	}

	public Double getJzTotalWeight() {
		return jzTotalWeight;
	}

	public void setJzTotalWeight(Double jzTotalWeight) {
		this.jzTotalWeight = jzTotalWeight;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getSblx() {
		return sblx;
	}

	public void setSblx(String sblx) {
		this.sblx = sblx;
	}

	public void setRecyclerecordid(String recyclerecordid) {
		this.recyclerecordid = recyclerecordid;
	}

	public String getRecyclerecordid() {
		return recyclerecordid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}

	public String getTenantid() {
		return tenantid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductid() {
		return productid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	public String getRegionid() {
		return regionid;
	}

	public void setCommunityid(String communityid) {
		this.communityid = communityid;
	}

	public String getCommunityid() {
		return communityid;
	}

	public void setSbbh(String sbbh) {
		this.sbbh = sbbh;
	}

	public String getSbbh() {
		return sbbh;
	}

	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getEquipmentid() {
		return equipmentid;
	}

	public void setTfbz(String tfbz) {
		this.tfbz = tfbz;
	}

	public String getTfbz() {
		return tfbz;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setTenantgroupid(String tenantgroupid) {
		this.tenantgroupid = tenantgroupid;
	}

	public String getTenantgroupid() {
		return tenantgroupid;
	}

	public void setOperateuserid(String operateuserid) {
		this.operateuserid = operateuserid;
	}

	public String getOperateuserid() {
		return operateuserid;
	}

	public void setLjlx(String ljlx) {
		this.ljlx = ljlx;
	}

	public String getLjlx() {
		return ljlx;
	}

	public void setTdqzl(Integer tdqzl) {
		this.tdqzl = tdqzl;
	}

	public Integer getTdqzl() {
		return tdqzl;
	}

	public void setTdhzl(Integer tdhzl) {
		this.tdhzl = tdhzl;
	}

	public Integer getTdhzl() {
		return tdhzl;
	}

	public void setTdzl(Integer tdzl) {
		this.tdzl = tdzl;
	}

	public Integer getTdzl() {
		return tdzl;
	}

	public void setHdjf(Integer hdjf) {
		this.hdjf = hdjf;
	}

	public Integer getHdjf() {
		return hdjf;
	}

	public double getTddj() {
		return tddj;
	}

	public void setTddj(double tddj) {
		this.tddj = tddj;
	}

	public void setTffs(String tffs) {
		this.tffs = tffs;
	}

	public String getTffs() {
		return tffs;
	}

	public void setJlly(Integer jlly) {
		this.jlly = jlly;
	}

	public Integer getJlly() {
		return jlly;
	}

	public String getDxfsrs() {
		return dxfsrs;
	}

	public void setDxfsrs(String dxfsrs) {
		this.dxfsrs = dxfsrs;
	}

	public Date getTfsj() {
		return tfsj;
	}

	public void setTfsj(Date tfsj) {
		this.tfsj = tfsj;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getActive() {
		return active;
	}

	public void setYzqyid(String yzqyid) {
		this.yzqyid = yzqyid;
	}

	public String getYzqyid() {
		return yzqyid;
	}

	public String getYxxqid() {
		return yxxqid;
	}

	public void setYxxqid(String yxxqid) {
		this.yxxqid = yxxqid;
	}

	public Integer getGrowthValue() {
		return growthValue;
	}

	public double getGrowthValueSingleprice() {
		return growthValueSingleprice;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setGrowthValue(Integer growthValue) {
		this.growthValue = growthValue;
	}

	public void setGrowthValueSingleprice(double growthValueSingleprice) {
		this.growthValueSingleprice = growthValueSingleprice;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Long getMaxCursor() {
		return maxCursor;
	}

	public void setMaxCursor(Long maxCursor) {
		this.maxCursor = maxCursor;
	}

	public Long getRecyclerecordzzid() {
		return recyclerecordzzid;
	}

	public void setRecyclerecordzzid(Long recyclerecordzzid) {
		this.recyclerecordzzid = recyclerecordzzid;
	}

	public BigDecimal getTddj2() {
		return tddj2;
	}

	public void setTddj2(BigDecimal tddj2) {
		this.tddj2 = tddj2;
	}

	public Integer getDxfssl() {
		return dxfssl;
	}

	public void setDxfssl(Integer dxfssl) {
		this.dxfssl = dxfssl;
	}

	public Long getRecycleloseweightzzid() {
		return recycleloseweightzzid;
	}

	public void setRecycleloseweightzzid(Long recycleloseweightzzid) {
		this.recycleloseweightzzid = recycleloseweightzzid;
	}

	public String getRecycleloseweightid() {
		return recycleloseweightid;
	}

	public void setRecycleloseweightid(String recycleloseweightid) {
		this.recycleloseweightid = recycleloseweightid;
	}

	public Integer getHsms() {
		return hsms;
	}

	public void setHsms(Integer hsms) {
		this.hsms = hsms;
	}

	public String getZldw() {
		return zldw;
	}

	public void setZldw(String zldw) {
		this.zldw = zldw;
	}

	public String getDjdw() {
		return djdw;
	}

	public void setDjdw(String djdw) {
		this.djdw = djdw;
	}
}