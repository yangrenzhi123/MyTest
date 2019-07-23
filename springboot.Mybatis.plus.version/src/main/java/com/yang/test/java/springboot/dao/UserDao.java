package com.yang.test.java.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.test.java.springboot.entity.User;

public interface UserDao extends BaseMapper<User> {

	int updateById(User u);
}