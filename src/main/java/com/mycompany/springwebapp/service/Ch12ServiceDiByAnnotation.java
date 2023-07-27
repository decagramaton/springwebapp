package com.mycompany.springwebapp.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springwebapp.dao.Ch12DaoByAnotation1;
import com.mycompany.springwebapp.dao.Ch12DaoByAnotation2;
import com.mycompany.springwebapp.dao.Ch12DaoByAnotation3;
import com.mycompany.springwebapp.dao.Ch12DaoI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Ch12ServiceDiByAnnotation {
	
	@Resource	//@Resource
	private Ch12DaoByAnotation1 daoAnotation1;
	private Ch12DaoByAnotation2 daoAnotation2;
	private Ch12DaoByAnotation3 daoAnotation3;
	
	@Resource(name="ch12DaoImpl1")
	private Ch12DaoI ch12Dao;
	
	// Setter
	public void setCh12DaoByXml1(Ch12DaoByAnotation1 daoAnotation1) {
		this.daoAnotation1 = daoAnotation1;
	}
	@Resource
	public void setCh12DaoByXml2(Ch12DaoByAnotation2 daoAnotation2) {
		this.daoAnotation2 = daoAnotation2;
	}
	@Resource
	public void setCh12DaoByXml3(Ch12DaoByAnotation3 daoAnotation3) {
		this.daoAnotation3 = daoAnotation3;
	}
	
	public void method() {
		daoAnotation1.method();
		daoAnotation2.method();
		daoAnotation3.method();
		ch12Dao.method();
	}
}
