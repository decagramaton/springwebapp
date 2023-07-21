package com.mycompany.springwebapp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Ch04Form2 {
	private String ssn;
	private String yearMonthDay;
	private String password;
	private boolean power;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date calendar;
}
