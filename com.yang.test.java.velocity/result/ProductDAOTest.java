package test.com.lyzh.msa.dao.test.console;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lyzh.msa.dao.DaoApplication;
import com.lyzh.msa.dao.dao.console.ProductDAO;
import com.lyzh.msa.framework.common.entity.console.Product;

/**
 * @Auther: yrz
 * @Date: 2018/10/19 11:58
 * @Description: 产品 DAO 测试
 */
@SpringBootTest(classes = DaoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ProductDAOTest {
	@Autowired
	private ProductDAO dao;

	@Test
	public void test() throws InterruptedException {
	}
}