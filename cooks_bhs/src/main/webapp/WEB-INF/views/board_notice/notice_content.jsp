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
<script>
    $(document).ready(function(){
    	addHit();
    });
    
    function addHit() {
		$.ajax({
			type:"POST",
			url:"noticeHit.app",
			async:true,
			dataType:"json",
			data : {
				no_Num : $('#no_Num').val(),
			},
			success : {}
		});
	}
</script>

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

					<h2 class="sub-header">공지사항</h2>

					<div>
						<form>

							<input type="hidden" name="pageNum" value="${pageNum}"> <input
								type="hidden" id="no_Num" value="${noticeVO.no_Num}">

							<table border="2" width="800" align="center">
								<tr>
									<td>글쓴이 :</td>
									<td>${noticeVO.id}</td>
									<td>조회수 :</td>
									<td>${noticeVO.no_Hit}</td>
								</tr>
								<tr>
									<td>제목 :</td>
									<td>${noticeVO.no_Title}</td>
									<td>날짜 :</td>
									<td>${noticeVO.no_Date}</td>
								</tr>
								<tr>
									<td colspan="4"><xmp>${noticeVO.no_Content}</xmp></td>
								</tr>

								<tr>
									<td colspan="4" align="right"><c:if
											test="${userLevel == 2}">
											<input type="button" value="수정하기" onclick="document.location.href='/cooks/NoticeupdateForm.app?no_Num=${noticeVO.no_Num}&pageNum=${pageNum}'">
											<input type="button" value="삭제하기" onclick="document.location.href='/cooks/NoticeDelete.app?no_Num=${noticeVO.no_Num}&pageNum=${pageNum}'">
										</c:if> <input type="button" value="목록으로"
										onclick="document.location.href='/cooks/NoticeList.app?pageNum=${pageNum}'">
									</td>
								</tr>
							</table>
						</form>

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
