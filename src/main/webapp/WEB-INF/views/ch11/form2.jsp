<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		DTO 객체의 필드값을 select 태그로 세팅
	</div>
	<div class="card-body">
		<%-- <form modelAttribute="member">
		  <div class="form-group">
		    <label for="mtype">Type</label>
		    <select class="form-control" id="mtype" name="mtype">
		      <c:forEach var="type" items="${typeList}">
		      	<option value="${type}"
		      			<c:if test="${member.mtype == type}">selected</c:if>		
		      	>${type}</option>
		      </c:forEach>
		    </select>
		  </div>
		  
		  <div class="form-group">
		    <label for="mjob">Job</label>
		    <select class="form-control" id="mjob" name="mjob">
		      <option value="">---선택하세요---</option>
		      <c:forEach var="job" items="${jobList}">
		      	<option value="${job}"
		      			<c:if test="${member.mjob == job}">selected</c:if>		
		      	>${job}</option>
		      </c:forEach>
		    </select>
		  </div>
		  
		  <div class="form-group">
		    <label for="mcity">City</label>
		    <select class="form-control" id="mcity" name="mcity">
		      <c:forEach var="city" items="${cityList}">
		      	<option value="${city.code}"
		      			<c:if test="${member.mcity == city.code}">selected</c:if>		
		      	>${city.label}</option>
		      </c:forEach>
		    </select>
		  </div>
		  <button type="submit" class="btn btn-primary btn-sm">제출</button>
		</form> --%>
		
		<form:form method="post" action="form2" modelAttribute="member">
		
		
		  <!-- form:select의 items 속성을 활용하여 객체리스트의 값으로 option이 자동으로 생성된다. -->
		  <!-- 코드 절약에 매우 좋다. -->
		  <div class="form-group">
		    <label for="mtype">Type</label>
		    <form:select path="mtype" items="${typeList}" class="form-control"/>
		  </div>
		  
		  
		  <!-- 별도의 option태그를 생성하고 싶을 때, form:options를 사용하여 생성한다. -->
		  <div class="form-group">
		    <label for="mjob">Job</label>
		    <form:select path="mjob" class="form-control">
		    	<option value="">---선택하세요---</option>
		    	<form:options items="${jobList}"/>
		    </form:select>
		  </div>
		  
		  <!-- value 속성 값과 레이블(출력값)이 다르게 설정하는 실습 -->
		  <!-- itemValue="code" itemLabel="label"는 items="${cityList}" DTO객체의 필드 이름 -->
		  <div class="form-group">
		    <label for="mcity">City</label>
		    <form:select path="mcity" items="${cityList}" 
		                 itemValue="code" itemLabel="label" 
		                 class="form-control"/>
		  </div>
		  
		  <button type="submit" class="btn btn-primary btn-sm">제출</button>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>