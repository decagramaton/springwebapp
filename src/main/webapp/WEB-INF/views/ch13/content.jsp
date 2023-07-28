<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">Ch13. 서비스와 DAO(데이터 접근객체)</div>
	<div class="card-body">
		<div class="mt-2">
			<a href="insertBoard" class="btn btn-info btn-sm">게시물 삽입(insert)</a>
		</div>
		<div class="mt-2">
			<a href="getBoardList" class="btn btn-info btn-sm">게시물 목록 가져오기(select)</a>
		</div>
		<div class="mt-2">
			<a href="updateBoard" class="btn btn-info btn-sm">게시물 변경(update)</a>
		</div>
		<div class="mt-2">
			<a href="deleteBoard?bno=2" class="btn btn-info btn-sm">게시물 삭제(delete)</a>
		</div>
		<div class="mt-2">
			<a href="getPageList" class="btn btn-info btn-sm">1페이지 가져오기(Pager)</a>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>