package com.mycompany.springwebapp.dao;

import java.util.List;

import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.mycompany.springwebapp.dto.Ch13Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class Ch13BoardDaoOldmpl implements Ch13BoardDaoOld {
	@Resource
	private SqlSessionTemplate sst;
	
	public int insert(Ch13Board board) {
		/*
		 * com.mycompany.springwebapp.dao.mybatis.Ch13BoardDaoOld : Mapper XML 선택
		 * insert : Mapper XML 안에 선언된 ID
		 * 리턴 값 : DB에 반영된 행의 개수
		 */
		int successRows = sst.insert("com.mycompany.springwebapp.dao.Ch13BoardDaoOld.insert", board);
	
		return successRows;
	}
	
	public List<Ch13Board> selectAll() {
		List<Ch13Board> boardList = sst.selectList("com.mycompany.springwebapp.dao.Ch13BoardDaoOld.selectAll");
		
		return boardList;
	}
	
	public Ch13Board selectByBno(int bno) {
		Ch13Board board = sst.selectOne("com.mycompany.springwebapp.dao.Ch13BoardDaoOld.selectByBno", bno);
		
		return board;
	}
	
	public int updateByBno(Ch13Board board) {
		int successRows = sst.update("com.mycompany.springwebapp.dao.Ch13BoardDaoOld.updateByBno", board);
	
		return successRows;
	}
	
	public int deleteByBno(int bno) {
		int successRows = sst.delete("com.mycompany.springwebapp.dao.Ch13BoardDaoOld.deleteByBno", bno);
		
		return successRows;
	}
}
