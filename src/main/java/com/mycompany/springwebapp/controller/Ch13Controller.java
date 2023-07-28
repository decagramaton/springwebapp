package com.mycompany.springwebapp.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dao.Ch13BoardDao;
import com.mycompany.springwebapp.dao.Ch13BoardDaoOldmpl;
import com.mycompany.springwebapp.dto.Ch13Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	
	@Resource
	private Ch13BoardDaoOldmpl boardDaoOld;
	
	@Autowired
	private Ch13BoardDao boardDao;
	
	
	@RequestMapping("/content")
	public String content() {
		return "ch13/content";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		
		Ch13Board board = new Ch13Board();
		board.setBtitle("제목5");
		board.setBcontent("내용5");
		board.setMid("user");
		
		//boardDaoOld.insert(board);
		boardDao.insert(board);
		
		log.info("저장된 board no : " + board.getBno());
		
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/getBoardList")
	public String getBoardList() {
		//List<Ch13Board> boardList = boardDaoOld.selectAll();
		List<Ch13Board> boardList = boardDao.selectAll();
		
		log.info(boardList.toString());
		
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard() {
		
		Ch13Board board = boardDao.selectByBno(4);
		board.setBtitle("변경된 제목");
		board.setBcontent("변경된 내용");
		
		//boardDaoOld.updateByBno(board);
		boardDao.updateByBno(board);
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(int bno) {
		//boardDaoOld.deleteByBno(bno);
		boardDao.deleteByBno(bno);
		return "redirect:/ch13/content";
	}
}
