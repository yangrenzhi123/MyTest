package com.yang.test.java.springboot;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = "com.yang.test.java.springboot.dao1", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class Test1MybatisConfig {
	// 配置数据源
	@Bean(name = "test1Datasource")
	public DataSource testDatasource() throws SQLException {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		// autoReconnect 当数据库连接异常中断时，是否自动重新连接
		// failOverReadOnly 自动重连成功后，连接是否设置为只读
		mysqlXADataSource.setUrl("jdbc:mysql://192.168.43.185:3306/test?autoReconnect=true&failOverReadOnly=false");
		mysqlXADataSource.setPassword("123456");
		mysqlXADataSource.setUser("root");
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXADataSource.setUseSSL(false);

		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setUniqueResourceName("test1Datasource");
		atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
		atomikosDataSourceBean.setMaxPoolSize(1);// 连接池中保留的最大连接数 默认1
		atomikosDataSourceBean.setMinPoolSize(1);// 连接池中保留的最小连接数 默认1
		atomikosDataSourceBean.setLoginTimeout(60);// java数据库连接池，最大可等待获取datasouce的时间 60秒
		atomikosDataSourceBean.setReapTimeout(30);// 设置连接池允许连接使用的时间（以秒为单位），然后要求它返回。超时就会抛出类似Resultset is close 的错误
		atomikosDataSourceBean.setMaxLifetime(3600); // 设置连接在池中被自动销毁之前的最大秒数。
		atomikosDataSourceBean.setMaxIdleTime(60); // 设置池中未使用的过量连接应该保留的最大秒数。
		atomikosDataSourceBean.setMaintenanceInterval(60);
		atomikosDataSourceBean.setBorrowConnectionTimeout(30);// 获取链接的最大等待时间，秒。
		atomikosDataSourceBean.setTestQuery("select 1");
		return atomikosDataSourceBean;
	}

	@Bean(name = "test1SqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1Datasource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper1/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "test1SqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}