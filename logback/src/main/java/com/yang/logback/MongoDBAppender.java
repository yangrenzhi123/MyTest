package com.yang.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class MongoDBAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	@Override
	protected void append(ILoggingEvent e) {
		StackTraceElement st = e.getCallerData()[0];
		if(st.getClassName().startsWith("com.yang")) {
			System.out.println(String.format("%s.%s:%d", st.getClassName(), st.getMethodName(), st.getLineNumber()));
			System.out.println(e.toString());
			System.out.println(e.getThrowableProxy().getMessage());
			System.out.println();
		}
	}
}
