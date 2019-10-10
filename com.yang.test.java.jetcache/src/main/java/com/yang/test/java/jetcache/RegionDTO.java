package com.yang.test.java.jetcache;

import java.io.Serializable;
import java.util.List;
/**
 * @Auther: wangyanping
 * @Date: 2018-11-23T16:39:01.710
 * @Description: 地区维护DTO
 * @since: 4.0.2
 */
public class RegionDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String regionid;
	private String qymc;
	private String qybm;
	private String qylx;
	private String sjqy;
	private String qyzt;
	private String createuser;
	private Integer xssx;
	private Float dtjd;
	private Float dtwd;
	private Float xqzxjd;
	private Float xqzxwd;
	private Integer xjqys;
	private String active;
	private Integer qycs;
	private String sjmc;
	private Integer hassub;
	private String nzbm;
	private String orderid;//订单ID
	private String orderregionid;//订单地区主键
	private Integer selected; //是否选中，1=是；其他为否；服务层计算后赋值，返回给前端
	private Integer hasSubRegionInProduct;
	private Integer hasCommunityInProduct;
	private String tenantusercommunityid;
	private String communityid; //映射一个小区id,作为树显示使用
	private String tenantuserid;
	private String tenantid;
	private String ismanager;
	private Integer cjlx; // 冗余场景类型
	private String productid; // 冗余产品线id，用于左侧树过滤
	private List<String> regionIdList;
	private String tenantmoduleid; // 冗余指标主题ID
	private Integer sjly; // 冗余数据来源
	private Integer orderSjly;//进行G端产品线按租户 还是地区的过滤

	private String keyword; // 区域树关键字搜索
	private String qyqm;
	
	public String getQyqm() {
		return qyqm;
	}

	public void setQyqm(String qyqm) {
		this.qyqm = qyqm;
	}

	public Integer getOrderSjly() {
		return orderSjly;
	}

	public void setOrderSjly(Integer orderSjly) {
		this.orderSjly = orderSjly;
	}

	public Integer getSjly() {
		return sjly;
	}

	public void setSjly(Integer sjly) {
		this.sjly = sjly;
	}

	public String getTenantmoduleid() {
		return tenantmoduleid;
	}

	public void setTenantmoduleid(String tenantmoduleid) {
		this.tenantmoduleid = tenantmoduleid;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Integer getHasSubRegionInProduct() {
		return hasSubRegionInProduct;
	}

	public void setHasSubRegionInProduct(Integer hasSubRegionInProduct) {
		this.hasSubRegionInProduct = hasSubRegionInProduct;
	}

	public Integer getHasCommunityInProduct() {
		return hasCommunityInProduct;
	}

	public void setHasCommunityInProduct(Integer hasCommunityInProduct) {
		this.hasCommunityInProduct = hasCommunityInProduct;
	}

	public String getOrderregionid() {
		return orderregionid;
	}
	public void setOrderregionid(String orderregionid) {
		this.orderregionid = orderregionid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
	public String getRegionid() {
		return regionid;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public String getQymc() {
		return qymc;
	}
	public void setQybm(String qybm) {
		this.qybm = qybm;
	}
	public String getQybm() {
		return qybm;
	}
	public void setQylx(String qylx) {
		this.qylx = qylx;
	}
	public String getQylx() {
		return qylx;
	}
	public void setSjqy(String sjqy) {
		this.sjqy = sjqy;
	}
	public String getSjqy() {
		return sjqy;
	}
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}
	public String getQyzt() {
		return qyzt;
	}
	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}
	public Integer getXssx() {
		return xssx;
	}
	public void setDtjd(Float dtjd) {
		this.dtjd = dtjd;
	}
	public Float getDtjd() {
		return dtjd;
	}
	public void setDtwd(Float dtwd) {
		this.dtwd = dtwd;
	}
	public Float getDtwd() {
		return dtwd;
	}
	public void setXqzxjd(Float xqzxjd) {
		this.xqzxjd = xqzxjd;
	}
	public Float getXqzxjd() {
		return xqzxjd;
	}
	public void setXqzxwd(Float xqzxwd) {
		this.xqzxwd = xqzxwd;
	}
	public Float getXqzxwd() {
		return xqzxwd;
	}
	public void setXjqys(Integer xjqys) {
		this.xjqys = xjqys;
	}
	public Integer getXjqys() {
		return xjqys;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getActive() {
		return active;
	}
	public void setQycs(Integer qycs) {
		this.qycs = qycs;
	}
	public Integer getQycs() {
		return qycs;
	}

	public String getSjmc() {
		return sjmc;
	}

	public void setSjmc(String sjmc) {
		this.sjmc = sjmc;
	}

	public Integer getHassub() {
		return hassub;
	}

	public void setHassub(Integer hassub) {
		this.hassub = hassub;
	}

	public String getNzbm() {
		return nzbm;
	}

	public void setNzbm(String nzbm) {
		this.nzbm = nzbm;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}
	public String getTenantusercommunityid() {
		return tenantusercommunityid;
	}

	public void setTenantusercommunityid(String tenantusercommunityid) {
		this.tenantusercommunityid = tenantusercommunityid;
	}

	public String getCommunityid() {
		return communityid;
	}

	public void setCommunityid(String communityid) {
		this.communityid = communityid;
	}

	public String getTenantuserid() {
		return tenantuserid;
	}

	public void setTenantuserid(String tenantuserid) {
		this.tenantuserid = tenantuserid;
	}

	public String getTenantid() {
		return tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}

	public String getIsmanager() {
		return ismanager;
	}

	public void setIsmanager(String ismanager) {
		this.ismanager = ismanager;
	}

	public Integer getCjlx() {
		return cjlx;
	}

	public void setCjlx(Integer cjlx) {
		this.cjlx = cjlx;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public List<String> getRegionIdList() {
		return regionIdList;
	}

	public void setRegionIdList(List<String> regionIdList) {
		this.regionIdList = regionIdList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}