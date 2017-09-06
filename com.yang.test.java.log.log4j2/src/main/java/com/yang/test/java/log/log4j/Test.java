package com.yang.test.java.log.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

	public static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.info("i am info");
	}
}