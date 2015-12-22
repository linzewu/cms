<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机动车查验管理系统</title>
<link rel="stylesheet" type="text/css" href="js/easyui1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui1.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/easyui1.4/themes/color.css">
<link rel="stylesheet" type="text/css" href="css/dwf_0.1.css">
<link rel="stylesheet" type="text/css" href="css/cms.css">

<style type="text/css">
	.t{
	/* 	_margin-top: 339px;
		_margin-left: 600px;
		margin-top: 339px\9;
		margin-left: 600px\9;
		padding-top: 339px;
		padding-left: 600px; */
		
		position: absolute;
		top: 339px;
		left: 600px;
	}
</style>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui1.4/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="margin: 0;background-color: #0172AA;">
<form action="baseManager!!multipleManager.action"  method="post" id="loginForm">
	<div style="background-image: url('images/login.jpg');background-repeat: no-repeat;width: 1360px;height:610px;margin: 0 auto; ">
		<table  class="t">
			<tr>
				<td><input tabindex="1" name="userName"  value=""  id="userName"  style="height: 19px;width: 150px;"  /></td>
				<td rowspan="2" style="padding-left: 12px;">
					<a id="login" href="javascript:login()" >
					<img alt="登陆" src="images/loginbt.jpg"  style="border:0 ;">
					</a>
				</td>
			</tr>
			<tr>
				<td><input tabindex="2" name="password"  value=""  id="password"  style="height: 19px;width: 150px;" type="password" /></td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="mType" value="loginManager">
	<input type="hidden" name="method" value="login">
</form>
<script type="text/javascript">

function login(){
	//$('#loginForm').form('submit');
	var userName = $("#userName").val();
	var password=$("#password").val();
	if($.trim(userName)==""||$.trim(password)==""){
		$.messager.alert("提示","用户名密码为必填！","info");
		return
	}
	var param ={mType:"loginManager",method:"login"};
	param.userName=userName;
	param.password=password;
	
	$.post("baseManager!!multipleManager.action",param,function(data){
		if (data['state'] == "200") {
			location.href="index.action";
		}else{
			if(data['state']=='405'){
				$.messager.alert("提示",'用户名密码错误！');
			}else if(data['state']=='408'){
				$.messager.alert("提示",'用户名密码不能为空！');
			}
		}
	},"json").error(function(){
		$.messager("错误","网络异常","error");
	});
	
}
document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	
	if(e && e.keyCode==13&&$("#password").is(":focus")){ 
		login();
	}
};  

/* $(window).keydown(function(event){
	  switch(event.keyCode) {
	  	case 13:
	  		login();
	  }
}); */
</script>

</body>
</html>