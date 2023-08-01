<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">Ch13. 서비스와 DAO(데이터 접근객체)</div>
	<div class="card-body">
		<div class="mt-2">
			<a href="writeBoard" class="btn btn-info btn-sm">게시물 삽입(insert)</a>
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
<div class="card m-2">
	<div class="card-header">Ch13. 회원 서비스</div>
	<div class="card-body">
		<c:if test="${ch13Login == null}">
			<div class="mt-2">
				<a href="join" class="btn btn-info btn-sm">회원 가입 페이지 이동</a>
			</div>
			<div class="mt-2">
				<a href="login" class="btn btn-info btn-sm">로그인 페이지 이동</a>
			</div>
		</c:if>
		<c:if test="${ch13Login != null}">
			<a href="logout" class="btn btn-info btn-sm">로그아웃</a>
			<p>현재 : ${ch13Login.mid}가 로그인되었습니다.</p>
		</c:if>
	</div>
</div>
<div class="card m-2">
	<div class="card-header">게시판</div>
	<div class="card-body">
		<div class="mt-2">
			<a href="getBoardList" class="btn btn-info btn-sm">회원 가입 페이지 이동</a>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>