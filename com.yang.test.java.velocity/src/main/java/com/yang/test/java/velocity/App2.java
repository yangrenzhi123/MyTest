package com.yang.test.java.velocity;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class App2 {
	public static String exportFixedVelocity() {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			// 进行初始化操作
			ve.init();
			
			
			
			// 加载模板，设定模板编码
			//Template t = ve.getTemplate("com/yang/test/java/velocity/1.vm", "UTF-8");
			// 设置初始化数据
			VelocityContext context = new VelocityContext();
			context.put("name", "张三");

			String[] hobbyArray = { "吃饭", "喝水", "洗澡" };
			context.put("hobby", "爱好");
			context.put("hobbyArray", hobbyArray);

			// 设置输出
			StringWriter writer = new StringWriter();
			// 将环境数据转化输出
			//t.merge(context, writer);
			
			Reader reader = new FileReader(new File("C:/Users/yrz/Documents/workspace-sts-3.5.1.RELEASE/com.yang.test.java.velocity/src/main/java/com/yang/test/java/velocity/1.vm"));

			ve.evaluate(context, writer, "", reader);
			
			return writer.toString();

		} catch (Exception e) {
			throw new RuntimeException("模版转化错误!");
		}
	}

	public static void main(String[] args) {
		System.out.println(exportFixedVelocity());
		;
	}
}
