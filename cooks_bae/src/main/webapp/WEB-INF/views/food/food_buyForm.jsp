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

<title>주문하기</title>

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
<script src="food/js/food_add.js"></script>
<script src="common/js/signUp.js"></script>


<style>
.#ex_date {
	display: inline-block;
}
</style>

</head>


<body>
	<jsp:include page="../common/navTop.jsp" />

	<!-- Page Content -->
	<div class="container">
		<div class="row">

			<h3>
				<p>&nbsp&nbsp&nbsp 주문하기</p>
			</h3>

			<br>

			<div class="well bs-component">

				<form class="form-horizontal" id="detailForm" name="detailForm" action="addFood.app" method="post" enctype="multipart/form-data">
				
				<input type="hidden" id="f_recipe" name="f_recipe" value="">
				<input type="hidden" id="f_ingredients" name="f_ingredients" value="">
				<input type="hidden" id="f_origin" name="f_origin" value="">
				<input type="hidden" id="r_num" name="r_num" value="${r_num}">

				<h4>01. 주문자 정보</h4>
				<hr>
					<div class="form-group">
						<label class="col-sm-2 control-label">주문하시는분</label>
						<div class="col-sm-4">
							<h5>${userVO.id}</h5>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">주소</label>
						<div class="col-sm-4">
							<h5>${userVO.address}</h5>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">연락처</label>
						<div class="col-sm-4">
							<h5>${userVO.phone}</h5>
						</div>
					</div>
				<hr>
				
				<h4>02. 배송 정보</h4>
				<hr>
					<div class="form-group">
						<label class="col-sm-2 control-label">받으실분</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="f_name" name="f_name">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">받으실곳</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="f_price" name="f_price">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">연락처</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="f_price" name="f_price">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">남기실 말씀</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="f_price" name="f_price">
						</div>
					</div>
				<hr>
				
				<h4>03. 결제 정보</h4>
				<hr>
					<div class="form-group">
						<label for="foodName" class="col-sm-2 control-label">음식 이름</label>
						<div class="col-sm-4">
							<h5>${f_name}</h5>
						</div>
					</div>

					<div class="form-group">
						<label for="price" class="col-sm-2 control-label">가격</label>
						<div class="col-sm-4">
							<h5>${f_price}</h5>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">수량</label>
						<div class="col-sm-4">
							<h5>${count}</h5>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">합계</label>
						<div class="col-sm-4">
							<h5 id="totalPrice">${f_price * count}</h5>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">적립금 적용</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="mileage" name="mileage" onforminput="totalPrice()" placeholder="보유하고계신 마일리지는${userVO.mileage}입니다">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">총 결제금액</label>
						<div class="col-sm-4">
							<input type="text" id="resultPrice">
						</div>
					</div>
				<hr>
				


				<br>
				<br>

				
					


					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="button" onclick="totalDetail()" class="btn btn-danger" value="구매하기">
						</div>
					</div>
					
				</form>
			</div>

		</div>
	</div>

	<!-- Footer -->
	<div class="container">
		<hr>
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; Get Hyped for Food</p>
				</div>
			</div>
		</footer>
	</div>
	<!-- /.container -->

</body>

</html>
