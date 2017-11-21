package com.yang.test.java.velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class EntityGer {

	public static void main(String[] args) throws IOException {
		String entityName = "KaoQin";
		File file = new File(entityName + "Controller.java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createController(entityName);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void main1(String[] args) throws IOException {
		String[] s = { "Attendance", "Building", "Classes", "Record", "Student", "Teacher" };
		for (String entityName : s) {
			File file = new File("I" + entityName + "Dao.java");
			FileWriter fileWriter = new FileWriter(file);

			String re = createDao(entityName);
			fileWriter.write(re);
			fileWriter.close();
		}
	}

	public static String createController(String entityName) {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/controller.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("entityName", entityName);

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createDao(String entityName) {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/dao.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("entityName", entityName);

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createEntity(String tableName, List<Column> columns) {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/entity.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("package", "com.xk.hb.entity");
		context.put("tableName", tableName);
		context.put("imports", new String[] { "javax.persistence.Entity", "javax.persistence.Id" });
		context.put("columns", columns);

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}
}