<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		게시물 내용
	</div>
	<div class="card-body">
		<div>
			<div>
				<div>
					<p>
						<span>번호:</span> 
						<span>${board.bno}</span>
					</p>
					
					<p>
						<span>제목:</span> 
						<span>${board.btitle}</span>
					</p>
					
					<p>
						<span>글쓴이:</span> 
						<span>${board.mid}</span>
					<p>
					
					<p>
						<span>날짜:</span> 
						<span><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd HH.mm.ss"/></span> <br/>
					</p>
					
					<c:if test="${board.battachoname !=null}">
						<p>
							<span>첨부:</span> 
							<span>${board.battachoname}
								<%-- 첨부파일이 Server File System에 저장되어 있는 경우 = battachsname 가 있는 경우--%>
								<c:if test="${board.battachsname != null}">
									<a href="filedownload1?bno=${board.bno}" class="btn btn-info btn-sm ml-2">다운로드1</a>
									<img src="filedownload1?bno=${board.bno}" height="100"/>
								</c:if>
								<%-- 첨부파일이 DB에 저장되어 있는 경우 = battachdata 가 있는 경우 --%>
								<c:if test="${board.battachdata != null}">
									<a href="filedownload2?bno=${board.bno}" class="btn btn-info btn-sm ml-2">다운로드2</a>
									
									
									<%-- src의 속성 값은 완전한 응답 HTTP가 되어야한다.(요청경로+헤더명+본문) --%>
									<%-- 서버의 정적 리소스를 요청해서 응답받는 경우. EX) photo1.jpg --%>
									<%-- 요청 경로로 Controller에서 생성한 응답은 받는 경우. 
									EX) 'filedownload2?bno=${board.bno}', 'data:MIME;base64, base64fh 인코딩된 데이터'--%>
									<img src="data:${board.battachtype};base64, ${base64Img}" height="100"/>
								</c:if>
							</span>
						</p>
					</c:if>
				</div>
				
				<div>
					<span class="title">내용:</span> <br/>
					<textarea style="width:100%" readonly>${board.bcontent}</textarea>
				</div>
				
				<a class="btn btn-info btn-sm mt-2" href="getBoardList">목록</a>
				<c:if test="${ch13Login.mid == board.mid}">
					<a class="btn btn-info btn-sm mt-2" href="updateBoard?bno=${board.bno}">수정</a>
					<a class="btn btn-info btn-sm mt-2" href="deleteBoard?bno=${board.bno}">삭제</a>
				</c:if>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>