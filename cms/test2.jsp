<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="js/easyui1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui1.4/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/easyui1.4/themes/color.css">

<link rel="stylesheet" type="text/css" href="css/cms.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui1.4/jquery.easyui.min.js"></script>

<title>首页</title>
</head>
<body>
<table id="preCarRegisterDG" class="easyui-datagrid"   data-options="rownumbers:true,
		fitColumns:true,
		singleSelect:true,
		pagination:true,
		pageSize:10,
		url:'baseManager!!getBeanList.action?bType=preCarRegister',
		method:'get'"
		>  
    <thead>
        <tr>  
            <th data-options="field:'cllx',width:80"   style="width: 180px" >车辆类型</th>
            <th data-options="field:'clsbdh',width:80" style="width: 180px"  >车辆识别码</th>  
            <th data-options="field:'csys',width:80"  style="width: 180px"  >车身颜色</th>
            <th data-options="field:'hpzl',width:80"  style="width: 180px" >号牌种类</th>
            <th data-options="field:'ywlx',width:80" style="width: 80px" >业务类型</th>
            <th data-options="field:'syr',width:80" >所有人</th>
            <th data-options="field:'sfz',width:80" >身份证</th>
        </tr>  
      </thead>  
</table>
</body>
</html>