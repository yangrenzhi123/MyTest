package com.yang.test.java.orm.mybatis;

import org.apache.ibatis.annotations.Select;

public interface IUser {
	
    @Select("select * from T where id = #{id}")
    public T getUserByID(int id);
}