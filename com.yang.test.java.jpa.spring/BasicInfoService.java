package com.xk.campushealth.service.impl;

import com.xk.campushealth.dao.IBasicInfoDao;
import com.xk.campushealth.service.intf.IBasicInfoService;

@Service
public class BasicInfoService implements IBasicInfoService {

	@Autowired
	IBasicInfoDao BasicInfoDao;
}