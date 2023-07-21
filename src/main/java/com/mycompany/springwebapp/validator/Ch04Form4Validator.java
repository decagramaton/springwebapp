package com.mycompany.springwebapp.validator;

import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.springwebapp.dto.joinForm;
import com.mycompany.springwebapp.dto.loginForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04Form4Validator implements Validator {
   @Override
   public boolean supports(Class<?> clazz) {
      boolean check = loginForm.class.isAssignableFrom(clazz);
      return check;
   }

   @Override
   public void validate(Object target, Errors errors) {
	   loginForm loginform = (loginForm) target;
      
      // mid 검사
      String mid = loginform.getMid();
      if(mid == null || mid.equals("")) {
         errors.rejectValue("mid", "errors.form.required", "필수 입력(D)");
      } else if(mid.length() < 8) {
         errors.rejectValue("mid", "errors.form.minlength", new Object[] {8},"최소 8자 입력(D)");
      } else if(mid.length() > 15) {
         errors.rejectValue("mid", "errors.form.maxlength", new Object[] {15},"최대 15자 입력(D)");
      }
      
      // mpassword 검사
      String mpassword = loginform.getMpassword();
      if(mpassword == null || mpassword.equals("")) {
         errors.rejectValue("mpassword", "errors.form.required", "필수 입력(D)");
      } else {
         String regExp = "^[a-zA-Z0-9]{8,15}$";
         boolean result = Pattern.matches(regExp, mpassword);
         if(result == false) {
            errors.rejectValue("mpassword", "errors.form.format", "형식에 맞지 않음(D)");
         }
      }
   }
}