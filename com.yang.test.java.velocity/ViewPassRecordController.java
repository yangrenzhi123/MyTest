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

import com.xk.campushealth.dao.IViewPassRecordDao;
import com.xk.campushealth.entity.ViewPassRecord;
import com.xk.campushealth.query.ViewPassRecordQuery;
import com.xk.campushealth.support.constant.HbConstant;
import com.xk.campushealth.support.dto.DtoResult;
import com.xk.campushealth.support.dto.Pager;

@SuppressWarnings({"deprecation", "serial"})
@Controller
@RequestMapping(value = "/viewPassRecord")
public class ViewPassRecordController {

	@Autowired
	IViewPassRecordDao viewPassRecordDao;

	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Pager page(final ViewPassRecordQuery query, Integer pageNow, Integer pageSize) {

		Pageable pageable = new PageRequest(pageNow, pageSize);
		Page<ViewPassRecord> p = viewPassRecordDao.findAll(new Specification<ViewPassRecord>() {
			public Predicate toPredicate(Root<ViewPassRecord> root, CriteriaQuery<?> q, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (query.id != null) {
					list.add(cb.equal(root.get("id"), query.id));
				}
				if (!StringUtils.isEmpty(query.studentNo)) {
					list.add(cb.like(root.get("studentNo").as(String.class), "%" + query.studentNo + "%"));
				}
				if (!StringUtils.isEmpty(query.type)) {
					list.add(cb.like(root.get("type").as(String.class), "%" + query.type + "%"));
				}
				if (!StringUtils.isEmpty(query.time)) {
					list.add(cb.like(root.get("time").as(String.class), "%" + query.time + "%"));
				}
				if (!StringUtils.isEmpty(query.isInlegal)) {
					list.add(cb.like(root.get("isInlegal").as(String.class), "%" + query.isInlegal + "%"));
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
				if (!StringUtils.isEmpty(query.createTime)) {
					list.add(cb.like(root.get("createTime").as(String.class), "%" + query.createTime + "%"));
				}
				if (!StringUtils.isEmpty(query.className)) {
					list.add(cb.like(root.get("className").as(String.class), "%" + query.className + "%"));
				}
				if (!StringUtils.isEmpty(query.grade)) {
					list.add(cb.like(root.get("grade").as(String.class), "%" + query.grade + "%"));
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