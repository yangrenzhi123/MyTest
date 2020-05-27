package com.yang.test.java.springboot.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.test.java.springboot.entity.T;

public interface UserDao extends BaseMapper<T> {

	List<T> selectByPrimaryKey(Integer id);
}