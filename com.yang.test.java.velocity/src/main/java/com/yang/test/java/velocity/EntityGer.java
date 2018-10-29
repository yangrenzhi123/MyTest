package com.yang.test.java.velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class EntityGer {

	public static void main(String[] args) throws IOException {
		String[] s = new String[] {"Product", "产品"};
		t1(s);
		t2(s);
		t3(s);
		t4(s);
		t5(s);
		t6(s);
		t7(s);
		t8(s);
		t9(s);
		t10(s);
	}

	public static void t1(String[] s) throws IOException {
		File file = new File("result/" + s[0] + "Mapping.xml");
		FileWriter fileWriter = new FileWriter(file);

		String re = createMapper(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t2(String[] s) throws IOException {
		File file = new File("result/" + s[0] + ".java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createEntity(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t3(String[] s) throws IOException {
		File file = new File("result/" + s[0] + "DTO.java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createDTO(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t4(String[] s) throws IOException {
		File file = new File("result/" + s[0] + "DAO.java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createDao(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t5(String[] s) throws IOException {
		File file = new File("result/" + s[0] + "DAOTest.java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createDaoTest(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t6(String[] s) throws IOException {
		File file = new File("result/" + s[0] + "DAOController.java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createDaoController(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t7(String[] s) throws IOException {
		File file = new File("result/" + s[0] + "RPC.java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createRPC(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t8(String[] s) throws IOException {
		File file = new File("result/" + s[0] + "Service.java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createService(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t9(String[] s) throws IOException {
		File file = new File("result/" + s[0] + "ServiceTest.java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createServiceTest(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t10(String[] s) throws IOException {
		File file = new File("result/" + s[0] + "Controller.java");
		FileWriter fileWriter = new FileWriter(file);

		String re = createController(s[0], s[1]);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static void t4(String entityName) throws IOException {
		List<Column> columns = new ArrayList<Column>();

		Column id = new Column();
		id.isPk = true;
		id.name = "id";
		id.bigName = "Id";
		id.type = "Integer";
		columns.add(id);

		String[] c = { "placeName" };
		for (String item : c) {
			Column column = new Column();
			column.name = item;
			column.bigName = item.substring(0, 1).toUpperCase() + item.substring(1);
			column.type = "String";
			columns.add(column);
		}

		File file3 = new File(entityName + "Query.java");
		FileWriter fileWriter3 = new FileWriter(file3);
		String re3 = createQueryModel(entityName, columns);
		fileWriter3.write(re3);
		fileWriter3.close();

		File file2 = new File(entityName + "Controller.java");
		FileWriter fileWriter2 = new FileWriter(file2);
		String re2 = createController(entityName, columns);
		fileWriter2.write(re2);
		fileWriter2.close();

		File file = new File(entityName + ".java");
		FileWriter fileWriter = new FileWriter(file);
		String re = createView(entityName, "com.xk.campushealth.entity", columns);
		fileWriter.write(re);
		fileWriter.close();
	}

	public static String test() {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/t.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("t", 2);

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createQueryModel(String tableName, List<Column> columns) {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/queryModel.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("tableName", tableName);
		context.put("columns", columns);

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createController(String entityName, List<Column> columns) {
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
		context.put("smEntityName", entityName.substring(0, 1).toLowerCase() + entityName.substring(1));
		context.put("columns", columns);

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createService(String entityName) {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/service.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("entityName", entityName);

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createServiceImpl(String entityName) {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/serviceImpl.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("entityName", entityName);
		context.put("smallEntityName", entityName.substring(0, 1).toLowerCase() + entityName.substring(1));

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createDao(String entity, String entityCN) {
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
		context.put("entity", entity);
		context.put("entityCN", entityCN);
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createDaoTest(String entity, String entityCN) {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/daoTest.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("entity", entity);
		context.put("entityCN", entityCN);
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createView(String tableName, String packageName, List<Column> columns) {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/view.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("package", packageName);
		context.put("tableName", tableName);
		context.put("columns", columns);

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}
	

	public static String createMapper(String entity, String entityCN) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();

		Template t = ve.getTemplate("com/yang/test/java/velocity/mapper.vm", "UTF-8");

		VelocityContext context = new VelocityContext();
		context.put("entity", entity);
		context.put("entitym", entity.substring(0, 1).toLowerCase() + entity.substring(1, entity.length()));
		context.put("entityCN", entityCN);
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createDaoController(String entity, String entityCN) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();

		Template t = ve.getTemplate("com/yang/test/java/velocity/daocontroller.vm", "UTF-8");

		VelocityContext context = new VelocityContext();
		context.put("entity", entity);
		context.put("entitym", entity.substring(0, 1).toLowerCase() + entity.substring(1, entity.length()));
		context.put("entityCN", entityCN);
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createEntity(String entity, String entityCN) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();

		Template t = ve.getTemplate("com/yang/test/java/velocity/entity.vm", "UTF-8");

		VelocityContext context = new VelocityContext();
		context.put("entity", entity);
		context.put("entityCN", entityCN);
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createDTO(String entity, String entityCN) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();

		Template t = ve.getTemplate("com/yang/test/java/velocity/dto.vm", "UTF-8");

		VelocityContext context = new VelocityContext();
		context.put("entity", entity);
		context.put("entityCN", entityCN);
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}
	


	public static String createRPC(String entity, String entityCN) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();

		Template t = ve.getTemplate("com/yang/test/java/velocity/rpc.vm", "UTF-8");

		VelocityContext context = new VelocityContext();
		context.put("entity", entity);
		context.put("entityCN", entityCN);
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createService(String entity, String entityCN) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();

		Template t = ve.getTemplate("com/yang/test/java/velocity/service.vm", "UTF-8");

		VelocityContext context = new VelocityContext();
		context.put("entity", entity);
		context.put("entityCN", entityCN);
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createServiceTest(String entity, String entityCN) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();

		Template t = ve.getTemplate("com/yang/test/java/velocity/serviceTest.vm", "UTF-8");

		VelocityContext context = new VelocityContext();
		context.put("entity", entity);
		context.put("entityCN", entityCN);
		context.put("entitym", entity.substring(0, 1).toLowerCase() + entity.substring(1, entity.length()));
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createController(String entity, String entityCN) {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();

		Template t = ve.getTemplate("com/yang/test/java/velocity/controller.vm", "UTF-8");

		VelocityContext context = new VelocityContext();
		context.put("entity", entity);
		context.put("entityCN", entityCN);
		context.put("entitym", entity.substring(0, 1).toLowerCase() + entity.substring(1, entity.length()));
		context.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}

	public static String createShuJuQianYi(List<ShuJuQianYiTable> tables) {
		// 创建引擎
		VelocityEngine ve = new VelocityEngine();
		// 设置模板加载路径，这里设置的是class下
		ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		// 进行初始化操作
		ve.init();
		// 加载模板，设定模板编码
		Template t = ve.getTemplate("com/yang/test/java/velocity/ShuJuQianYi.vm", "UTF-8");
		// 设置初始化数据
		VelocityContext context = new VelocityContext();
		context.put("tables", tables);

		// 设置输出
		StringWriter writer = new StringWriter();
		// 将环境数据转化输出
		t.merge(context, writer);

		return writer.toString();
	}
}