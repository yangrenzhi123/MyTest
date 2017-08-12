package com.yang.test.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.test.jpa.dao.TreeDao;
import com.yang.test.jpa.domain.Tree;

@Service
public class TreeServiceImpl implements TreeService {

	@Autowired
	private TreeDao treeDao;

	public List<Long> getSub(Long parentId) {
		List<Tree> result = new ArrayList<Tree>();
		
		List<Long> parentsIds = new ArrayList<Long>(1);
		parentsIds.add(parentId);
		while(true){
			List<Tree> l = treeDao.findByParentIdIn(parentsIds);
			if(result != null && l.size() > 0){
				result.addAll(l);
				parentsIds = new ArrayList<Long>(l.size());
				for(Tree t : l){
					parentsIds.add(t.getoId());
				}
			}else{
				break;
			}
		}
		
		List<Long> l = new ArrayList<Long>();
		for(Tree tree : result){
			l.add(tree.getoId());
		}
		return l;
	}


	@Transactional
	public void test() {
		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:root-context.xml");
		
		TreeDao b = (TreeDao) ac.getBean("treeDao");
		Tree t = b.findOne(1110394L);
		
		b.update(1110394L, t.getoId() + 1);
		System.out.println(t.getoId());
	}

}
