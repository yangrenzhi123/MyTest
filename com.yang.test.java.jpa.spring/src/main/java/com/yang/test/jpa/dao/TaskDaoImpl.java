package com.yang.test.jpa.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.java.velocity.Column;
import com.yang.test.java.velocity.EntityGer;

@SuppressWarnings("unchecked")
@Repository("taskDao")
public class TaskDaoImpl implements TaskDao {

	@PersistenceContext(unitName = "forC3po")
	private EntityManager em;
	@PersistenceContext(unitName = "default")
	private EntityManager df;

	public static void main(String[] args) throws IOException {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		final TaskDao b = (TaskDao) ac.getBean("taskDao");

		for (String item : new String[] { "JHCheckdata" }) {
			b.test(item);
		}
	}

	@Transactional(value = "transactionManager")
	public void test(String tableName) throws IOException {
		String sql = 
		"SELECT"+
		    " B.NAME AS column_name,"+
		    " d.NAME,"+
		    " C.VALUE AS column_description,"+
			" b.is_identity"+
		" FROM"+
		    " sys.TABLES A"+
		    " INNER JOIN sys.COLUMNS B ON B.object_id = A.object_id"+
		    " LEFT JOIN sys.types d ON b.user_type_id = d.user_type_id"+
		    " LEFT JOIN sys.extended_properties C ON C.major_id = B.object_id"+
		    " AND C.minor_id = B.column_id"+
		" WHERE"+
		    " A.NAME = '"+tableName+"'";

		Query q = df.createNativeQuery(sql);
		List<Object[]> l = q.getResultList();
		List<Column> columns = new ArrayList<Column>();
		for(Object[] item : l){
			String type;
			String t = (String)item[1];
			if("int".equals(t) || "bigint".equals(t)||"tinyint".equals(t)){
				type = "Integer";
			}else if("bit".equals(t)){
				type = "Boolean";
			}else if("datetime".equals(t)){
				type = "Date";
			}else if("datetime".equals(t)||"date".equals(t)){
				type = "Date";
			}else {
				type = "String";
			}

			Boolean is_identity = (Boolean)item[3];
			
			Column column = new Column();
			column.name = (String)item[0];
			column.bigName = ((String)item[0]).substring(0, 1).toUpperCase() + ((String)item[0]).substring(1);
			column.type = type;
			column.note = (String)item[2];
			column.isPk = is_identity;
			columns.add(column);
		}

		File file6 = new File(tableName + "Service.java");
		FileWriter fileWriter6 = new FileWriter(file6);
		fileWriter6.write(EntityGer.createServiceImpl(tableName));
		fileWriter6.close();

		File file5 = new File("I" + tableName + "Service.java");
		FileWriter fileWriter5 = new FileWriter(file5);
		fileWriter5.write(EntityGer.createService(tableName));
		fileWriter5.close();

		File file4 = new File("I" + tableName + "Dao.java");
		FileWriter fileWriter4 = new FileWriter(file4);
		String re4 = EntityGer.createDao(tableName);
		fileWriter4.write(re4);
		fileWriter4.close();

		File file3 = new File(tableName + "Query.java");
		FileWriter fileWriter3 = new FileWriter(file3);
		String re3 = EntityGer.createQueryModel(tableName, columns);
		fileWriter3.write(re3);
		fileWriter3.close();

		File file2 = new File(tableName + "Controller.java");
		FileWriter fileWriter2 = new FileWriter(file2);
		fileWriter2.write(EntityGer.createController(tableName, columns));
		fileWriter2.close();

		File file = new File(tableName + ".java");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(EntityGer.createEntity(tableName, "com.xk.campushealth.entity", columns));
		fileWriter.close();
	}
}