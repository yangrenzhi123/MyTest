package com.yang.test.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.jpa.domain.Tree;

public interface TreeDao extends JpaRepository<Tree, Long> {

	@Transactional
	@Modifying
	@Query("delete from Tree t where t.parentId=:parentId")
	void deleteByParentId(@Param("parentId") Integer parentId);


	@Transactional
	@Modifying
	@Query("update Tree t set t.oId = :newVal where t.id=:id")
	void update(@Param("id") Long id, @Param("newVal") Long newVal);

	
	List<Tree> findByParentIdIn(List<Long> parentId);
	
	Tree findByOId(Integer oId);
}