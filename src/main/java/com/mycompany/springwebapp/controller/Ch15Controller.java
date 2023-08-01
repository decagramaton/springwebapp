package com.mycompany.springwebapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.aspect.Login;
import com.mycompany.springwebapp.aspect.RuntimeCheck;

import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Pager;
import com.mycompany.springwebapp.dto.Ch15Account;
import com.mycompany.springwebapp.service.Ch13BoardService;
import com.mycompany.springwebapp.service.Ch15AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch15")
public class Ch15Controller {
	
	@Resource
	private Ch15AccountService accountService;

	@RequestMapping("/content")
	public String content(Model model) {
		
		List<Ch15Account> list = accountService.getAccount();
		model.addAttribute("list", list);
		
		return "ch15/content";
	}
	
	@PostMapping("/transfer")
	public String transfer(int fromAno, int toAno, int amount) {
		accountService.transfer(fromAno, toAno, amount);
		return "redirect:/ch15/content";
	}
	
}
