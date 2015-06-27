<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Dash Board-notice</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="community/css/shop-homepage.css" rel="stylesheet">
<link href='common/css/modalStyle.css' rel='stylesheet'>

<!-- jQuery -->
<script src="js/jquery-1.11.1.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<!-- Custom JS -->
<script src="community/js/community.js"></script>
<script src="common/js/common.js"></script>
<script src="common/js/signUp.js"></script>

</head>

<body>
	<jsp:include page="../common/navTop.jsp" />
	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="row row-offcanvas row-offcanvas-left">

				<div class="col-sm-3 col-md-2 sidebar-offcanvas" id="sidebar"
					role="navigation">

					<ul class="nav nav-sidebar" style="margin-top: 15px;">
						<li><a href="#">자유게시판</a></li>
						<li><a href="#">나만 아는 레시피</a></li>
						<li><a href="#">공지사항</a></li>
					</ul>

				</div>
				<!--/span-->

				<div class="col-sm-9 col-md-10 main">

					<!--toggle sidebar button-->
					<p class="visible-xs">
						<button type="button" class="btn btn-primary btn-xs"
							data-toggle="offcanvas">
							<i class="glyphicon glyphicon-chevron-left"></i>
						</button>
					</p>

					<h2 class="sub-header">Q&A</h2>
					
					<br>

					<div class="panel panel-default">
					<input type="hidden" name="pageNum" value="${pageNum}">
					<input type="hidden" name="q_Dept" value="${qnaVO.q_Dept}">
    				<input type="hidden" name="q_Position" value="${qnaVO.q_Position}">
					<input type="hidden" id="f_Num" value="${qnaVO.f_Num}">
						<div class="panel-heading">
							<h4>${qnaVO.q_Title}</h4>
							<label for="id">글쓴이 : </label> ${qnaVO.id} <br>
						</div>
						<div class="panel-body">${qnaVO.q_Content}</div>
						<div class="panel-footer" style="padding-bottom: 30px;">
							<div id="inlineFooter" style="float: right">
							<c:if test="${qnaVO.id == loginUser.id}">
								<a href="QNAupdateForm.app?q_Num=${qnaVO.q_Num}&pageNum=${pageNum}">게시글 수정</a> .  
								<a href="QNADelete.app?q_Num=${qnaVO.q_Num}&pageNum=${pageNum}">게시글 삭제</a> . 
							</c:if>
							<c:if test="${(loginUser.user_Level == 1 || loginUser.user_Level == 2) && qnaVO.q_Dept == 0}">
								<a href="QNAreplyForm.app?q_Num=${qnaVO.q_Num}&pageNum=${pageNum}">답글달기</a> .
							</c:if>
								<a href="QNAlist.app?pageNum=${pageNum}">목록으로</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--/row-->
		</div>

	</div>
	<!-- row -->


	<!-- /container -->


	<div class="container">

		<hr>

		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; Get Hyped for Food</p>
				</div>
			</div>
		</footer>

	</div>
	<!-- /.container -->

	<jsp:include page="../common/sign_modal.jsp" />

</body>
</html>
