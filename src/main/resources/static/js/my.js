//	$(document).ready(function(){//用于初始化界面
//		getInfo();
//	});	

	function getInfo(){
//		var id = $.cookie("id");
//		var username = $.cookie("username");
//		var avatar = $.cookie("avatar");
//		var result = {'id':id, 'username':username, 'avatar': avatar};
		var result = '';
		$.ajax({
			"async":false,
            "url":"/user/getInfo",
            "type":"GET",
            "dataType":"json",
            "success":function(data){
                if(data.state==1){
                	result = data.data;
                	//result = data.message.username;
                    //$('#username').html(data.message.username);
                   //window.location.href = "index.do";
                }else{
                	result = null;
                	//alert(data.message);
                	//window.location.href = "/";
                }
                console.log(data);
            },
        });
		return result;
	}
	
	function getUserData(){
		var result = '';
		$.ajax({
			"async":false,
            "url":"/user/getUserData",
            "type":"GET",
            "dataType":"json",
            "success":function(data){
                if(data.state==1){
                	result = data.data;
                	$("#username").val(data.data.username);
                	$("#phone").val(data.data.phone);
                	$("#email").val(data.data.email);
                	//sex = data.data.gender==0?'女':'男';
                	sex = data.data.gender;
                	$("input[name=gender][value="+sex+"]").attr("checked",true);
                    //$('#username').html(data.message.username);
                   //window.location.href = "index.do";
                }else{
                	result = null;
                	//alert(data.message);
                	//window.location.href = "/";
                }
                console.log(data);
            },
        });
		return result;
	}

	function getProduct(type){
//		var result = '';
		$.ajax({
			"async":false,
            "url":"/products/"+type,
            "type":"GET",
            "dataType":"json",
            "success":function(data){
                if(data.state==1){
                	result = data.data;

		            for(var i=0;i<result.length;i++){
		                // 每一条记录生成一个option
		                var option='<div class="col-md-12">'+
							'<div class="col-md-7 text-row-2"><a href="product.html?id='+result[i].id+'">'+result[i].title+'</a></div>'+
							'<div class="col-md-2">'+result[i].price+'</div>'+
							'<div class="col-md-3"><img src="'+result[i].image+'collect.png" class="img-responsive" /></div>'+
						'</div>';
		                // 将option添加到select内部
		                $("#"+type+"Product").append(option);
		            }
                	//result = data.message.username;
                    //$('#username').html(data.message.username);
                   //window.location.href = "index.do";
                }
                console.log(data);
            },
        });
//		return result;
	}

	function getProductInfo(id){
		var result = '';
		$.ajax({
			"async":false,
            "url":"/products/"+id+"/get",
            "type":"GET",
            "dataType":"json",
            "success":function(data){
                if(data.state==1){
                	result = data.data;
                }
                console.log(data);
            },
        });
		return result;
	}
	
	function reg(){
        var password = $('#password').val();
        var password2 = $('#confirm').val();
        
        $('.msg').html("");
        if(password==password2){
        	$.ajax({
        		"url":"/user/register",
        		"data":$("#form-reg").serialize(),
        		"type":"POST",
        		"dataType":"json",
        		"success":function(data){
        			if(data.state==1){
        				alert(data.message);
        				window.location.href = "login.html";
        			}else{
        				$('#error_msg').html(data.message);
        			}
        			console.log(data);
        		},
        	});
        }else{
        	$("#error_msg").html("两次输入的密码不一致！");
        }
        return false;
	}
	
	function login(){
		var username = $('#username').val();
        var password = $('#password').val();
        
        $('.msg').html("");
        $.ajax({
        	"url":"/user/login",
        	"data":$("#login_form").serialize(),
        	"type":"POST",
        	"dataType":"json",
        	"success":function(data){
        		if(data.state==1){
        			$.cookie("id",data.data.uid,{"expires":7});
        			$.cookie("username",data.data.username,{"expires":7});
        			$.cookie("avatar",data.data.avatar,{"expires":7});
        			alert(data.message);
        			window.location.href = "index.html";
        		}else{
        			$('#error_msg').html(data.message);
        		}
        		console.log(data);
        	},
        });
        return false;
	}

	function update_pwd(){
        var new_pwd = $('#new_pwd').val();
        var password2 = $('#confirm').val();
        
        $('.msg').html("");
        if(new_pwd==password2){
        	$.ajax({
        		"url":"/user/updatePwd",
        		"data":$("#update_pwd_form").serialize(),
        		"type":"POST",
        		"dataType":"json",
        		"success":function(data){
        			if(data.state==1){
        				alert(data.message);
        				window.location.href = "";
        			}else{
        				$('#error_msg').html(data.message);
        			}
        			console.log(data);
        		},
        	});
        }else{
        	$("#error_msg").html("新密码两次输入的不一致！");
        }
        return false;
	}

	function updateUserData(){
        
        $('.msg').html("");
        $.ajax({
        	"url":"/user/updateUserData",
        	"data":$("#update_user_form").serialize(),
        	"type":"POST",
        	"dataType":"json",
        	"success":function(data){
        		if(data.state==1){
        			alert(data.message);
        			window.location.href = "";
        		}else{
        			$('#error_msg').html(data.message);
        		}
        		console.log(data);
        	},
        });
        return false;
	}

	function updateUserAvatar(){
        
        $('.msg').html("");
        $.ajax({
        	"url":"/user/updateUserAvatar",
            "data":new FormData($("#form-change-avatar")[0]),
            "type":"post",
            "contentType":false,
            "processData":false,
            "dataType":"json",
        	"success":function(data){
        		if(data.state==1){
        			alert(data.message);
        			$("#img-avatar").attr("src",data.data);
        		    // 更新cookie中头像的路径
        		    $.cookie("avatar",data.data,{"expires":7});
        		}else{
        			$('#error_msg').html(data.message);
        		}
        		console.log(data);
        	},
        });
        return false;
	}
	
	// parent是父级编号，sid是select标签的id属性的值
	function appendList(parent,sid){
	    // 发送ajax请求
	    $.ajax({
	        "url":"/districts/",
	        "data":"parent="+parent,
	        "type":"get",
	        "dataType":"json",
	        "success":function(json) {
	            // 获取列表的数组
	            var list = json.data;
	            if(sid=="city"){
	            	$("#area").html("<option value='0'>---- 请选择 ----</option>");
	            }
            	$("#"+sid).html("<option value='0'>---- 请选择 ----</option>");
	            // 遍历数组
	            for(var i=0;i<list.length;i++){
	            	console.log(list[i]);
	                // 每一条记录生成一个option
	                var option="<option value='"+
	                  list[i].code+"'>"+list[i].name+"</option>";   
	                // 将option添加到select内部
	                $("#"+sid).append(option);
	            }
	        }
	    });
	}
	
	function addAddress(){
        
        $('.msg').html("");
        $.ajax({
        	"url":"/addresses/create_address",
        	"data":$("#add_address_form").serialize(),
        	"type":"POST",
        	"dataType":"json",
        	"success":function(data){
        		if(data.state==1){
        			alert(data.message);
        			window.location.href = "address.html";
        		}else{
        			$('#error_msg').html(data.message);
        		}
        		console.log(data);
        	},
        });
        return false;
	}

	function getAddress(){
		var result = '';
        $.ajax({
			"async":false,
        	"url":"/addresses/get_address",
        	"type":"GET",
			"async":false,
        	"success":function(data){
        		if(data.state==1){
        			result = data.data;
        		}else{
        			result = null;
        		}
        		console.log(result);
        	},
        });
        return result;
	}

	function delAddress(id){
        
        $.ajax({
        	"url":"/addresses/delete_address",
        	"data":{id:id},
        	"type":"POST",
        	"dataType":"json",
        	"success":function(data){
        		if(data.state==1){
        			alert(data.message);
        			//window.location.href = "address.html";
        		}else{
        			$('#error_msg').html(data.message);
        		}
        		console.log(data);
        	},
        });
        return false;
	}

	function defAddress(id){
        
        $.ajax({
        	"url":"/addresses/default_address",
        	"data":{id:id},
        	"type":"POST",
        	"dataType":"json",
        	"success":function(data){
        		if(data.state==1){
        			alert(data.message);
        			//window.location.href = "address.html";
        		}else{
        			$('#error_msg').html(data.message);
        		}
        		console.log(data);
        	},
        });
        return false;
	}
	
	//初始化购物车
	function initCart(){
		
		var cart_cookie = $.cookie("cart");
		var cart = {};
		if(cart_cookie){
			cart = JSON.parse(cart_cookie);
		}
		var i = 1;
		for (x in cart) {
			
			var id = x;
			var num = cart[x];
			var result = getProductInfo(id);
			var title = result.title;
			var price = result.price;
			var image = result.image;
			var max_num = result.num;
			
			var html = '<tr data-id="'+id+'">'+
			'<td>'+
			'<input type="checkbox" class="ckitem" checked="checked"/>'+
		'</td>'+
		'<td><img src="'+image+'collect.png" class="img-responsive" /></td>'+
		'<td><a href="product.html?id='+id+'">'+title+'</a></td>'+
		'<td>¥<span id="goodsPrice'+i+'">'+price+'</span></td>'+
		'<td>'+
		'<input type="button" value="-" class="num-btn" onclick="reduceNum('+i+')" />'+
		'<input id="goodsCount'+i+'" type="text" max="'+max_num+'" size="2" readonly="readonly" class="num-text" value="'+num+'">'+
		'<input class="num-btn" type="button" value="+" onclick="addNum('+i+')" />'+
		'</td>'+
		'<td><span id="goodsCast'+i+'"></span></td>'+
		'<td>'+
			'<input type="button" onclick="delCartItem(this)" class="cart-del btn btn-default btn-xs" value="删除" />'+
		'</td>'+
	'</tr>';
			$("#cart-body").append(html);
			i++;
		}
	}
	
	//操作购物车
	function optCart(id,opt){
		var cart_cookie = $.cookie("cart");
		var cart = {};
		if(cart_cookie){
			cart = JSON.parse(cart_cookie);
		}
		
		if(cart == null){
			cart = {};
		}
		
		if(id){
			
			if(opt=="delete"){
				delete cart[id];
			}else if(cart.hasOwnProperty(id)&&opt=="add"){
				cart[id]+=1;
			}else if(!cart.hasOwnProperty(id)){
				cart[id]=1;
			}else if(opt=="minus"&&cart[id]>=1){
				cart[id]-=1;
			}
			
		}else{
			console.log(cart_cookie);
			return cart;
		}
		$.cookie("cart",JSON.stringify(cart),{"expires":7});
		console.log($.cookie("cart"));
	}
	
	//初始化订单确认页面
	function initOrder(){
		var add = getAddress();
		for(var i=0;i<add.length;i++){
        	var data = add[i];
        	var localtion = data.provinceName+data.cityName+data.areaName+data.address;
        	var deafult = data.isDefault;
        	if(deafult==1){
        		var htm = '<option value="'+data.aid+'" selected="selected">'+data.name+'&nbsp;&nbsp;&nbsp;'+data.tag+
        		'&nbsp;&nbsp;&nbsp;'+localtion+'&nbsp;&nbsp;&nbsp;'+data.phone+'&nbsp;&nbsp;&nbsp;默认</option>';
        	}else{
        		var htm = '<option value="'+data.aid+'">'+data.name+'&nbsp;&nbsp;&nbsp;'+data.tag+
        		'&nbsp;&nbsp;&nbsp;'+localtion+'&nbsp;&nbsp;&nbsp;'+data.phone+'</option>';
        	}
        	$("#order_address").append(htm);
        }
		var cart_cookie = $.cookie("order");
		var cart = {};
		if(cart_cookie){cart = JSON.parse(cart_cookie);}
		var j = 0;
		var amount = 0;
		for (x in cart) {
			var id = x;
			var num = cart[x];
			var result = getProductInfo(id);
			var title = result.title;
			var price = result.price;
			var image = result.image;
			var max_num = result.num;
			var html = '<tr>'+
				'<td><img src="'+image+'collect.png" class="img-responsive" /></td>'+
				'<td>'+title+'</td>'+'<td>¥<span>'+price+'</span></td>'+
				'<td>'+num+'</td>'+'<td><span>'+(price*num)+'</span></td>'+'</tr>';
			amount += price*num;
			$("#order_body").append(html);
			j++;
		}
		$("#selectCount").html(j);
		$("#selectTotal").html(amount);
	}
	
	//获得订单价格信息
	function getOrderPrice(orderNo){

        $.ajax({
			"async":false,
        	"url":"/order/getOrderPriceByOrderNo",
        	"data":{orderNo:orderNo},
        	"type":"POST",
			"async":false,
        	"success":function(data){
        		if(data.state==1){
        			order = data.data;
    		    	$("#tip").html("订单号："+orderNo+"，支付金额¥"+order.payment+"，收款方达内学子商城");
    		    	$("#payment").html("¥"+order.payment);
        		}else{
        			alert(data.message);
        			$.cookie("orderNo",null);
        			$("#error_msg").html(data.message);
        			window.location.href = "cart.html";
        		}
        		console.log(data);
        	},
        });
		
	}
	
	//初始化订单页
	function initOrdersPage(){
		
        $.ajax({
			"async":false,
        	"url":"/order/getOrder",
        	"type":"GET",
			"async":false,
        	"success":function(data){
        		if(data.state==1){
        			var html = "";
        			ordersNo = data.data;
        			if(ordersNo.length==0){
        				alert("无订单数据！");
        			}
        			console.log(ordersNo);
        			for (var i=0;i<ordersNo.length;i++) {
        				var orderNo = ordersNo[i];
        				var orderId = orderNo.orderNo;
        				var d = new Date(orderNo.createdTime);
        				var times=d.getFullYear() + '-' + (d.getMonth() + 1) + 
        				'-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
        				html += '<div class="panel panel-default"><div class="panel-heading"><p class="panel-title">'+
							'订单号：'+orderId+'，下单时间：'+times+' ，收货人：'+orderNo.name+
							'</p></div><div class="panel-body"><table class="orders-table" width="100%">'+
							'<thead><tr><th width="15%"></th><th width="30%">商品</th><th width="8%">单价</th><th width="8%">'+
							'数量</th><th width="9%">小计</th><th width="10%">售后</th><th width="10%">状态</th><th width="10%">'+
							'操作</th></tr></thead><tbody class="orders-body">';
        				$.ajax({
        					"async":false,
        		        	"url":"/order/getOrderItem",
        		        	"data":{orderNo:orderId},
        		        	"type":"POST",
        					"async":false,
        		        	"success":function(data){
        		        		if(data.state==1){
        		        			ordersItem = data.data;
        		        			console.log(ordersItem);
        		        			for (var j=0;j<ordersItem.length;j++) {
        		        				orderItem=ordersItem[j];
        		        			html += '<tr>'+
									'<td><img src="'+orderItem.productImage+'collect.png" class="img-responsive" /></td>'+
									'<td>'+orderItem.productName+'</td>'+
									'<td>¥<span>'+orderItem.currentUnitPrice+'</span></td>'+
									'<td>'+orderItem.quantity+'件</td>'+
									'<td>¥<span>'+orderItem.totalPrice+'</span></td>'+
									'<td><a href="#">申请售后</a></td>'+
									'<td>'+
										'<div>已发货</div>'+
										'<div><a href="orderInfo.html">订单详情</a></div>'+
									'</td>'+
									'<td><a href="#" class="btn btn-default btn-xs">确认收货</a></td>'+
								'</tr>';
        		        			}
        		        			
        		        		}else{
        		        			alert(data.message);
        		        		}
//        		        		console.log(data);
        		        	},
        		        });
        				html += '</tbody></table><div><span class="pull-right">订单总金额：¥'+orderNo.payment+
        				'</span></div></div></div>';
        			}
        			
    		    	$("#content").html(html);
        		}else{
        			alert(data.message);
        		}
//        		console.log(data);
        	},
        });
		
	}
	