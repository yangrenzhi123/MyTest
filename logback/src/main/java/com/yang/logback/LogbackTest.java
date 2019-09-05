package com.yang.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {

	final static Logger logger = LoggerFactory.getLogger(LogbackTest.class);

	public static void main(String[] args) {
		logger.debug("debug");
		logger.info("infoo");

		try {
			throw new RuntimeException("123");
		} catch (Exception e) {
			logger.error("出错", e);
			System.out.println();
		}
	}
}