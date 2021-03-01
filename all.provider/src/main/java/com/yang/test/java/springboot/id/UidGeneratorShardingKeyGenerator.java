package com.yang.test.java.springboot.id;

import java.util.Properties;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

public class UidGeneratorShardingKeyGenerator implements ShardingKeyGenerator {

    public static final IdWorker ID = new IdWorker(0, 0, 0);

    public Comparable<?> generateKey() {
        long id = ID.nextId();
        return id;
    }

    public String getType() {
        return "UidGenerator";
    }

	@Override
	public Properties getProperties() {
		return null;
	}

	@Override
	public void setProperties(Properties arg0) {
	}
}