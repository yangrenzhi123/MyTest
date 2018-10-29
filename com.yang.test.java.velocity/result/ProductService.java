package com.lyzh.saas.console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;

import com.lyzh.msa.framework.common.entity.console.Product;
import com.lyzh.msa.framework.common.dto.console.ProductDTO;
import com.lyzh.saas.console.rpc.ProductRPC;
import com.lyzh.msa.framework.common.entity.PageDataInfo;







/**
 * 产品
 * 
 * @author yangrenzhi
 * @date  2018-10-29 16:27
 * @Description  
 * @since 4.0.1
 */
@Service
public class ProductService {
	
	@Autowired
	ProductRPC rpc;
	
	
	/**
	 * 分页查询
	 * @param 
	 * @return
	 */
	public PageDataInfo<ProductDTO> pageList(ProductDTO dto){
		PageInfo<ProductDTO> pageInfo = rpc.page(dto);
		PageDataInfo<ProductDTO> dataInfo = new PageDataInfo<>();
		dataInfo.setPageNum(pageInfo.getPageNum());
		dataInfo.setPageSize(pageInfo.getPageSize());
		dataInfo.setTotal(pageInfo.getTotal());
		dataInfo.setData(pageInfo.getList());
		return dataInfo;
	}
	
	/**
	 * 获取产品列表
	 * @return
	 */
	public List<Product> find() {
		return rpc.find();
	}

	/**
	 * 新增
	 * @return
	 */
	public void save(Product entity) {
		rpc.insert(entity);
	}
	
	/**
	 * 修改
	 * @return
	 */
	public void update(Product entity) {
		rpc.update(entity);
	}
	
	/**
	 * 删除
	 * @return
	 */
	public void delete(Product entity) {
		rpc.delete(entity);
	}
}