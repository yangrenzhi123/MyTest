package com.yang.test.java.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Entity extends Parent{

	@Validate(requierdWithMsg = "true,ID不能为空")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		Entity entity = new Entity();
		entity.setId(1);
		entity.setT(null);
		Field[] fields = Entity.class.getDeclaredFields();
		Field[] field2 = Entity.class.getSuperclass().getDeclaredFields();
		Field[] field3 = Entity.class.getSuperclass().getSuperclass().getDeclaredFields();
		
		for(Field field : fields){
			Validate v = field.getAnnotation(Validate.class);
			String fieldName = field.getName();
			String rule = v.requierdWithMsg();
			String r1 = rule.split(",")[0];
			String r2 = rule.split(",")[1];
			if(r1.equals("true") && getFieldValueByName(fieldName, entity) == null){
				System.out.println(r2);
			}else{
				System.out.println("pass");
			}
		}

		for(Field field : field2){
			Validate v = field.getAnnotation(Validate.class);
			String fieldName = field.getName();
			String rule = v.requierdWithMsg();
			String r1 = rule.split(",")[0];
			String r2 = rule.split(",")[1];
			if(r1.equals("true") && getFieldValueByName(fieldName, entity) == null){
				System.out.println(r2);
			}else{
				System.out.println("pass");
			}
		}

		for(Field field : field3){
			Validate v = field.getAnnotation(Validate.class);
			String fieldName = field.getName();
			String rule = v.requierdWithMsg();
			String r1 = rule.split(",")[0];
			String r2 = rule.split(",")[1];
			if(r1.equals("true") && getFieldValueByName(fieldName, entity) == null){
				System.out.println(r2);
			}else{
				System.out.println("pass");
			}
		}
	}

	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("属性不存在");
			return null;
		}
	}
}