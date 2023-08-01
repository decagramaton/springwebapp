package com.mycompany.springwebapp.dto;

import java.sql.Blob;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Ch13Board {
	private int bno;
	private String btitle;
	private String bcontent;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bdate;
	private String mid;
	private int bhitcount;
	
	// 클라이언트가 컨트롤러로 보내는 데이터를 받기 위한 필드
	private MultipartFile battach;
	
	// 컨트롤러 -> 서비스 -> DAO -> DB으로 전달하기 위한 필드
	private String battachoname;
	private String battachsname;
	
	// 방법1. 서버 파일 시스템에 파일로 저장
	private String battachtype;
	
	// 방법2. DB에서 BLOB으로 저장
	// (JAVA)byte[] <--(MyBaits)-> BLOB(DB)
	// MyBatis에서  자동으로 형변환 해준다.
	private byte[] battachdata;
	
	
	// FILE 크기가 작은경우, DB로 관리하는것이 좋다.
	// FIEL 크기가 매우 큰 경우, Server HDD에 저장하는게 좋다.
	// DB에는 File name만 저장하고, Server에 저장된 파일을 찾아서 사용한다.
	
}
