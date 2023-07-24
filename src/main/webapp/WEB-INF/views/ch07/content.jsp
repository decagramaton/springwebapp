<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">Ch07. 모델(데이터) 전달</div>
	<div class="card-body">
		<div>
			<a href="useRequest1" class="btn btn-info btn-sm">request1 이용</a>
			<a href="useRequest2" class="btn btn-info btn-sm">request2 이용</a>
			<a href="useRequest3" class="btn btn-info btn-sm">request3 이용</a>
			<a href="useRequest4?bno=1&btitle=제목1&bcontent=내용1&bwriter=글쓴이1&bdate=2023-07-24" class="btn btn-info btn-sm">request4 이용</a>
			<a href="useRequest5?bno=2&btitle=제목2&bcontent=내용2&bwriter=글쓴이2&bdate=2023-07-24" class="btn btn-info btn-sm">request5 이용</a>
		</div>
		<div>
			<a href="useSeesion1" class="btn btn-info btn-sm">Seesion1 이용</a>
			<a href="useSeesion2" class="btn btn-info btn-sm">Seesion2 이용</a>
			<a href="useSeesion3" class="btn btn-info btn-sm">Seesion3 이용</a>
			<a href="useSeesion4" class="btn btn-info btn-sm">Seesion4 이용</a>
			<a href="useSeesion5" class="btn btn-info btn-sm">Seesion5 이용</a>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>