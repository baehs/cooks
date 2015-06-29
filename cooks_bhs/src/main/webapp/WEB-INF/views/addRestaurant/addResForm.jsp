<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Cook's</title>

    <!-- Custom Fonts -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<!-- Bootstrap Core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom CSS -->
	<link href="css/main.css" rel="stylesheet">
	<link href="community/css/shop-homepage.css" rel="stylesheet">
	<link href='common/css/modalStyle.css' rel='stylesheet'>
	<link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	
	<!-- Custom JS -->
	<script src="common/js/common.js"></script>
	<script src="common/js/signUp.js"></script>
   
    
</head>


<body>

	<jsp:include page="../common/navTop.jsp"/>
	
	<!-- Page Content -->
	<div class="container">
	<div class="row">

		<h3>맛집 등록하기</h3>

		<br>
		
		<div class="well bs-component">
		
		<form class="form-horizontal">
			<div class="form-group">
				<label for="resName" class="col-sm-2 control-label">상호명</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="resName">
				</div>
			</div>
			
			<div class="form-group">
				<label for="resNumber" class="col-sm-2 control-label">사업자등록번호</label>
				<div class="col-sm-4">		
					<input type="text" class="form-control" id="resNumber">
				</div>
				<button type="submit" class="btn btn-default">중복확인</button>
							
			</div>
									
			<div class="form-group">
				<label for="address" class="col-sm-2 control-label">주소</label>
				<div class="col-sm-4">					
					<input type="text" class="form-control" id="address">						
				</div>
			</div>
			
			<div class="form-group">
			<label for="phone" class="col-sm-2 control-label">연락처</label>
			<div class="form-inline col-sm-4">
					<input type="text" class="form-control" size="2" id="phone" value="010"> - 
					<input type="text" class="form-control" size="3" id="phone"> - 
					<input type="text" class="form-control" size="3" id="phone">
				</div>
				
			</div>
			
			
			<div class="form-group">
				<label for="foodType" class="col-sm-2 control-label">음식유형</label>
				<div class="col-sm-4">
					<label class="checkbox-inline">
					  <input type="checkbox" id="foodType" value="option1"> 한식
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="foodType" value="option2"> 중식
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="foodType" value="option3"> 일식
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="foodType" value="option4"> 양식
					</label>
					<label class="checkbox-inline">
					  <input type="checkbox" id="foodType" value="option5"> 분식
					</label>
				</div>
			</div>			
			
			
			<div class="form-group">
				<label for="foodPic" class="col-sm-2 control-label">맛집사진</label>
				<div class="col-sm-4">
					<input type="file" id="inputFile">		
				</div>
			</div>
			
			
			<br>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-danger">맛집 등록하기</button>
				</div>
			</div>
		</form>
		</div>

		</div>
	</div>

<!-- ................ Footer container ............ -->
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
    
<jsp:include page="../common/sign_modal.jsp"/>
	
</body>
</html>