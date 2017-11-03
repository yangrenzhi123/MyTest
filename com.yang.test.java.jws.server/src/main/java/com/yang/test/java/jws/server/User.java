package com.yang.test.java.jws.server;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
public class User {

	@XmlElement(namespace = "http://www.xikesoft.com/")
	private String UserID;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Integer TenantCode;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Integer UserStatus;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Integer UserType;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Date CreateTime;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Integer CreatePeople;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Date LastModifyTime;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Integer LastModifyPeople;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Boolean IsHealth;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Date LastLoginTime;
	@XmlElement(namespace = "http://www.xikesoft.com/")
	private Integer TechnicianID;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public Integer getTenantCode() {
		return TenantCode;
	}

	public void setTenantCode(Integer tenantCode) {
		TenantCode = tenantCode;
	}

	public Integer getUserStatus() {
		return UserStatus;
	}

	public void setUserStatus(Integer userStatus) {
		UserStatus = userStatus;
	}

	public Integer getUserType() {
		return UserType;
	}

	public void setUserType(Integer userType) {
		UserType = userType;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public Integer getCreatePeople() {
		return CreatePeople;
	}

	public void setCreatePeople(Integer createPeople) {
		CreatePeople = createPeople;
	}

	public Date getLastModifyTime() {
		return LastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		LastModifyTime = lastModifyTime;
	}

	public Integer getLastModifyPeople() {
		return LastModifyPeople;
	}

	public void setLastModifyPeople(Integer lastModifyPeople) {
		LastModifyPeople = lastModifyPeople;
	}

	public Boolean getIsHealth() {
		return IsHealth;
	}

	public void setIsHealth(Boolean isHealth) {
		IsHealth = isHealth;
	}

	public Date getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

	public Integer getTechnicianID() {
		return TechnicianID;
	}

	public void setTechnicianID(Integer technicianID) {
		TechnicianID = technicianID;
	}
}