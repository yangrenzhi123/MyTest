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
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(basePackages="com.yang.test.java.springboot.dao2",sqlSessionFactoryRef="test2SqlSessionFactory")
public class Test2MybatisConfig {
    //配置数据源
    @Bean(name="test2Datasource")
    public DataSource testDatasource() throws SQLException {
        MysqlXADataSource mysqlXADataSource=new MysqlXADataSource();
        mysqlXADataSource.setUrl("jdbc:mysql://192.168.10.91:3307/lyzhhw4");
        mysqlXADataSource.setUser("root");
        mysqlXADataSource.setPassword("Lenovo@@7788");
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setAllowMultiQueries(true);
        mysqlXADataSource.setUseSSL(false);
        

        AtomikosDataSourceBean atomikosDataSourceBean=new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test2Datasource");
		atomikosDataSourceBean.setMaxPoolSize(10);
		atomikosDataSourceBean.setMinPoolSize(1);
		atomikosDataSourceBean.setLoginTimeout(60);
		atomikosDataSourceBean.setReapTimeout(30);
		atomikosDataSourceBean.setMaxLifetime(3600);
		atomikosDataSourceBean.setMaxIdleTime(60);
		atomikosDataSourceBean.setMaintenanceInterval(60);
		atomikosDataSourceBean.setBorrowConnectionTimeout(30);
        return atomikosDataSourceBean;
    }
    @Bean(name="test2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2Datasource")DataSource dataSource) 
            throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果还有分页等其他事务
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper2/*.xml"));
        return bean.getObject();
    }

    @Bean(name="test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory")
    SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}