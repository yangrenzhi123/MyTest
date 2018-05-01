package com.yang.test.java.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test2 {

	public static Logger logger = LogManager.getLogger();

	public static void log() {
		logger.info("com.yang.test.java.log");
	}
}