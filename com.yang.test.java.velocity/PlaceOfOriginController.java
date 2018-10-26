package com.lyzh.saas.console.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyzh.msa.framework.common.entity.MsgInfo;
import com.lyzh.msa.framework.common.entity.console.PlaceOfOrigin;
import com.lyzh.msa.framework.common.exception.TipException;
import com.lyzh.saas.console.service.PlaceOfOriginService;

/**
 * 
 * @author yangrenzhi
 * @date  2018-10-26 17:14
 * @Description   实体中文API接口
 * @since
  */
@RestController
@RequestMapping("/PlaceOfOrigin")
public class PlaceOfOriginController {

	@Autowired
	PlaceOfOriginService placeOfOriginService;
}