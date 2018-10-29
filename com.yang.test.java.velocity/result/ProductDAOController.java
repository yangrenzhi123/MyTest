package com.lyzh.msa.dao.controller.console;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyzh.msa.dao.dao.console.ProductDAO;
import com.lyzh.msa.framework.common.entity.console.Product;
import com.lyzh.msa.framework.common.util.ThreadLocalUtils;

/**
 * @Auther: yangrenzhi
 * @Date: 2018-10-29 16:27
 * @Description: 产品DAO接口
 */
@RestController
@RequestMapping("/dao/console/Product")
public class ProductDAOController {

	@Autowired
	private ProductDAO productDAO;
	
		/**
	 * 增加
	 */
	@RequestMapping("/insert")
	public void insert(@RequestBody Product entity) {
		entity.setCreateuser(ThreadLocalUtils.getCurrentUser());
		entity.setCreatetime(new Date());

		productDAO.insert(entity);
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody Product entity) {
		entity.setLastmodifyuser(ThreadLocalUtils.getCurrentUser());
		entity.setLastmodifytime(new Date());

		productDAO.update(entity);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/find")
	public List<Product> find() {
		return productDAO.find();
	}
	
		/**
	 * 分页查询
	 * @param tenantDTO
	 * @return
	 */
	@RequestMapping("/page")
	public PageInfo<TenantDTO> page(@RequestBody TenantDTO tenantDTO){
		PageInfo<TenantDTO> pageInfo = tenantDAO.page(tenantDTO).toPageInfo();
		return pageInfo;
	}
}