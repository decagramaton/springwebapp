package com.mycompany.springwebapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springwebapp.dto.Ch11City;
import com.mycompany.springwebapp.dto.Ch11Member;
import com.mycompany.springwebapp.dto.Ch11Skill;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch11")
public class Ch11Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch11/content";
	}
	
	// form에 기본값을 세팅할 때 주로 사용
	@GetMapping("/form1")
	public String getForm(@ModelAttribute("member") Ch11Member member) {
		member.setMid("summber");
		member.setMname("홍길동");
		member.setMpassword("12345");
		member.setMnation("한국");
		return "ch11/form1";
	}
	
	// form에서 받아온 데이터를 처리할 때 주로 사용
	@PostMapping("/form1")
	public String handleForm1(@ModelAttribute("member") Ch11Member member) {
		log.info(member.toString());
		return "redirect:/ch11/content";
	}
	
	
	@GetMapping("/form2")
	public String getForm2(@ModelAttribute("member") Ch11Member member, Model model) {
		
		// 드롭다운리스트(<select>)의 항목을 추가할 항목
		List<String> typeList = new ArrayList<>();
		typeList.add("일반회원");
		typeList.add("기업회원");
		typeList.add("헤드헌트회원");
		
		// Request 객체에 저장 후, jsp에 전달
		model.addAttribute("typeList", typeList);
		
		// Select태그의 기본값 지정
		// member가 값을 가지고 있기 때문에, Select태그의 기본값이 지정된다. selected="selected"
		member.setMtype("기업회원");
		
		
		// ------------------------------------
		
		List<String> jobList = new ArrayList<>();
		jobList.add("학생");
		jobList.add("개발자");
		jobList.add("디자이너");
		model.addAttribute("jobList", jobList);
		
		// ------------------------------------
		
		List<Ch11City> cityList = new ArrayList<>();
		cityList.add(new Ch11City(1, "서울"));
		cityList.add(new Ch11City(2, "부산"));
		cityList.add(new Ch11City(3, "제주"));
		member.setMcity(3);
		model.addAttribute("cityList", cityList);
		
		return "ch11/form2";
	}

	@PostMapping("/form2")
	public String postForm2(@ModelAttribute("member") Ch11Member member) {
		log.info(member.toString());
		return "redirect:/ch11/content";
	}

	
	
	@GetMapping("/form3")
	public String getForm3(@ModelAttribute("member") Ch11Member member, Model model) {
		
		List<String> languageList = new ArrayList<>();
		languageList.add("C");
		languageList.add("Python");
		languageList.add("Java");
		languageList.add("Javascript");
		languageList.add("HTML");
		
		model.addAttribute("languageList", languageList);
		member.setMlanguage(new String[] {"Java", "HTML"});
		
		
		// ------------------------------------
		
		List<Ch11Skill> skillList = new ArrayList<>();
		skillList.add(new Ch11Skill(1, "SpringFramework"));
		skillList.add(new Ch11Skill(2, "Bootstrap"));
		skillList.add(new Ch11Skill(3, "MyBatis"));
		model.addAttribute("skillList", skillList);
		member.setMskill(new int[] {1,2});

		return "ch11/form3";
	}
	
	@PostMapping("/form3")
	public String postForm3(@ModelAttribute("member") Ch11Member member) {
		log.info(member.toString());
		return "redirect:/ch11/content";
	}
	
	
	
	@GetMapping("/form4")
	public String getForm4(@ModelAttribute("member") Ch11Member member, Model model) {
		
		List<String> jobList = new ArrayList<>();
		jobList.add("학생");
		jobList.add("교수");
		jobList.add("임직원");
		jobList.add("사서");
		jobList.add("학군단");
		
		model.addAttribute("jobList", jobList);
		member.setMjob("사서");
		
		// ------------------------------------
		
		List<Ch11City> cityList = new ArrayList<>();
		cityList.add(new Ch11City(1, "서울"));
		cityList.add(new Ch11City(2, "부산"));
		cityList.add(new Ch11City(3, "제주"));
		model.addAttribute("cityList", cityList);
		member.setMcity(2);

		return "ch11/form4";
	}
	
	@PostMapping("/form4")
	public String postForm4(@ModelAttribute("member") Ch11Member member) {
		log.info(member.toString());
		return "redirect:/ch11/content";
	}
	
	@GetMapping("/form5")
	public String getForm5() {

		return "ch11/form5";
	}
}
