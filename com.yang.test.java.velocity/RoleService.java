package com.xk.campushealth.service.impl;

import com.xk.campushealth.dao.IRoleDao;
import com.xk.campushealth.service.intf.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	IPassRecordDao passRecordDao;
}