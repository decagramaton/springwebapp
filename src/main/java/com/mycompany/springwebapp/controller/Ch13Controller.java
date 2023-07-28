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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	
	@Resource
	private Ch13BoardDaoOldmpl boardDaoOld;
	
	@Autowired
	private Ch13BoardDao boardDao;
	
	@Autowired
	private Ch13MemberDao memberDao;
	
	
	@RequestMapping("/content")
	public String content() {
		return "ch13/content";
	}
	
	
	@GetMapping("/insertMember")
	public String insertMember() {
		Ch13Member member = new Ch13Member();
		member.setMid("user1");
		member.setMname("사용자1");
		member.setMpassword("12345");
		member.setMemail("이메일1");
		memberDao.insert(member);
		
		log.info("저장된 board no : " + member.getMid());
		
		return "redirect:/ch13/content";
	}
	
	@GetMapping("/getMemberList")
	public String getMemberList() {
		Ch13Member member = memberDao.selectMyMid("user1");
		log.info(member.toString());
		
		return "redirect:/ch13/content";
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		

		Ch13Board board = new Ch13Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setMid("user");
		boardDao.insert(board);

		
		//boardDaoOld.insert(board);
		
		log.info("저장된 board no : " + board.getBno());
		
		return "redirect:/ch13/content";
	}
	
	
	@GetMapping("/getPageList")
	public String getPageList() {
		int totalRows = boardDao.count();
		Ch13Pager pager = new Ch13Pager(10, 5, totalRows, 1);
		List<Ch13Board> boardList = boardDao.selectPageView(pager);
		
		for(Ch13Board board : boardList) {
			log.info(board.toString());
		}
		
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
