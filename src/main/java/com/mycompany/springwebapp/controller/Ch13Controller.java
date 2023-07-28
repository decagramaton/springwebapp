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
import com.mycompany.springwebapp.dao.Ch13MemberDao;
import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Member;
import com.mycompany.springwebapp.dto.Ch13Pager;
import com.mycompany.springwebapp.service.Ch13BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	
	@Resource
	private Ch13BoardDaoOldmpl boardDaoOld;
	
	@Autowired
	private Ch13BoardService boardService;

	
	
	
	
	
	@RequestMapping("/content")
	public String content() {
		return "ch13/content";
	}
	
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		

		Ch13Board board = new Ch13Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setMid("user");
		
		boardService.write(board);

		
		//boardDaoOld.insert(board);
		
		log.info("저장된 board no : " + board.getBno());
		
		return "redirect:/ch13/content";
	}
	
	
	@GetMapping("/getPageList")
	public String getPageList() {
		int totalBoardNum = boardService.getTotalBoardNum();
		Ch13Pager pager = new Ch13Pager(10, 5, totalBoardNum, 1);
		List<Ch13Board> boardList = boardService.getList(pager);
		
		for(Ch13Board board : boardList) {
			log.info(board.toString());
		}
		
		return "redirect:/ch13/content";
	}
	
	
	@GetMapping("/getBoardList")
	public String getBoardList() {
		//List<Ch13Board> boardList = boardDaoOld.selectAll();
		int bno = 1;
		Ch13Board board = boardService.getBoard(bno);
		
		log.info(board.toString());
		
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard() {
		int bno = 1;
		Ch13Board board = boardService.getBoard(bno);
		board.setBtitle("변경된 제목");
		board.setBcontent("변경된 내용");
		
		//boardDaoOld.updateByBno(board);
		boardService.modify(board);
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(int bno) {
		//boardDaoOld.deleteByBno(bno);
		boardService.remove(bno);
		return "redirect:/ch13/content";
	}
}
