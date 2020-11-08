package com.lyzh.msa.framework.common.dto.fl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author guoxingyong
 * @since 2018/12/22 11:42
 */
public class TenantGroupDTO implements Serializable {

	private static final long serialVersionUID = -7650044349219572849L;

	private String tenantgroupid;
	private Long tenantgroupzzid;
	private String tenantuserid;
	private String ismanager;

	private Date createtime; // 创建时间
	private String tenantid;

	private String productid;

	private Integer sflszh;

	private String regionid;

	private String communityid;
	//完整手机号码    用户信息导出用
	private String sjhmys;
	private String zhhh;

	private Integer zhjf;
	private Long zhjfxn;

	private Integer zhzt;

	private String cyewm;

	private String qtewm;

	private String dewm;

	private String sctxm;

	private Integer active;

	private String ddmc;//订单名称

	private String zhnc;

	//启用状态，1启用0停用，用户首次绑定业主停用绿色帐户，解绑最后一个业主启用绿色帐户
	private Integer qyzt;

	private String sjhm;

	private String phoneNum;
	
	private String tenantaccountid;// TenantAccount tenantAccount;

	private Integer cjlx; // 场景类型的冗余字段，以便进行数据筛选

	private String orderid;//项目ID

	private String yzid;

	private Integer cysl; // 成员数量
	private String yhdz;
	private String cjmc;//场景名称

	private Integer lyfs;

	private Integer sdjf;

	private Integer czz;//成长值
	private Integer sfysk=0;//是否演示卡
	private Integer investor;// 是否是大型散户

	private String bdzt;//绑定状态
	private Date bdsj;//绑定时间
	private Date zhywsj;//最后业务时间
	private String zhmc;//幢
	private String dymc;//单元
	private String fjmc;//房间
	private Integer dqlx;//地区类型
	private String dqmc;//苑
	private String tenantdistrictid;//关联单元主键

	private List<String> districtids = new ArrayList();
	private Map<String,List> map;

	private String qyqm;
	private String zhywsjstr;
	private String createtimestr;
	private String bdsjstr;

	private String outName;//

	//付款二维码
	private String paymentCode;

	private String bq1;
	private String bq2;
	private String bq3;

	/**
	 * 业主信息修改新增参数   2020-05-16
	 */
	private String updateType;
	private String ysjhm;
	private String ysjhmyzm;
	private String xsjhm;
	private String xsjhmyzm;

	private String zhlx;


	private String sfzh;//身份证号

	private int zzmm;//政治面貌 0 群众 1 党员 2 团员
	private String zzmmstr;//政治面貌 0 群众 1 党员 2 团员

	private String cyick;//厨余垃圾桶ic卡号

	private String qtick;//其他垃圾桶ic卡号

	private  String baidulng; //垃圾桶位置经度

	private  String baidulat; //垃圾桶位置纬度
	/*
	  所属街道社区
	 */
	private  String jdsq;

	private String zsxm;//真实姓名

	public String getZzmmstr() {
		return zzmmstr;
	}

	public void setZzmmstr(String zzmmstr) {
		this.zzmmstr = zzmmstr;
	}

	public String getJdsq() {
		return jdsq;
	}

	public void setJdsq(String jdsq) {
		this.jdsq = jdsq;
	}

	public String getZhlx() {
		return zhlx;
	}

	public void setZhlx(String zhlx) {
		this.zhlx = zhlx;
	}

	public String getYsjhm() {
		return ysjhm;
	}

	public void setYsjhm(String ysjhm) {
		this.ysjhm = ysjhm;
	}

	public String getYsjhmyzm() {
		return ysjhmyzm;
	}

	public void setYsjhmyzm(String ysjhmyzm) {
		this.ysjhmyzm = ysjhmyzm;
	}

	public String getXsjhm() {
		return xsjhm;
	}

	public void setXsjhm(String xsjhm) {
		this.xsjhm = xsjhm;
	}

	public String getXsjhmyzm() {
		return xsjhmyzm;
	}

