package com.lyzh.msa.dao.controller.console;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyzh.msa.dao.dao.console.PlaceOfOriginDAO;
import com.lyzh.msa.framework.common.entity.console.PlaceOfOrigin;
import com.lyzh.msa.framework.common.util.ThreadLocalUtils;

/**
 * @Auther: yangrenzhi
 * @Date: 2018-10-26 17:14
 * @Description: 实体中文DAO接口
 */
@RestController
@RequestMapping("/dao/console/PlaceOfOrigin")
public class PlaceOfOriginDAOController {

	@Autowired
	private PlaceOfOriginDAO placeOfOriginDAO;
}