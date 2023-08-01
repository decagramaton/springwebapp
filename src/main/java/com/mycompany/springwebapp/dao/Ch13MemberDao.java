package com.mycompany.springwebapp.dao;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;

import com.mycompany.springwebapp.dto.Ch13Member;

@Mapper
public interface Ch13MemberDao {
	public int insert(Ch13Member member);	// mrole의 기본값은 "ROLE_USER", menabled의 기본값은 true
	public Ch13Member selectByMid(String mid);
	public int update(Ch13Member member);  // 비밀번호(mpassword), 이메일(memail) 변경
}
