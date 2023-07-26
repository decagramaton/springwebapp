package com.mycompany.springwebapp.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12ServiceCreateByXml {
	public Ch12ServiceCreateByXml() {
		log.info("생성됨");
	}
	
	public static Ch12ServiceCreateByXml getInstance() {
		log.info("run");
		return new Ch12ServiceCreateByXml();
	}
	
	public Ch12ServiceCreateByXml getSelfObject() {
		log.info("run");
		return new Ch12ServiceCreateByXml();
	}
}
