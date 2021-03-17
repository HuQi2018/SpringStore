$(function() {
	
	
	//地址管理点击删除时的提示
	$(".add-del").click(function() {
			if (confirm("确定删除这个地址吗？")) {
				delAddress($(this).data('id'));
				$(this).parent().parent().hide();
				//location.href = "address.html";
			}
		})
		
	//点击设置为默认地址时的效果
	$(".add-def").click(function() {
		defAddress($(this).data('id'));
		$(".add-def").show();
		$(this).hide();
	})
	
	//地址修改
	$(".fa-edit").click(function() {
		alert("地址修改！");
		//$(this).data('id');
		//$(".add-def").show();
		//$(this).hide();
	})
//	$(".add-def:eq(0)").hide();
	
	
})