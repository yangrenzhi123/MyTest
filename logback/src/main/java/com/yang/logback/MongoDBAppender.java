package com.yang.logback;

import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class MongoDBAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	static MongoCollection c;
	static {
		MongoClient mongoClient = new MongoClient("172.28.51.33", 27017);
		MongoDatabase mgdb = mongoClient.getDatabase("test");
		c = mgdb.getCollection("log");
	}
	
	@Override
	protected void append(ILoggingEvent e) {
		StackTraceElement st = e.getCallerData()[0];
		if(st.getClassName().startsWith("com.yang")) {

			final Document doc = new Document();
			doc.append("time", new Date());
			doc.append("level", e.getLevel().toString());
			doc.append("logger", e.getLoggerName());
			doc.append("thread", e.getThreadName());
			doc.append("message", e.getFormattedMessage());
			doc.append("detail", String.format("%s.%s:%d", st.getClassName(), st.getMethodName(), st.getLineNumber()));


			c.insertOne(doc);
		}
	}
}
