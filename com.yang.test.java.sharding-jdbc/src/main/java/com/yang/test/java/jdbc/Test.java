package com.yang.test.java.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;

@SuppressWarnings("deprecation")
public class Test {

	public static void main(String[] args) throws SQLException {
		Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>(2);
		dataSourceMap.put("TEST0", createDataSource("TEST0"));
		dataSourceMap.put("TEST1", createDataSource("TEST1"));

		DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);

		TableRule tableRule = new TableRule("TT", false, Arrays.asList("TT0", "TT1"), dataSourceRule, null, null, null);

		ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule)
				.tableRules(Arrays.asList(tableRule))
				.databaseShardingStrategy(
						new DatabaseShardingStrategy("sharding_column", new ModuloDatabaseShardingAlgorithm()))
				.tableShardingStrategy(new TableShardingStrategy("sharding_column", new ModuloTableShardingAlgorithm()))
				.build();

		DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
		String sql = "SELECT * FROM TT o";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			try (ResultSet rs = preparedStatement.executeQuery()) {
				while (rs.next()) {
					System.out.println(rs.getInt(1));
				}
			}
		}
	}

	private static DataSource createDataSource(String dataSourceName) {
		BasicDataSource result = new BasicDataSource();
		result.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
		result.setUrl("jdbc:mysql://127.0.0.1:3306/" + dataSourceName + "?serverTimezone=UTC");
		result.setUsername("root");
		result.setPassword("1qazxcvbnm,./");
		return result;
	}
}