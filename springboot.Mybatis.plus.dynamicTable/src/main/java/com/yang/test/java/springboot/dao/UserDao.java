package com.yang.test.java.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.test.java.springboot.entity.Tt;

public interface UserDao extends BaseMapper<Tt> {

	Tt selectByPrimaryKey(Integer id);
}