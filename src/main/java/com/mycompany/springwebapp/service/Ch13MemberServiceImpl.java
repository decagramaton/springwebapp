package com.mycompany.springwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springwebapp.dao.Ch13MemberDao;
import com.mycompany.springwebapp.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Ch13MemberServiceImpl implements Ch13MemberService {
	@Autowired
	private Ch13MemberDao memberDao;
	
	
	@Override
	public Ch13Member getMember(String mid) {
		Ch13Member member = memberDao.selectMyMid(mid);
		return member;
	}
	
	
	@Override
	public void join(Ch13Member member) {
		memberDao.insert(member);
	}
	
	@Override
	public LoginResult login(Ch13Member member) {
		//Step1. 로그인ID를 매개변수로 DB에서 해당하는 Member 정보 얻기
		Ch13Member dbMember = memberDao.selectMyMid(member.getMid());
		
		//Step2. Member 정보가 없으면 미가입 상태
		if(dbMember == null) {
			return Ch13MemberService.LoginResult.FAIL_MID;
		}
		
		//Step3. DB의 PW와 로그인 PW가 같은지 확인
		// 활성
		if(dbMember.getMpassword().equals(member.getMpassword())) {
			if(dbMember.isMenabled()) {
				return LoginResult.SUCCESS;
			} else {
				return LoginResult.FAIL_ENABLED;
			}
		} else {
			return LoginResult.FAIL_MPASSWORD;
		}
		
		
		
	}
	
	@Override
	public void logout(String mid) {
		// 만약, DB에 로그인 정보가 저장되어 있다면, 삭제하는 코드를 작성한다.
		// 로그아웃은 로그인 세션을 제거하면 된다.
		// 세션, 리퀘스트는 컨트롤러에서 처리해야한다.
	}
	
	@Override
	public void modify(Ch13Member member) {
		memberDao.update(member);
		
	}
	
	@Override
	public void unjoin(String mid) {
		Ch13Member member = memberDao.selectMyMid(mid);
		member.setMenabled(false);
		memberDao.update(member);
	}
}