	public void setXsjhmyzm(String xsjhmyzm) {
		this.xsjhmyzm = xsjhmyzm;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	
	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public int getZzmm() {
		return zzmm;
	}

	public void setZzmm(int zzmm) {
		this.zzmm = zzmm;
	}

	public String getCyick() {
		return cyick;
	}

	public void setCyick(String cyick) {
		this.cyick = cyick;
	}

	public String getQtick() {
		return qtick;
	}

	public void setQtick(String qtick) {
		this.qtick = qtick;
	}

	public String getBaidulng() {
		return baidulng;
	}

	public void setBaidulng(String baidulng) {
		this.baidulng = baidulng;
	}

	public String getBaidulat() {
		return baidulat;
	}

	public void setBaidulat(String baidulat) {
		this.baidulat = baidulat;
	}
	public String getBq1() {
		return bq1;
	}

	public void setBq1(String bq1) {
		this.bq1 = bq1;
	}

	public String getBq2() {
		return bq2;
	}

	public void setBq2(String bq2) {
		this.bq2 = bq2;
	}

	public String getBq3() {
		return bq3;
	}

	public void setBq3(String bq3) {
		this.bq3 = bq3;
	}

	public Long getZhjfxn() {
		return zhjfxn;
	}

	public void setZhjfxn(Long zhjfxn) {
		this.zhjfxn = zhjfxn;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	
	public Long getTenantgroupzzid() {
		return tenantgroupzzid;
	}

	public void setTenantgroupzzid(Long tenantgroupzzid) {
		this.tenantgroupzzid = tenantgroupzzid;
	}


	private String roletenantaccountid;//mogodb中用户的id

	public String getRoletenantaccountid() {
		return roletenantaccountid;
	}

	public void setRoletenantaccountid(String roletenantaccountid) {
		this.roletenantaccountid = roletenantaccountid;
	}

	public Integer getInvestor() {
		return investor;
	}

	public void setInvestor(Integer investor) {
		this.investor = investor;
	}
	private String qymc;
	private String sqmc;
	private List<String> communityIdList;

	public Map<String, List> getMap() {
		return map;
	}

	public void setMap(Map<String, List> map) {
		this.map = map;
	}

	public List<String> getDistrictids() {
		return districtids;
	}

	public void setDistrictids(List<String> districtids) {
		this.districtids = districtids;
	}

	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}

	public String getSqmc() {
		return sqmc;
	}

	public void setSqmc(String sqmc) {
		this.sqmc = sqmc;
	}

	public String getZhywsjstr() {
		return zhywsjstr;
	}

	public void setZhywsjstr(String zhywsjstr) {
		this.zhywsjstr = zhywsjstr;
	}

	public String getCreatetimestr() { return createtimestr; }

	public void setCreatetimestr(String createtimestr) { this.createtimestr = createtimestr; }

	public String getBdsjstr() { return bdsjstr; }

	public void setBdsjstr(String bdsjstr) { this.bdsjstr = bdsjstr; }

	public String getQyqm() {
		return qyqm;
	}

	public void setQyqm(String qyqm) {
		this.qyqm = qyqm;
	}

	public String getBdzt() {
		return bdzt;
	}

	public void setBdzt(String bdzt) {
		this.bdzt = bdzt;
	}

	public Date getBdsj() {
		return bdsj;
	}

	public void setBdsj(Date bdsj) {
		this.bdsj = bdsj;
	}

	public Date getZhywsj() {
		return zhywsj;
	}

	public void setZhywsj(Date zhywsj) {
		this.zhywsj = zhywsj;
	}
	private String zhStr;//幢中文名称
	private String dyStr;//单元中文名称
	
	

	public String getZhStr() {
		return zhStr;
	}

	public void setZhStr(String zhStr) {
		this.zhStr = zhStr;
	}

	public String getDyStr() {
		return dyStr;
	}

	public void setDyStr(String dyStr) {
		this.dyStr = dyStr;
	}

	public String getZhmc() {
		return zhmc;
	}

	public void setZhmc(String zhmc) {
		this.zhmc = zhmc;
	}

	public Integer getDqlx() {
		return dqlx;
	}

	public void setDqlx(Integer dqlx) {
		this.dqlx = dqlx;
	}

	public String getDymc() {
		return dymc;
	}

	public void setDymc(String dymc) {
		this.dymc = dymc;
	}

	public String getFjmc() {
		return fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public String getDqmc() {
		return dqmc;
	}

	public void setDqmc(String dqmc) {
		this.dqmc = dqmc;
	}

	public String getTenantdistrictid() {
		return tenantdistrictid;
	}

	public void setTenantdistrictid(String tenantdistrictid) {
		this.tenantdistrictid = tenantdistrictid;
	}

	public Integer getSdjf() {
		return sdjf;
	}

	public void setSdjf(Integer sdjf) {
		this.sdjf = sdjf;
	}

	public String getCjmc() {
		return cjmc;
	}

	public void setCjmc(String cjmc) {
		this.cjmc = cjmc;
	}

	public String getYhdz() {
		return yhdz;
	}

	public void setYhdz(String yhdz) {
		this.yhdz = yhdz;
	}
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getTenantgroupid() {
		return tenantgroupid;
	}

	public void setTenantgroupid(String tenantgroupid) {
		this.tenantgroupid = tenantgroupid;
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

	public Integer getSflszh() {
		return sflszh;
	}

	public void setSflszh(Integer sflszh) {
		this.sflszh = sflszh;
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

	public String getZhhh() {
		return zhhh;
	}

	public void setZhhh(String zhhh) {
		this.zhhh = zhhh;
	}

	public Integer getZhjf() {
		return zhjf;
	}

	public void setZhjf(Integer zhjf) {
		this.zhjf = zhjf;
	}

	public Integer getZhzt() {
		return zhzt;
	}

	public void setZhzt(Integer zhzt) {
		this.zhzt = zhzt;
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

	public String getDewm() {
		return dewm;
	}

	public void setDewm(String dewm) {
		this.dewm = dewm;
	}

	public String getSctxm() {
		return sctxm;
	}

	public void setSctxm(String sctxm) {
		this.sctxm = sctxm;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getDdmc() {
		return ddmc;
	}

	public void setDdmc(String ddmc) {
		this.ddmc = ddmc;
	}

	public String getZhnc() {
		return zhnc;
	}

	public void setZhnc(String zhnc) {
		this.zhnc = zhnc;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public List<String> getCommunityIdList() {
		return communityIdList;
	}

	public void setCommunityIdList(List<String> communityIdList) {
		this.communityIdList = communityIdList;
	}

	public String getTenantaccountid() {
		return tenantaccountid;
	}

	public void setTenantaccountid(String tenantaccountid) {
		this.tenantaccountid = tenantaccountid;
	}

	public Integer getCjlx() {
		return cjlx;
	}

	public void setCjlx(Integer cjlx) {
		this.cjlx = cjlx;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getYzid() {
		return yzid;
	}

	public void setYzid(String yzid) {
		this.yzid = yzid;
	}

	public Integer getCysl() {
		return cysl;
	}

	public void setCysl(Integer cysl) {
		this.cysl = cysl;
	}

	public Integer getQyzt() {
		return qyzt;
	}

	public void setQyzt(Integer qyzt) {
		this.qyzt = qyzt;
	}

	public Integer getLyfs() {
		return lyfs;
	}

	public void setLyfs(Integer lyfs) {
		this.lyfs = lyfs;
	}

	public Integer getCzz() {
		return czz;
	}

	public void setCzz(Integer czz) {
		this.czz = czz;
	}

	public Integer getSfysk() {
		return sfysk;
	}

	public void setSfysk(Integer sfysk) {
		this.sfysk = sfysk;
	}
	
	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getSjhmys() {
		return sjhmys;
	}

	public String getZsxm() {
		return zsxm;
	}

	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}

	public void setSjhmys(String sjhmys) {
		this.sjhmys = sjhmys;
	}
}