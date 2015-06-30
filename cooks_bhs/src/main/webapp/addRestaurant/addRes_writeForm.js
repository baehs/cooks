$(function () {
	
	$('#addResBtn').click(function() {
		
		if($("#r_Num").val() == "" || $("#r_Name").val() == "" || $("#r_Adress").val() == ""
			|| $("#r_Phone").val() == ""|| $("#r_Foodtype").val() == ""|| $("#r_Info").val() == "") {
			alert("입력하지 않은 내용이 있습니다.");
		} else {
			addRes();
		}
			
	}); // click()
	
}); // $function()

function addRes(){
	
	$.ajax({
		type: "POST",
		url: "/cooks/addRestaurant.app",
		async: true,
		dataType: "json",
		data: {
			r_Num:$("#r_Num").val(),
			r_Name:$("#r_Name").val(),
			r_Adress: $("#r_Adress").val(),
			r_Phone:$("#r_Phone").val(),
			r_Foodtype:$("#r_Foodtype").val(),
			r_Info: $("#r_Info").val()
		},
		
		success : function(data) {
			if(data.status == 'success') {
				location.href = "/cooks/cooksMain.app"
			} 
	
		},
		error : function(xhr) {
			console.log("error", xhr);
			alert("error html = " + xhr.statusText);
		} // error
		
	}); // ajax
}
