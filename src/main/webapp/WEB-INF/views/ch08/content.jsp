<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">Ch08. Session 이동</div>
	<div class="card-body">
		<form method="post" action="addCart">
			<div class="form-group">
		    	<label for="name">상품선택:</label>
		    	<select class="form-control" id="name"name="name">
		    		<option value="item1" selected="selected">아이템1</option>
					<option value="item2">아이템2</option>
					<option value="item3">아이템3</option>
					<option value="item4">아이템4</option>
					<option value="item5">아이템5</option>
		    	</select>
		  	</div>
		  	<div class="form-group">
		    	<label for="amount">수량:</label>
		    	<input type="number" id="amount" name="amount" value="1"/>
		  	</div>
		  	<button type="submit" class="btn btn-info btn-sm m-2">장바구니 넣기</button>
		</form>
		
		<a href="clearCart" class="btn btn-info btn-sm m-2">장바구니 비우기</a>
		
		<hr>
		<p>장바구니 내용</p>
		<ul>
			<c:forEach var="item" items="${cart}">
				<li>${item.name}, ${item.amount}개</li>
			</c:forEach>
		</ul>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>