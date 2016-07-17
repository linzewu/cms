<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
Object user =session.getAttribute("user");
if(user==null){
%>
<script type="text/javascript">
window.location.href="login.jsp";
</script>
<% 
}
%>   

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="js/easyui1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui1.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/easyui1.4/themes/color.css">
<link rel="stylesheet" type="text/css" href="css/dwf_0.1.css">

<link rel="stylesheet" type="text/css" href="css/cms.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/dwf_0.1.js"></script>
<script type="text/javascript" src="js/dataCode.js"></script>
<script type="text/javascript" src="js/easyui1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui1.4/locale/easyui-lang-zh_CN.js"></script>
<script language="javascript" src="print/CheckActivX.js"></script>

<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>  
       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed> 
</object> 

<OBJECT ID="vehPrinter" CLASSID="CLSID:F1EFB37B-C778-4AF8-B0C2-B647C9E89E2D" CODEBASE="VehPlatForm_web.CAB#version=2,7,0,0">
</OBJECT>
<title>机动车查验管理系统</title>
</head>
<body class="easyui-layout">
    <div data-options="region:'north'"  class="top">
    	<img alt="" src="images/top_yc.jpg" >
    	<div class="dwf-nav1"  action="js/menu.js"></div>
    </div>
    <div data-options="region:'west'" split="true" title="系统菜单" class="west">
    	<div class="dwf-menu1" ></div>
    </div>
    <div data-options="region:'center',title:'center title'" class="center" id="center"></div>
     <div data-options="region:'south'" class="bottom">
     	<div class="copyrigth">
     		<span>&copy;2011-2014 江苏省盐城市车辆管理所</span>
     	</div>
     	<div class="tool">
     		<!-- <div class="item">
				<a href="javaScript:void(0);" class="easyui-linkbutton" onclick="$('#dlg').dialog('open')" data-options="iconCls:'icon-edit'">密码修改</a>
			</div> -->
			<div class="item">
				<a href="index.action" class="easyui-linkbutton"  data-options="iconCls:'icon-home'">主页</a>
			</div>
			<div class="item">
				<a href="javascript:logout('baseManager!!multipleManager.action?mType=loginManager&method=logout')" class="easyui-linkbutton" data-options="iconCls:'icon-logout'"  >注销</a>
			</div>
			<!-- <div class="item userInfo">
				<label>当前角色：</label>
				<input class="easyui-combobox"  editable="false"/>
			</div> -->
			<div class="item userInfo">
				<label>当前用户：${user['RealName']} </label>
			</div>
	     	</div>
    </div>
    <div id="win" class="easyui-window"  collapsible="false" ></div>
</body>
</html>