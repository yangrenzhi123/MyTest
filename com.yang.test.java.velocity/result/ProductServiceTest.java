package com.lyzh.saas.console.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lyzh.msa.framework.common.entity.console.Product;
import com.lyzh.saas.console.ConsoleApplication;
import com.lyzh.saas.console.service.ProductService;

@SpringBootTest(classes = ConsoleApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ProductServiceTest {

	@Autowired
	ProductService productService;

	/**
	 * 查询
	 */
	@Test
	public void find() {
		List<Product> l = productService.find();
		System.out.println(l.size());
	}
}