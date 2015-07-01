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

<title>Dash Board-Recommend</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="community/css/shop-homepage.css" rel="stylesheet">
<link href='common/css/modalStyle.css' rel='stylesheet'>

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Custom JS -->
<script src="common/js/common.js"></script>
<script src="common/js/signUp.js"></script>
<!-- <script src="community/js/community.js"></script> -->	<!-- 글쓰기 모달 -->

</head>


<body>
<jsp:include page="../common/navTop.jsp"/>

	<!-- Page Content -->
	<div class="container">

		<div class="row">

			<div class="row row-offcanvas row-offcanvas-left">

				<div class="col-sm-3 col-md-2 sidebar-offcanvas" id="sidebar"
					role="navigation">

					<ul class="nav nav-sidebar" style="margin-top: 15px;">
						<li><a href="/cooks/NoticeList.app">공지사항</a></li>
						<li><a href="/cooks/freeList.app">자유게시판</a></li>
						<li class="active"><a href="/cooks/recipeList.app">나만 아는 레시피</a></li>
						<li><a href="/cooks/reviewListView.app">음식후기</a></li>
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

					<h2 class="sub-header">나만 아는 레시피</h2>

					<c:if test="${count == 0 }">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<td>게시판에 저장된 글이 없습니다</td>
									</tr>
								</thead>
							</table>
						</div>
					</c:if>


					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<td>번호</td>
									<td>제목</td>
									<td>작성자</td>
									<td>작성일</td>
									<td>조회수</td>
									<td>좋아요</td>
								</tr>
							</thead>
							
							<tbody>
							
							<c:forEach var="recipe" items="${recipeList}">
								<tr>
									<td>
										<c:out value="${recipe.recipe_num}"></c:out>
									</td>
									<td>
										<a href="recipeContent.app?recipe_num=${recipe.recipe_num}&pageNum=${pageNum}">${recipe.recipe_title}</a>
									</td>
									<td>
										<c:out value="${recipe.name}"></c:out>
									</td>
									<td>
										<c:out value="${recipe.recipe_date}"></c:out>
									</td>
									<td>
										<c:out value="${recipe.recipe_hit}"></c:out>
									</td>
									<td>
										<c:out value="${recipe.recipe_like}"></c:out>
									</td>									
								</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<!-- Button trigger modal -->
						<!--  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#writeForm" data-backdrop="static">글쓰기</button> -->
					 	<form action="recipeWriteForm.app" method="post">
							<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}">
							<input type="hidden" id="userId" value="${loginUser.id}">
							<c:if test="${loginUser.id!=null}">
								<input type="submit" value="글쓰기"/>
							</c:if> 
						</form>
					</div>

				</div> <!--/row-->
			</div>

		</div> <!-- row -->
	</div> <!-- /container -->


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


  	<div id="writeForm_Modal"></div>  
  <jsp:include page="../common/sign_modal.jsp"/>

</body>

</html>
