package com.mycompany.springwebapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springwebapp.dto.Ch07Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch07")
public class Ch07Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch07/content";
	}
	
	// Model 이용
	@GetMapping("/useRequest1")
	public String useRequest1(HttpServletRequest request) {
		List<Ch07Board> boards = new ArrayList<>();
		
		for(int i=1; i<=5; i++) {
			Ch07Board board = new Ch07Board();
			board.setBno(i);
			board.setBtitle("제목"+i);
			board.setBcontent("내용"+i);
			board.setBwriter("글쓴이"+i);
			board.setBdate(new Date());
			boards.add(board);
		}
		
		request.setAttribute("boardList", boards);
		
		return "ch07/request";
	}
	
	// ModelAndView 이용 - 잘안씀
	@GetMapping("/useRequest2")
	public ModelAndView useRequest2() {
		List<Ch07Board> boards = new ArrayList<>();
		
		for(int i=1; i<=5; i++) {
			Ch07Board board = new Ch07Board();
			board.setBno(i);
			board.setBtitle("제목"+i);
			board.setBcontent("내용"+i);
			board.setBwriter("글쓴이"+i);
			board.setBdate(new Date());
			boards.add(board);
		}
		
		ModelAndView mnv = new ModelAndView();
		
		// request.setAttritube()와 동일한 기능
		mnv.addObject("boardList", boards);
		mnv.setViewName("ch07/request");
		
		
		
		return mnv;
	}
	
	// Model 매개변수 이용 - 가장 많이 사용
	@GetMapping("/useRequest3")
	public String useRequest3(Model model) {
		List<Ch07Board> boards = new ArrayList<>();
		
		for(int i=1; i<=5; i++) {
			Ch07Board board = new Ch07Board();
			board.setBno(i);
			board.setBtitle("제목"+i);
			board.setBcontent("내용"+i);
			board.setBwriter("글쓴이"+i);
			board.setBdate(new Date());
			boards.add(board);
		}
		
		
		// request.setAttritube()와 동일한 기능
		model.addAttribute("boardList", boards);
		
		return "ch07/request";
	}
	
	// Command 객체 이용
	@GetMapping("/useRequest4")
	public String useRequest4(Ch07Board board) {
		//request.setAttribute("ch07Board", board); 와 같은 기능
		log.info(board.toString());
		
		return "ch07/request";
	}
	
	// Command 객체에서 @ModelAttribute 이용
	@GetMapping("/useRequest5")
	public String useRequest5(@ModelAttribute("newBoard") Ch07Board board) {
		//request.setAttribute("newBoard", board); 와 같은 기능
		return "ch07/request";
	}
}
