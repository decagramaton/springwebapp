<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">사용자 정의 예외 처리 페이지 - 사용자 정의 Exception</div>
	<div class="card-body">
		<p> 사유 : ${message} 이유로 주문 불가</p>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>