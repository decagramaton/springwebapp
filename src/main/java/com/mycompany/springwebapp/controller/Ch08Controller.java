package com.mycompany.springwebapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch08Item;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch08")
public class Ch08Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}
	
	@PostMapping("/addCart")
	public String addCart(Ch08Item item, HttpSession session) {
		// get Cart Object in Session
		List<Ch08Item> cart = (List<Ch08Item>) session.getAttribute("cart");
		
		// 세션에 카드가 없을 경우, 새 객체를 세션에 저장
		if(cart == null) {
			cart = new ArrayList<Ch08Item>();
			session.setAttribute("cart", cart);
		}
		
		boolean exist = false;
		for(Ch08Item cartItem : cart) {
			// 카드에 기존 아이템이 있는 경우, 수량 수정
			if(cartItem.getName().equals(item.getName())) {
				cartItem.setAmount(cartItem.getAmount() + item.getAmount());
				exist = true;
			}
		}
		
		// 카드에 없는 새로운 아이템이 있는 경우.
		if(exist == false) {
			cart.add(item);
		}
		
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/clearCart")
	public String clearCart(HttpSession session) {
		// 세션 삭제 방법1. 세션에 저장된 객체 삭제
		session.removeAttribute("cart");
		
		// 세션 삭제 방법2. 세션 자체를 무효화(세션에 저장된 모든 정보 소멸)
		// 현 HttpSession의 객체는 삭제, 다음 요청시 새로운 세션 객체 생성
		//session.invalidate();
		
		return "redirect:/ch08/content";
	}
}
