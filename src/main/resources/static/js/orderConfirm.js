$(function() {
	$(".link-pay").click(function() {
		if($.cookie("order") != "null"){
			$.ajax({
	        	"url":"/order/create",
	        	"data":{aid:$("#order_address").val(),products:$.cookie("order")},
	        	"type":"POST",
	        	"dataType":"json",
	        	"success":function(data){
	        		if(data.state==1){
	        			alert(data.message);
	        			$.cookie("orderNo",data.data);
	        			var order = JSON.parse($.cookie("order"));
	        			for (x in order) {//删除cart里的
	        				var id = x;
	        				console.log(x);
	        				optCart(x,"delete");
	        			}
	        			$.cookie("order",null);
	        			window.location.href = "payment.html";
	        		}else{
	        			$('#error_msg').html(data.message);
	        		}
	        		console.log(data);
	        	},
	        });
		}else{
			$('#error_msg').html("购物车为空！");
		}
	})
})
$(function() {
	$(".link-success").click(function() {
		var orderNo = $.cookie("orderNo");
		if(orderNo != "null"){
			$.ajax({
	        	"url":"/order/payOrder",
	        	"data":{orderNo:orderNo,payType:$("input[name=optionsRadios]:checked").val()},
	        	"type":"POST",
	        	"dataType":"json",
	        	"success":function(data){
	        		if(data.state==1){
	        			alert(data.message);
	        			$.cookie("orderNo",null);
	        			window.location.href = "paySuccess.html";
	        		}else{
	        			$('#error_msg').html(data.message);
	        		}
	        		console.log(data);
	        	},
	        });
		}else{
			$('#error_msg').html("订单号为空！");
		}
	})
})