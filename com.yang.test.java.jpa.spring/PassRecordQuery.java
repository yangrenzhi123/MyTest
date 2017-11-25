package com.xk.campushealth.query;

import java.util.Date;

public class PassRecordQuery {

	public Integer id;
	public String studentNo;
	public Integer type;
	public Date timeStart;
	public Date timeEnd;
	public Integer status;
	public Boolean isInlegal;
	public String imagePath;
	public String schoolId;
	public String passType;
	public Date createTimeStart;
	public Date createTimeEnd;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(Date time) {
		this.timeStart = time;
	}
	public Date getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(Date time) {
		this.timeEnd = time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Boolean getIsInlegal() {
		return isInlegal;
	}
	public void setIsInlegal(Boolean isInlegal) {
		this.isInlegal = isInlegal;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTime) {
		this.createTimeStart = createTime;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTime) {
		this.createTimeEnd = createTime;
	}
}