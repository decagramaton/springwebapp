package com.mycompany.springwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springwebapp.dao.Ch13BoardDao;
import com.mycompany.springwebapp.dao.Ch13BoardDaoOld;
import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Ch13BoardServiceImpl implements Ch13BoardService{
	
	@Autowired
	private Ch13BoardDao boardDao;
	
	/*@Autowired
	private Ch13BoardDaoOld boardDaoOld;*/
	
	@Override
	public void write(Ch13Board board) {
		boardDao.insert(board);
		//boardDaoOld.insert(board);
	}
	
	@Override
	public void remove(int bno) {
		boardDao.deleteByBno(bno);
		//boardDaoOld.deleteByBno(bno);
	}
	
	@Override
	public void modify(Ch13Board board) {
		Ch13Board dbBoard = boardDao.selectByBno(board.getBno());
		
		
		boardDao.updateByBno(board);
	}
	
	@Override
	public List<Ch13Board> getList(Ch13Pager pager) {
		List<Ch13Board> boardList = boardDao.selectByPage(pager);
		
		return boardList;
	}
	
	@Override
	public Ch13Board getBoard(int bno) {
		Ch13Board board = boardDao.selectByBno(bno);
		
		return board;
	}
	
	@Override
	public void addHitCount(int bno) {
		Ch13Board dbBoard = boardDao.selectByBno(bno);
		dbBoard.setBhitcount(dbBoard.getBhitcount()+1);
		
		boardDao.updateByBno(dbBoard);
	}
	
	@Override
	public int getTotalBoardNum() {
		int count = boardDao.count();
		
		return count;
	}
}
