<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<table id="preCarRegisterDG"   data-options="rownumbers:true,
		fitColumns:true,
		toolbar:'#toolbar2',
		singleSelect:true,
		pagination:true,
		pageSize:10,
		url:'baseManager!!multipleManager.action?bType=preCarRegister&method=getCarList'"
		>  
    <thead>
        <tr>  
            <th data-options="field:'cllx',width:80"  formatter="formatterCllx"  style="width: 180px" >车辆类型</th>
            <th data-options="field:'clsbdh',width:80" style="width: 180px"  >车辆识别码</th>  
            <th data-options="field:'csys',width:80"  formatter="formatterCsys" style="width: 180px"  >车身颜色</th>
            <th data-options="field:'hpzl',width:80"  formatter="formatterHpzl"  style="width: 180px" >号牌种类</th>
            <th data-options="field:'ywlx',width:80" formatter="formatterYwlx" style="width: 80px" >业务类型</th>
            <th data-options="field:'syr',width:80" >所有人</th>
            <th data-options="field:'sfz',width:80" >身份证</th>
        </tr>  
      </thead>  
</table>
<div id="toolbar2"  style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
	   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" plain="true" onclick="print()">打印查验单</a> 
	   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="loadCenter({title:'车辆预登记',action:'baseManager!proCarRegister!toPage.action'})">车辆预登记</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEdit()">修改预登记信息</a>
    </div>
    <div style="margin-bottom:5px">
    	车辆识别代号：<input type="text" class="easyui-textbox" id="clsbdh" >
    	<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="queryClsbdh()">查询</a>
    </div>
</div>
<div id="printTemplet"  style="display: none;"></div>
<script type="text/javascript" src="print/LodopFuncs.js"></script>
<script type="text/javascript" src="js/carRegister.js"></script>
<script type="text/javascript">
$(function(){
	$('#preCarRegisterDG').datagrid();
});
var ppRow=null;
function toEdit(){
	var row = $("#preCarRegisterDG").datagrid("getSelected");
	if(row!=null){
		ppRow=row;
		loadCenter({title:'车辆预登记',action:'baseManager!proCarRegisterEdit!toPage.action'});
	}else{
		$.messager.alert("提示","请选择一行数据","warning");
	}
}

function queryClsbdh(){
	var clsbdh=$("#clsbdh").textbox("getValue");
	$('#preCarRegisterDG').datagrid("reload",{clsbdh:clsbdh});

}

</script>

