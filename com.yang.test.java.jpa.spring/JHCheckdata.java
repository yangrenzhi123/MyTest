package com.xk.campushealth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JHCheckdata {

	public String organCode;
	public String patIndexNo;
	public String visitCardNo;
	public String reportNo;
	public String patName;
	public String idNumber;
	public String birthDate;
	public String sexCode;
	public String sexName;
	public String maritalStatusCode;
	public String maritalStatusName;
	public String company;
	public String familyAddr;
	public String mobileNo;
	public String examDate;
	public String examResult;
	public String examAdvice;
	public String reportDr;
	public String reportDate;
	public String checkDr;
	public String checkDate;
	public Integer examType;
	public String age;

	public String getOrganCode() {
		return organCode;
	}
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}
	public String getPatIndexNo() {
		return patIndexNo;
	}
	public void setPatIndexNo(String patIndexNo) {
		this.patIndexNo = patIndexNo;
	}
	public String getVisitCardNo() {
		return visitCardNo;
	}
	public void setVisitCardNo(String visitCardNo) {
		this.visitCardNo = visitCardNo;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getMaritalStatusCode() {
		return maritalStatusCode;
	}
	public void setMaritalStatusCode(String maritalStatusCode) {
		this.maritalStatusCode = maritalStatusCode;
	}
	public String getMaritalStatusName() {
		return maritalStatusName;
	}
	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getFamilyAddr() {
		return familyAddr;
	}
	public void setFamilyAddr(String familyAddr) {
		this.familyAddr = familyAddr;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getExamResult() {
		return examResult;
	}
	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}
	public String getExamAdvice() {
		return examAdvice;
	}
	public void setExamAdvice(String examAdvice) {
		this.examAdvice = examAdvice;
	}
	public String getReportDr() {
		return reportDr;
	}
	public void setReportDr(String reportDr) {
		this.reportDr = reportDr;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getCheckDr() {
		return checkDr;
	}
	public void setCheckDr(String checkDr) {
		this.checkDr = checkDr;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public Integer getExamType() {
		return examType;
	}
	public void setExamType(Integer examType) {
		this.examType = examType;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}