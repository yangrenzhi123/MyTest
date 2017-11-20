package com.yang.test.servlet.springmvc.kaoqin;

import java.util.Date;

public class KaoQinData {

	public String stuNum;
	public Integer clockType;
	public Date clockTime;
	public String imgUrl;
	public String schoolId;
	public String passType;
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public Integer getClockType() {
		return clockType;
	}
	public void setClockType(Integer clockType) {
		this.clockType = clockType;
	}
	public Date getClockTime() {
		return clockTime;
	}
	public void setClockTime(Date clockTime) {
		this.clockTime = clockTime;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getPassType() {
		return passType;
	}
	public void setPassType(String passType) {
		this.passType = passType;
	}
}