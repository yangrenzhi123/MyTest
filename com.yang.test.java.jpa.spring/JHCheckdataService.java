package com.xk.campushealth.service.impl;

import com.xk.campushealth.dao.IJHCheckdataDao;
import com.xk.campushealth.service.intf.IJHCheckdataService;

@Service
public class JHCheckdataService implements IJHCheckdataService {

	@Autowired
	IPassRecordDao passRecordDao;
}