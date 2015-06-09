<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" >

$(function(){
	$("#templetConfig").datagrid({
		queryParams:{
			bType:"defaultItemTempletConfig"
		}
	});
});

var templetRow=null;

function loadtemplet(){
 	var row = $("#templetConfig").datagrid("getSelected");
 	if(row==null){
 		$.messager.alert("提示","请选择要编辑的模板","warning");
 		return;
 	}
 	templetRow=row;
 	loadCenter({title:'车辆预登记 修改',action:'baseManager!templetConfig!toPage.action'});
 	
}

</script>
    
<table id="templetConfig"    data-options="rownumbers:true,
		toolbar:'#templetToolbar',
		fitColumns:true,
		singleSelect:true,
		pagination:true,
		pageSize:10,
		onDblClickRow:loadImplCarParam,
		url:'baseManager!!getBeanList.action'">
    <thead>
        <tr>  
            <th data-options="field:'templetName',width:80"    style="width: 180px" >模板名称</th>
        </tr>  
      </thead>  
</table>

<div id="templetToolbar"  style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
	   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="loadCenter({title:'车辆预登记 新增',action:'baseManager!templetConfig!toPage.action'})">车辆预登记</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="loadtemplet()" >编辑模板</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="del('templetConfig','defaultItemTempletConfig')">删除模板</a>
    </div>
</div>
