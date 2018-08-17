package com.yang.test.jpa.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.java.velocity.Column;
import com.yang.test.java.velocity.EntityGer;
import com.yang.test.java.velocity.ShuJuQianYiTable;
import com.yang.test.jpa.spring.entity.Account;
import com.yang.test.jpa.spring.entity.T;

@SuppressWarnings("unchecked")
@Repository("taskDao")
public class TaskDaoImpl implements TaskDao {

	//@PersistenceContext(unitName = "forC3po")
	private EntityManager em;
	//@PersistenceContext(unitName = "default")
	private EntityManager df;
	//@PersistenceContext(unitName = "forJdbc")
	private EntityManager jbdc;
	@PersistenceContext(unitName = "forDurid")
	private EntityManager durid;
	//@PersistenceContext(unitName = "forOracle")
	private EntityManager oracle;
	

	public static void main(String[] args) {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		final TaskDao b = (TaskDao) ac.getBean("taskDao");
		b.test();
	}

	public void test() {
		Query q = durid.createQuery("from Account");
		List<Acc> l = q.getResultList();
		if(l != null && l.size() > 0){
			for(Acc item : l){
				System.out.println(item.getAccountid());
			}
		}else{
			System.out.println("empty");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void test2() {
		CriteriaBuilder cb = df.getCriteriaBuilder();
		CriteriaQuery<?> query = cb.createQuery();
        Root<T> root = query.from(T.class);
        
        List<Selection<?>> selections = new ArrayList<Selection<?>>();
        selections.add(root.get("id").alias("id"));
        selections.add(cb.min(root.get("id").as(Integer.class)));

        query.multiselect(selections);
        query.groupBy(root.get("id"));
        query.having(cb.greaterThan(cb.count(root.get("value")).as(Integer.class), 1));
        
        
        TypedQuery q = df.createQuery(query);
        q.setFirstResult(0);
        q.setMaxResults(10);
		q.getResultList();
	}
	
	public static void main22(String[] args) throws IOException {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		final TaskDao b = (TaskDao) ac.getBean("taskDao");

		String[] s = new String[] {"Account", "AccountNewData", "AssessReportGeneration", "BaseModel", "Channel", "ChannelTemplate", "ChannelTemplateGroup", "CharacterCode",
				"CHECKDATA2011", "CHECKDATA2012", "CHECKDATA2013", "CHECKDATA2014", "CHECKDATA2015", "CHECKDATA2016", "CompanyAccount", "CustomFieldList", "CustomLabel", 
				"CustomPageField", "CustomPageList", "darea", "dauthority", "ddepartment", "ddisease", "ddoctor", "dgoods", "Dictionary", "dlable", "dprofile", "dresult",
				"dshelf", "ELMAH_Error", "ImportBaseInfo", "IntPrimaryKey",  "MedCustomer", "MessageTemplate", "ModelLevel", "ModelRecord", "ModelResultDistribute", 
				"Options", "PhysicalDept", "PhysicalItem", "PhysicalProject", "PlatformAbnormalItem", "PlatformAllDisease", "PlatformAllDiseaseApprovalHistory", "PlatformAllDiseaseChangeHistory",
				"PlatformAssessReportStyle", "PlatformAssessReportTemplate", "PlatformCheckReportStyle", "PlatformDept", "PlatformDictionary", "PlatformDisease", "PlatformDiseaseItem", 
				"PlatformDiseaseRelation", "PlatformDiseaseRelationAbnormal", "PlatformItem", "PlatformLifeProposal", "PlatformModelChangeHistory", "PlatformNutritionPrograms",
				"PlatformProject", "PlatformSportPrograms", "PlatformTeamAssessReportStyle", "PlatformTeamAssessReportTemplate",
				"QuestionBank", "QuestionBankOption", "QuestionResult", "QuestionResultDetail2017", "QuestionResultDetail2018", "ReserveExportExcelFormat", "Score", "SignGroupItem",
				"SignItem", "SignItemData", "SignItemGroup", "SignItemRecord", "SysActions", "SysChannel", "SysPermission", "SysRole", "SysRolePermission",
				"SysTheme", "SysUser", "Template", "Tenant", "TenantAbnormalItem", "TenantAccountAssessPackage", "TenantAccountHealthManage", "TenantAssessImportItemLevel", "TenantAssessPackage",
				"TenantAssessPackageModel", "TenantAssessReport", "TenantAssessReportStyle", "TenantAssessReportView", "TenantCheckReportStyle", "TenantConfigSetUp", "TenantCrowdGroup",
				"TenantDept", "TenantDictionary", "TenantDisease", "TenantDiseaseItem", "TenantDiseaseRelation", "TenantDiseaseRelationAbnormal", "TenantInterpose", "TenantInterposeCount", 
				"TenantInterposePackage", "TenantInterposePackageProject", "TenantInterposePlanRemind", "TenantInterposeProject", "TenantItem", "TenantLifeProposal", "TenantMessageSetUp",
				"TenantMessageTemplate", "TenantMessageUse", "TenantModelChangeHistory", "TenantNutritionPrograms", "TenantPackage", "TenantPackageItem", "TenantPackageType", "TenantPermission",
				"TenantProject", "TenantProjectTemplate", "TenantProjectTemplateItem", "TenantSetUpInfo", "TenantSportPrograms", "TenantSysActions", "TenantSysPerDistribution", "TenantTeamAssessImportDisease",
				"TenantTeamAssessImportDiseaseGroup", "TenantTeamAssessImportItemGroup", "TenantTeamAssessReportStyle", "TenantTeamAssessTemplate", "Topic", "User", "UserRightPermission",
				"xbmi", "xbodyneedheat", "xdevicetype", "xdiseasetype", "xdrug", "xenergyneed", "xhealthproposal", "xnutrientneeds", "xphysicalprojectitem", "xplatformprojectitem", "xrelationrelative",
				"xresult", "xrole", "xsmschannel", "xsport", "xtenantprojectitem", "xtopicandmodelrelationship", "xuserrole", "xzdinerinfo", "xzdinerinfodetail", "xzdiseasematerial", "xzfood",
				"xzfoodmaterial", "xzfoodnutrition", "xzmaterial", "xzmaterialcategory", "xzmaterialcategoryinfo", "xzmaterialnutrition", "xzrestaurant"};
		List<ShuJuQianYiTable> tables = new ArrayList<ShuJuQianYiTable>();
		for (String item : s) {
			tables.add(b.test2(item));
		}
		

		File file6 = new File("ShuJuQianYi.txt");
		FileWriter fileWriter6 = new FileWriter(file6);
		fileWriter6.write(EntityGer.createShuJuQianYi(tables));
		fileWriter6.close();
	}
	
	@Transactional(value = "transactionManager")
	public ShuJuQianYiTable test2(String tableName) throws IOException {
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

		ShuJuQianYiTable table = new ShuJuQianYiTable();
		table.setTableName("["+tableName+"]");
		
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
			if(!Boolean.TRUE.equals(table.getHsIdentity()) && Boolean.TRUE.equals(is_identity)){
				table.setHsIdentity(true);
			}
			
			Column column = new Column();
			column.name = (String)item[0];
			column.bigName = ((String)item[0]).substring(0, 1).toUpperCase() + ((String)item[0]).substring(1);
			column.type = type;
			column.note = (String)item[2];
			column.isPk = is_identity;
			columns.add(column);
			table.setColumnLink((table.getColumnLink() != null ? table.getColumnLink() : "") + "[" + (String)item[0] + "],");
		}
		table.setColumns(columns);
		table.setColumnLink(table.getColumnLink().substring(0, table.getColumnLink().length() - 1));
		return table;
	}

	public static void main2(String[] args) throws IOException {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		final TaskDao b = (TaskDao) ac.getBean("taskDao");

		for (String item : new String[] { "BasicInfo" }) {
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