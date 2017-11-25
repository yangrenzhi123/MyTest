package com.xk.campushealth.web.business.controller;

import java.util.ArrayList;
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

import com.xk.campushealth.dao.IPassRecordDao;
import com.xk.campushealth.entity.PassRecord;
import com.xk.campushealth.query.PassRecordQuery;
import com.xk.campushealth.support.constant.HbConstant;
import com.xk.campushealth.support.dto.DtoResult;
import com.xk.campushealth.support.dto.Pager;

@SuppressWarnings({"deprecation", "serial"})
@Controller
@RequestMapping(value = "/PassRecord")
public class PassRecordController {

	@Autowired
	IPassRecordDao passRecordDao;

	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Pager page(final PassRecordQuery query, Integer pageNow, Integer pageSize) {

		Pageable pageable = new PageRequest(pageNow, pageSize);
		Page<PassRecord> p = passRecordDao.findAll(new Specification<PassRecord>() {
			public Predicate toPredicate(Root<PassRecord> root, CriteriaQuery<?> q, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (query.id != null) {
					list.add(cb.equal(root.get("id"), query.id));
				}
				if (!StringUtils.isEmpty(query.studentNo)) {
					list.add(cb.like(root.get("studentNo").as(String.class), "%" + query.studentNo + "%"));
				}
				if (query.type != null) {
					list.add(cb.equal(root.get("type"), query.type));
				}
				if (query.timeStart != null) {
					list.add(cb.greaterThan(root.get("time").as(Date.class), query.timeStart));
				}
				if (query.timeEnd != null) {
					list.add(cb.lessThan(root.get("time").as(Date.class), query.timeEnd));
				}
				if (query.status != null) {
					list.add(cb.equal(root.get("status"), query.status));
				}
				if (query.isInlegal != null) {
					list.add(cb.equal(root.get("isInlegal"), query.isInlegal));
				}
				if (!StringUtils.isEmpty(query.imagePath)) {
					list.add(cb.like(root.get("imagePath").as(String.class), "%" + query.imagePath + "%"));
				}
				if (!StringUtils.isEmpty(query.schoolId)) {
					list.add(cb.like(root.get("schoolId").as(String.class), "%" + query.schoolId + "%"));
				}
				if (!StringUtils.isEmpty(query.passType)) {
					list.add(cb.like(root.get("passType").as(String.class), "%" + query.passType + "%"));
				}
				if (query.createTimeStart != null) {
					list.add(cb.greaterThan(root.get("createTime").as(Date.class), query.createTimeStart));
				}
				if (query.createTimeEnd != null) {
					list.add(cb.lessThan(root.get("createTime").as(Date.class), query.createTimeEnd));
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