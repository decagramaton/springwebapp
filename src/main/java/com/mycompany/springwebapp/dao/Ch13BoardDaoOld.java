package com.mycompany.springwebapp.dao;

import java.util.List;

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
	
	public List<Ch13Board> selectAll() {
		List<Ch13Board> boardList = sst.selectList("com.mycompany.springwebapp.dao.mybatis.Ch13BoardDaoOld.selectAll");
		
		for(Ch13Board board : boardList) {
			log.info(board.toString());
		}
		
		return boardList;
	}
	
	public void updateByBno() {
		List<Ch13Board> boardList = selectAll();
		Ch13Board board = boardList.get(1);
		
		board.setBtitle("변경된 제목");
		board.setBcontent("변경된 내용");
		
		sst.update("com.mycompany.springwebapp.dao.mybatis.Ch13BoardDaoOld.updateByBno", board);
	}
	
	public void deleteByBno() {
		int bno = 1;
		sst.delete("com.mycompany.springwebapp.dao.mybatis.Ch13BoardDaoOld.deleteByBno", bno);
	}
}
