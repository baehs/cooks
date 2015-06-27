$(function () {
	
	$('#qnaUpdateBnt').click(function() {
		
		console.log("ㅇㅇㅇㅇ", $("#q_Title").val())
		if($("#q_Title").val() == "" || $("#q_Content").val() == "") {
			alert("제목 또는 내용을 입력하세요.");
		} else {
			
			$.ajax({
				type: "POST",
				url: "/cooks/QNAUpdate.app",
				async: true,
				dataType: "json",
				data: {
					q_Title:$("#q_Title").val(),
					q_Content:$("#q_Content").val(),
					pageNum: $("#pageNum").val()
				},
				
				success : function(data) {
					if(data.status == 'success') {
						location.href = "/cooks/QNAlist.app"
					} 
			
				},
				error : function(xhr) {
					console.log("error", xhr);
					alert("error html = " + xhr.statusText);
				} // error
				
			}); // ajax
			
		}
		
		
		
	}); // click()
	
	
	
}); // $function()


