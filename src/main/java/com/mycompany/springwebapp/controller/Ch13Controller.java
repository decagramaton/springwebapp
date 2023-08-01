package com.mycompany.springwebapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springwebapp.dao.Ch13BoardDao;
import com.mycompany.springwebapp.dao.Ch13BoardDaoOldmpl;
import com.mycompany.springwebapp.dao.Ch13MemberDao;
import com.mycompany.springwebapp.dto.Ch13Board;
import com.mycompany.springwebapp.dto.Ch13Member;
import com.mycompany.springwebapp.dto.Ch13Pager;
import com.mycompany.springwebapp.dto.joinForm;
import com.mycompany.springwebapp.interceptor.Login;
import com.mycompany.springwebapp.service.Ch13BoardService;
import com.mycompany.springwebapp.service.Ch13MemberService;
import com.mycompany.springwebapp.service.Ch13MemberService.JoinResult;
import com.mycompany.springwebapp.service.Ch13MemberService.LoginResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ch13")
public class Ch13Controller {
	@Autowired
	private Ch13BoardService boardService;
	
	@Autowired
	private Ch13MemberService memberService;
	
	// DisPAtcher에도 property.xml 설정파일이 있어야한다.
	// Context:property-placeholder ../>WebApplicationContext 단위로 사용하므로.
	@Value("${file.upload.dir}")
	private String fileUploadDir;

	@RequestMapping("/content")
	public String content() {
		return "ch13/content";
	}
	
	@GetMapping("/join")
	public String getjoinForm() {
		return "ch13/joinForm";
	}
	
	@PostMapping("/join")
	public String postjoinForm(Ch13Member member, Model model) {
		JoinResult result = memberService.join(member);
		
		if(result == JoinResult.FAIL_DUPLICATED_MID) {
			String error = "중복된 ID 존재";
			model.addAttribute("error", error);
			return "ch13/joinForm";
		} else {
			memberService.join(member);
			return "redirect:/ch13/content";
		}
	}
	
	@GetMapping("/login")
	public String getLoginForm() {
		
		return "ch13/loginForm";
	}
	
	@PostMapping("/login")
	public String postLogin(Ch13Member member , Model model, HttpSession session) {
		LoginResult result = memberService.login(member);
		String error = "";
		
		if(result == LoginResult.FAIL_MID) {
			error = "MID가 없다";
		} else if (result == LoginResult.FAIL_ENABLED) {
			error = "MID가 비활성화 상태다";
		} else if (result == LoginResult.FAIL_MPASSWORD) {
			error = "Password가 틀리다";
		} else {
			Ch13Member dbMember = memberService.getMember(member.getMid());
			session.setAttribute("ch13Login", dbMember);
			
			return "redirect:/ch13/content";
		}
		
		model.addAttribute("error",error);
		
		return "ch13/loginForm";
	}
	
	@GetMapping("/logout")
	public String getLoginForm(HttpSession session) {
		session.removeAttribute("ch13Login");
		
		return "redirect:/ch13/content";
	}
	
	
	@Login
	@GetMapping("/writeBoard")
	public String writeBoardForm() {
		
		return "ch13/writeBoardForm";
	}
	
	@Login
	@PostMapping("/writeBoard")
	public String writeBoard(Ch13Board board, HttpSession session) throws Exception, IOException {
		Ch13Member member = (Ch13Member) session.getAttribute("ch13Login");
		board.setMid(member.getMid());
		
		MultipartFile mFile = board.getBattach();
		if(!mFile.isEmpty()) {
			log.info("첨부파일 없음");
			
			// 1. 브라우저에서 선택한 파일이름으로 설정
			board.setBattachoname(mFile.getOriginalFilename());
			// 2. 파일 형식(MIME 타입)을 설정(image/jpeg, image/png, ... )
			board.setBattachtype(mFile.getContentType());
			
			// 3-1. 첨부파일을 서버 파일 시스템에 저장(방법1)
			String saveFileName = new Date().getTime() + "-" + mFile.getOriginalFilename();
			board.setBattachsname(saveFileName);
			
			File file = new File(fileUploadDir + "/" + saveFileName);
			mFile.transferTo(file);
			
			// 3-2. 첨부파일을 DB에 직접 저장(방법2)
			//board.setBattachdata(mFile.getBytes());
		}
		
		boardService.write(board);
		return "redirect:/ch13/getBoardList";
	}
	
	
	@GetMapping("/getPageList")
	public String getPageList() {
		//List<Ch13Board> boardList = boardDaoOld.selectAll();
		int bno = 1;
		Ch13Board board = boardService.getBoard(bno);
		
		log.info(board.toString());
		
		return "redirect:/ch13/content";
	}
	
	
	@GetMapping("/getBoardList")
	public String getBoardList(@RequestParam(defaultValue="1") int pageNo, Model model) {
		
		int totalBoardNum = boardService.getTotalBoardNum();
		
		Ch13Pager pager = new Ch13Pager(10, 5, totalBoardNum, pageNo);
		List<Ch13Board> boardList = boardService.getList(pager);
		
		model.addAttribute("pager", pager);
		model.addAttribute("boards", boardList);
		
		
		return "ch13/boardList";
	}
	
	
	@GetMapping("/detailBoard")
	public String detailBoard(int bno, Model model) {
		Ch13Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		
		if(board.getBattachdata() != null) {
			// 0,1 기반 Binary 데이터를 base64 (64bit) 문자열로 변환
			String base64Img = Base64.getEncoder().encodeToString(board.getBattachdata());
			model.addAttribute("base64Img", base64Img);
		}
		
		return "ch13/detailBoard";
	}
	
	@GetMapping("/filedownload1")
	public void filedownload1(int bno, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Ch13Board board = boardService.getBoard(bno);
		
		String fileOriginalName = board.getBattachoname();
		String fileSaveName = board.getBattachsname();
		String filePath = fileUploadDir + "/" + fileSaveName;
		
		String mimeType = board.getBattachtype();
		response.setContentType(mimeType);
		
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			fileOriginalName = URLEncoder.encode(fileOriginalName, "UTF-8");
		} else {
			fileOriginalName = new String(fileOriginalName.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""+ fileOriginalName +"\"");
		
		OutputStream os = response.getOutputStream();
		Path path = Paths.get(filePath);
		Files.copy(path, os);
		os.flush();
		os.close();
	}
	
	
	@GetMapping("/filedownload2")
	public void filedownload2(int bno, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Ch13Board board = boardService.getBoard(bno);
		
		String fileOriginalName = board.getBattachoname();
		
		String mimeType = board.getBattachtype();
		response.setContentType(mimeType);
		
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			fileOriginalName = URLEncoder.encode(fileOriginalName, "UTF-8");
		} else {
			fileOriginalName = new String(fileOriginalName.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""+ fileOriginalName +"\"");
		
		OutputStream os = response.getOutputStream();
		os.write(board.getBattachdata());
		os.flush();
		os.close();
	}
	
	
	
	@GetMapping("/updateBoard")
	public String updateBoard(int bno) {
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
