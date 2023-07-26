package com.mycompany.springwebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ch11City {
	private int code;			// value 속성 값 - 영문
	private String label;		// leble 값 - 국문
}
