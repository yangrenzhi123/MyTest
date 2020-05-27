package com.yang.test.java.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.test.java.springboot.entity.T;

public interface UserDao extends BaseMapper<T> {

	T selectByPrimaryKey(Integer id);
}