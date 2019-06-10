package com.yang.test.java.validate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("rawtypes")
public class Validator {

	
	/** 使用场景：当对数据做完整检验，且有不符合条件的情况，则抛出异常提示总共有哪些字段不满足要求 */
	public static void dealMessages(List<Message> messages) {
		if (messages != null && messages.size() > 0) {
			String content = "";
			if(messages != null && messages.size() > 0){
				for(Message item : messages){
					content = content + item.getContent() + "<br />";
				}
			}
			
			throw new BankBusinessValidateException(messages, content);
		}
	}

	public static List<Message> doValidate(Object entity) {
		if (entity == null) {
			throw new RuntimeException("entity 不能为空");
		}
		return doValidate(entity.getClass(), entity);
	}

	public static List<Message> doValidate(Class klass, Object entity) {
		List<Message> result = new ArrayList<Message>();

		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			Validate v = field.getAnnotation(Validate.class);
			Notes n = field.getAnnotation(Notes.class);
			String filedCn = n != null?n.filedCn():"";
			String fieldName = field.getName();
			
			if(v != null){
				boolean requierd = v.requierd();
				Object fv = getFieldValueByName(fieldName, entity);
				if(requierd){
					if(fv == null || (fv instanceof String && StringUtils.isEmpty((String)fv))){
						Message message = new Message();
						message.setType(ValidateType.required);
						message.setContent(filedCn + "不能为空");
						result.add(message);
					}
				}
				
				String requierdWithMsg = v.requierdWithMsg();
				if(StringUtils.isNotEmpty(requierdWithMsg)){
					if(fv == null || (fv instanceof String && StringUtils.isEmpty((String)fv))){
						Message message = new Message();
						message.setType(ValidateType.requierdWithMsg);
						message.setContent(requierdWithMsg);
						result.add(message);
					}
				}
				
				int length = v.length();
				if(length != 0 && fv != null){
					if(fv instanceof String && StringUtils.isNotEmpty((String)fv) && ((String) fv).length() != length){
						Message message = new Message();
						message.setType(ValidateType.length);
						message.setContent(filedCn + "限制长度为" + length);
						result.add(message);
					}else if((fv instanceof Long || fv instanceof Integer) && (fv + "").length() != length){
						Message message = new Message();
						message.setType(ValidateType.length);
						message.setContent(filedCn + "限制长度为" + length);
						result.add(message);
					}
				}
				
				int maxLength = v.maxLength();
				if(maxLength > 0){
					if(fv != null && fv instanceof String && ((String) fv).length() > maxLength){
						Message message = new Message();
						message.setType(ValidateType.maxLength);
						message.setContent(filedCn + "限制最大长度为" + maxLength);
						result.add(message);
					}
				}
				
				String startwith = v.startWith();
				if(StringUtils.isNotEmpty(startwith)){
					if(fv != null && fv instanceof String && ((String)fv).startsWith(startwith) == false){
						Message message = new Message();
						message.setType(ValidateType.startwith);
						message.setContent(filedCn + "必须以" + startwith + "开头");
						result.add(message);
					}
				}

				int[] amount = v.amount();
				if(amount.length == 2){
					if(fv != null && fv instanceof String){
						String value = (String) fv;
						if(StringUtils.isNotEmpty((String) fv)){
							String amountMess = "";
							
							String[] sa = value.split("\\.");
							if(sa.length == 2){
								if(sa[0].length() >  amount[0]){
									amountMess = "金额格式位数要求["+amount[0]+", "+amount[1]+"]";
								}
								if(sa[1].length() != amount[1]){
									amountMess = "金额格式位数要求["+amount[0]+", "+amount[1]+"]";
								}
								try{
									Double.parseDouble(value);
								}catch(NumberFormatException e){
									amountMess = "金额格式有误";
								}
							}else{
								amountMess = "金额格式位数要求["+amount[0]+", "+amount[1]+"]";
							}
							if(StringUtils.isNotEmpty(amountMess)){
								Message message = new Message();
								message.setType(ValidateType.startwith);
								message.setContent(amountMess);
								result.add(message);
							}
						}
					}
				}
			}
		}

		return result;
	}

	public static Object getFieldValueByName(String fieldName, Object o) {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getter = "get" + firstLetter + fieldName.substring(1);
		Object value = null;
		Method method = null;
		try {
			method = o.getClass().getMethod(getter, new Class[] {});
			value = method.invoke(o, new Object[] {});
		} catch (Exception e) {
			getter = "get" + fieldName;
			try{
				method = o.getClass().getMethod(getter, new Class[] {});
				value = method.invoke(o, new Object[] {});
			}catch(Exception e2){
				System.out.println("属性未找到");
			}
		}
		return value;
	}
}