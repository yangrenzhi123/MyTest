package com.yang.test.java.springboot;

import redis.clients.jedis.Jedis;

public class RenewalThread extends Thread {

	private int flag;
	private String key;
	private Jedis jedis;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Jedis getJedis() {
		return jedis;
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public void run() {
		while (true) {
			try {
				int flag = this.getFlag();
				if (flag == 0) {
					jedis.close();
					break;
				}

				Thread.sleep(15000);

				flag = this.getFlag();
				if (flag == 0) {
					jedis.close();
					break;
				}

				jedis.expire(key, 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}