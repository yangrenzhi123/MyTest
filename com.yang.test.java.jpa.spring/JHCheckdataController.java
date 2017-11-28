package com.xk.campushealth.web.business.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xk.campushealth.dao.IJHCheckdataDao;
import com.xk.campushealth.entity.JHCheckdata;
import com.xk.campushealth.query.JHCheckdataQuery;
import com.xk.campushealth.support.constant.HbConstant;
import com.xk.campushealth.support.dto.DtoResult;
import com.xk.campushealth.support.dto.Pager;

@SuppressWarnings({"deprecation", "serial"})
@Controller
@RequestMapping(value = "/jHCheckdata")
public class JHCheckdataController {

	@Autowired
	IJHCheckdataDao jHCheckdataDao;

	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Pager page(final JHCheckdataQuery query, Integer pageNow, Integer pageSize) {

		Pageable pageable = new PageRequest(pageNow, pageSize);
		Page<JHCheckdata> p = jHCheckdataDao.findAll(new Specification<JHCheckdata>() {
			public Predicate toPredicate(Root<JHCheckdata> root, CriteriaQuery<?> q, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(query.organCode)) {
					list.add(cb.like(root.get("organCode").as(String.class), "%" + query.organCode + "%"));
				}
				if (!StringUtils.isEmpty(query.patIndexNo)) {
					list.add(cb.like(root.get("patIndexNo").as(String.class), "%" + query.patIndexNo + "%"));
				}
				if (!StringUtils.isEmpty(query.visitCardNo)) {
					list.add(cb.like(root.get("visitCardNo").as(String.class), "%" + query.visitCardNo + "%"));
				}
				if (!StringUtils.isEmpty(query.reportNo)) {
					list.add(cb.like(root.get("reportNo").as(String.class), "%" + query.reportNo + "%"));
				}
				if (!StringUtils.isEmpty(query.patName)) {
					list.add(cb.like(root.get("patName").as(String.class), "%" + query.patName + "%"));
				}
				if (!StringUtils.isEmpty(query.idNumber)) {
					list.add(cb.like(root.get("idNumber").as(String.class), "%" + query.idNumber + "%"));
				}
				if (!StringUtils.isEmpty(query.birthDate)) {
					list.add(cb.like(root.get("birthDate").as(String.class), "%" + query.birthDate + "%"));
				}
				if (!StringUtils.isEmpty(query.sexCode)) {
					list.add(cb.like(root.get("sexCode").as(String.class), "%" + query.sexCode + "%"));
				}
				if (!StringUtils.isEmpty(query.sexName)) {
					list.add(cb.like(root.get("sexName").as(String.class), "%" + query.sexName + "%"));
				}
				if (!StringUtils.isEmpty(query.maritalStatusCode)) {
					list.add(cb.like(root.get("maritalStatusCode").as(String.class), "%" + query.maritalStatusCode + "%"));
				}
				if (!StringUtils.isEmpty(query.maritalStatusName)) {
					list.add(cb.like(root.get("maritalStatusName").as(String.class), "%" + query.maritalStatusName + "%"));
				}
				if (!StringUtils.isEmpty(query.company)) {
					list.add(cb.like(root.get("company").as(String.class), "%" + query.company + "%"));
				}
				if (!StringUtils.isEmpty(query.familyAddr)) {
					list.add(cb.like(root.get("familyAddr").as(String.class), "%" + query.familyAddr + "%"));
				}
				if (!StringUtils.isEmpty(query.mobileNo)) {
					list.add(cb.like(root.get("mobileNo").as(String.class), "%" + query.mobileNo + "%"));
				}
				if (!StringUtils.isEmpty(query.examDate)) {
					list.add(cb.like(root.get("examDate").as(String.class), "%" + query.examDate + "%"));
				}
				if (!StringUtils.isEmpty(query.examResult)) {
					list.add(cb.like(root.get("examResult").as(String.class), "%" + query.examResult + "%"));
				}
				if (!StringUtils.isEmpty(query.examAdvice)) {
					list.add(cb.like(root.get("examAdvice").as(String.class), "%" + query.examAdvice + "%"));
				}
				if (!StringUtils.isEmpty(query.reportDr)) {
					list.add(cb.like(root.get("reportDr").as(String.class), "%" + query.reportDr + "%"));
				}
				if (!StringUtils.isEmpty(query.reportDate)) {
					list.add(cb.like(root.get("reportDate").as(String.class), "%" + query.reportDate + "%"));
				}
				if (!StringUtils.isEmpty(query.checkDr)) {
					list.add(cb.like(root.get("checkDr").as(String.class), "%" + query.checkDr + "%"));
				}
				if (!StringUtils.isEmpty(query.checkDate)) {
					list.add(cb.like(root.get("checkDate").as(String.class), "%" + query.checkDate + "%"));
				}
				if (query.examType != null) {
					list.add(cb.equal(root.get("examType"), query.examType));
				}
				if (!StringUtils.isEmpty(query.age)) {
					list.add(cb.like(root.get("age").as(String.class), "%" + query.age + "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		}, pageable);

		Pager re = new Pager();
		re.setPageNow(pageNow);
		re.setPageSize(pageSize);
		re.setTotal(p.getTotalElements());
		re.setCode(HbConstant.RESULT_CODE_SUCCESS);
		re.setResult(p.getContent());
		return re;
	}
}