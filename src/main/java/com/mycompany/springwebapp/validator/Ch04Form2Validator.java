package com.mycompany.springwebapp.validator;

import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.mycompany.springwebapp.dto.Ch04Form2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04Form2Validator implements Validator {
	@Override
   public boolean supports(Class<?> clazz) {
      boolean check = Ch04Form2.class.isAssignableFrom(clazz);
      return check;
   }

   @Override
   public void validate(Object target, Errors errors) {
      Ch04Form2 ch04Form2 = (Ch04Form2) target;
      
      // 주민번호 유효성 검사
      String ssn = ch04Form2.getSsn();
      
	  if(ssn == null || ssn.equals("")) {
		  errors.rejectValue("ssn", "errors.form.required", "필수 입력(D)");
	  } else {
	      String regExp = "^\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])[-]*[1-4]\\d{6}$";
	      boolean result = Pattern.matches(regExp, ssn);
	      if(result == false) {
	    	  errors.rejectValue("ssn", "errors.form.format", "주민번호 형식이 아님(D)");
	      }
	  }
	  
	  
	  // 년월일 유효성 검사
      String yyyymmdd = ch04Form2.getYearMonthDay();
      
      if(yyyymmdd == null || yyyymmdd.equals("")) {
         errors.rejectValue("yearMonthDay", "errors.form.required", "필수 입력(D)");
      } else {
         String regExp = "[0-9]{8}";
         boolean result = Pattern.matches(regExp, yyyymmdd);
         
         if(result == false) {
            errors.rejectValue("yearMonthDay", "errors.form.format", "년월일 형식에 맞지 않음(D)");
         }
      }
      
      // 비밀번호 유효성 검사
      String password = ch04Form2.getPassword();
      if(password == null || password.equals("")) {
         errors.rejectValue("password", "errors.form.required", "필수 입력(D)");
      } else {
         String regExp = "^[a-zA-Z0-9]{8,15}$";
         boolean result = Pattern.matches(regExp, password);
         if(result == false) {
            errors.rejectValue("password", "errors.form.format", "형식에 맞지 않음(D)");
         } else if(result == true && (password.length() < 8)) {
        	errors.rejectValue("password", "errors.form.minlength", new Object[] {8},"최소 8자 입력(D)");
         } else if(result == true && (password.length() > 15)) {
        	errors.rejectValue("password", "errors.form.maxlength", new Object[] {15},"최대 15자 입력(D)");
         }
      }
   }
}