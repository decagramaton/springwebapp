package com.mycompany.springwebapp.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.mycompany.springwebapp.dao.Ch12DaoByXml1;
import com.mycompany.springwebapp.dao.Ch12DaoByXml2;
import com.mycompany.springwebapp.dao.Ch12DaoByXml3;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Ch12ServiceDiByXml {
	
	private Ch12DaoByXml1 daoXml1;
	private Ch12DaoByXml2 daoXml2;
	private Ch12DaoByXml3 daoXml3;
	
	// 생성자
	public Ch12ServiceDiByXml() {}
	public Ch12ServiceDiByXml(Ch12DaoByXml1 daoXml1) {
		this.daoXml1 = daoXml1;
	}
	
	// Setter
	public void setCh12DaoByXml1(Ch12DaoByXml1 daoXml1) {
		this.daoXml1 = daoXml1;
	}
	public void setCh12DaoByXml2(Ch12DaoByXml2 daoXml2) {
		this.daoXml2 = daoXml2;
	}
	public void setCh12DaoByXml3(Ch12DaoByXml3 daoXml3) {
		this.daoXml3 = daoXml3;
	}
	
	// Collection
	public void setCollection1(List<String> list) {
		for(String item : list) {
			log.info(item);
		}
	}
	
	public void setCollection2(Set set) {
		log.info("셋 개수:" + set.size());
	}
	
	public void setCollection3(Map<String, Object> map) {
		log.info("맵 개수:" + map.size());
	}
	
	public void setCollection4(Properties prop) {
		log.info("프로퍼티 개수:" + prop.size());
	}
	
	// Instance Method
	public void method() {
		daoXml1.method();
		daoXml2.method();
		daoXml3.method();
	}
}
