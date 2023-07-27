package com.mycompany.springwebapp.dao;

import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.mycompany.springwebapp.dto.Ch13Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class Ch13BoardDaoOld {
	@Resource
	private SqlSessionTemplate sst;
	
	public void insert(Ch13Board board) {
		sst.insert("com.mycompany.springwebapp.dao.mybatis.Ch13BoardDaoOld.insert", board);
	}
}
