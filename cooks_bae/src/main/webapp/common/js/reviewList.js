var endPageNum;
var loginUser_Id;

// $(document).ready(function() {}) ;
$(function () {
	
	// 로그인하지 않았을 경우에는 글쓰기 버튼을 보여주지 않음.
	if(loginUser_Id == null) $('#addBtn').css('display', 'none');
	else $('#addBtn').css('display', '');
		
	loadReviewList(currentPageNum);
	
	//리스트를 불러올 때 세션을 넘겨 받아서 세션이 NULL 값이면 글쓰기 사용X
	$('#re_Content').click(function() {
		
		if(loginUser_Id == null) {
			alert("로그인 후 이용이 가능합니다.");
			
		} else {
			$('#re_Content').removeAttr('placeholder');
			if ($('#reContentWrap').hasClass('fullSize')) { } 
			else {
				$('#reContentWrap').addClass('fullSize');
				$('#addBtn').css('display', '');
			} // else
		} //else
	});
	
	// 글쓰기 폼에서 포커스가 아웃되면 글쓰기폼이 줄어들게 해주는 코드.
	$('#tweetFrom').focusout(function() {
		if ($('#re_Content').val().length > 0) {

		} else {

			$('#re_Content').attr('placeholder', '여러분의 후기를 남겨주세요~');
			$('#reContentWrap').removeClass('fullSize');
			//$('#addBtn').css('display', 'none');
		}
	});
	
	// 별점 이벤트
	$(".star_rating a" ).click(function() {
		var re_Grade = 0;
	     $(this).parent().children("a").removeClass("on");
	     $(this).addClass("on").prevAll("a").addClass("on");
	     
	     if( $('#reviewStar5').hasClass("on") ) {
	    	 $('#re_Grade').val('5');
	    	 
	     } else if( $('#reviewStar4').hasClass("on") ) {
	    	 $('#re_Grade').val('4');
	    	 
	     } else if( $('#reviewStar3').hasClass("on") ) {
	    	 $('#re_Grade').val('3');
	    	 
	     } else if( $('#reviewStar2').hasClass("on") ) {
	    	 $('#re_Grade').val('2');
	    	 
	     } else if( $('#reviewStar1').hasClass("on") ) {
	    	 $('#re_Grade').val('1');
	     }
	     return false;
	});
	

/*	Ajax로 글쓰기하는 이벤트 (미완성)
	
	$('#writeReviewBtn').click(function () {
		console.log("글쓰기 이벤트 발생");
		$.ajax({
			type: "POST",
			url: "/cooks/login.app",
			async: true,
			dataType: "json",
			data: formdata,
			success : function(data) {
				if(data.status == "success") {
					alert("등록성공");
					loadReviewList(1);
					// 폼 리셋 시켜주는 $('#btnCancel').click(); 이벤트 추가.
				} else 
					alert("등록실패");
			},
			error : function(xhr) {
				console.log("error", xhr);
				alert("error html = " + xhr.statusText);
			} // error
			
		}); // ajax
		
	}); // click(function)
	
*/	

	// 페이지 이전, 다음 버튼 눌렀을 때 
	$('#prevBtn').click(function(event){
		if (currentPageNum > 1) {
			
			loadReviewList(--currentPageNum);
		}
	});

	$('#nextBtn').click(function(event){
		if (currentPageNum < endPageNum) {
			loadReviewList(++currentPageNum);
		}
	});
	
	
}); //document.ready

/*
//페이징처리
function setPageNum(currentPageNum, endPageNum) {
  window.currentPageNum = currentPageNum;
  window.endPageNum = endPageNum;
  
  $('#pageNo').html(currentPageNum);
  
  if (currentPageNum <= 1) $('#prevBtn').css('display', 'none');
  else $('#prevBtn').css('display', '');
  
  if (currentPageNum >= endPageNum) $('#nextBtn').css('display', 'none');
  else $('#nextBtn').css('display', '');
}
*/


function setPageNum(currentPageNum, endPageNum) {
	
	$('.page-no').remove();
	for(var index=1 ; index <= endPageNum ; index++) {
		
		$('<a>').addClass('page-no')
				.attr('href', '#')
				.attr('onClick', 'loadReviewList(' + index + ')')
				.html('&nbsp;&nbsp;'+index+'&nbsp;&nbsp;')
				.appendTo('#pagingBar')
	}
}


// 리뷰리스트
function loadReviewList(pageNum) {
	if(pageNum <= 0) pageNum = currentPageNum;
	
	$.getJSON('/cooks/reviewList.app?pageNum='+pageNum, function(data) {
		
		console.log(data);
		setPageNum(data.currentPageNum, data.endPageNum);
		
		if(data.loginUser_Id != null) {
			loginUser_Id = data.loginUser_Id;
		}
			
		var reviewList = data.reviewList;
		// 기존 리뷰 리스트 삭제
		$('.data-row').remove();
		// 새로 리뷰 리스트를 생성
		for(var i=0 ; i < reviewList.length ; i++) {
			
			if(reviewList[i].saveFileName == null) { reviewList[i].saveFileName = 'NoImage.jpg' }
			console.log("사진 : ", reviewList[i].saveFileName);
			
			
			var reviewContent = [];
			reviewContent = reviewList[i].re_Content.split('<br />')
			
			// 날짜 포맷.
			var formatted_date = new Date(reviewList[i].re_Date);
			
			if(reviewList[i].re_Grade == 5) { reviewList[i].re_Grade = '★★★★★' }
			else if(reviewList[i].re_Grade == 4) { reviewList[i].re_Grade = '★★★★' }
			else if(reviewList[i].re_Grade == 3) { reviewList[i].re_Grade = '★★★' }
			else if(reviewList[i].re_Grade == 2) { reviewList[i].re_Grade = '★★' }
			else reviewList[i].re_Grade = '★'
			
			$('<tr>').addClass('data-row').css('height', '5em')
			.append($('<td>').html(reviewList[i].re_Num).css('vertical-align', 'middle'))
			.append(
					$('<td>').append(
							$('<img>').attr('src', 'fileUpload/'+reviewList[i].saveFileName).css('width', '95%').css('height', '5em')
							)
							.css('vertical-align', 'middle')
						)
			.append(
					$('<td>').append(
							$('<a>').attr('href', '/cooks/contentReview.app?re_Num='+reviewList[i].re_Num+"&pageNum="+pageNum)
									.attr('data-replyNo', reviewList[i].re_Num) //.attr('onClick', 'return false') a태그 url이 # 일 경우
									.html(reviewContent[0]) 
							)
							.css('vertical-align', 'middle')
						)
			.append($('<td>').html(reviewList[i].id).css('vertical-align', 'middle').css('text-align', 'center'))
			.append($('<td>').html(formatted_date.toLocaleString()).css('vertical-align', 'middle').css('text-align', 'center'))
			.append($('<td>').html(reviewList[i].re_Grade).css('vertical-align', 'middle').css('text-align', 'center'))
			.appendTo('#reviewListTbody')
		} // for
		
	});
} // loadReviewList







