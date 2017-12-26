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

import com.xk.campushealth.dao.IPlaceOfOriginDao;
import com.xk.campushealth.entity.PlaceOfOrigin;
import com.xk.campushealth.query.PlaceOfOriginQuery;
import com.xk.campushealth.support.constant.HbConstant;
import com.xk.campushealth.support.dto.DtoResult;
import com.xk.campushealth.support.dto.Pager;

@SuppressWarnings({"deprecation", "serial"})
@Controller
@RequestMapping(value = "/placeOfOrigin")
public class PlaceOfOriginController {

	@Autowired
	IPlaceOfOriginDao placeOfOriginDao;

	@ResponseBody
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Pager page(final PlaceOfOriginQuery query, Integer pageNow, Integer pageSize) {

		Pageable pageable = new PageRequest(pageNow, pageSize);
		Page<PlaceOfOrigin> p = placeOfOriginDao.findAll(new Specification<PlaceOfOrigin>() {
			public Predicate toPredicate(Root<PlaceOfOrigin> root, CriteriaQuery<?> q, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (query.id != null) {
					list.add(cb.equal(root.get("id"), query.id));
				}
				if (!StringUtils.isEmpty(query.placeName)) {
					list.add(cb.like(root.get("placeName").as(String.class), "%" + query.placeName + "%"));
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

	@RequestMapping(value = "/start/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DtoResult start(@PathVariable("id") Integer id) {

		DtoResult re = new DtoResult(HbConstant.RESULT_CODE_SUCCESS);
		return re;
	}

	@RequestMapping(value = "/stop/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DtoResult stop(@PathVariable("id") Integer id) {

		DtoResult re = new DtoResult(HbConstant.RESULT_CODE_SUCCESS);
		return re;
	}

	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public DtoResult addOrUpdate() {

		DtoResult re = new DtoResult(HbConstant.RESULT_CODE_SUCCESS);
		return re;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DtoResult delete(@PathVariable("id") Integer id) {

		DtoResult re = new DtoResult(HbConstant.RESULT_CODE_SUCCESS);
		return re;
	}
}