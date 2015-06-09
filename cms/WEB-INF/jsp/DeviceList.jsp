<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<table id="deviceManager"   data-options="rownumbers:true,
		fitColumns:true,
		toolbar:'#toolbar2',
		singleSelect:true,
		pagination:true,
		pageSize:10,
		url:'device!getDevices.action'">  
    <thead>
        <tr>  
            <th data-options="field:'imei',width:80"   >设备串号</th>
            <th data-options="field:'stationCode',width:80"  >检验机构</th>  
            <th data-options="field:'x',width:80"   >坐标X</th>
            <th data-options="field:'y',width:80"   >坐标Y</th>
            <th data-options="field:'scope',width:80"    >使用范围（米）</th>
            <th data-options="field:'startTime',width:80"  >查验时间（开始）</th>
            <th data-options="field:'endTime',width:80"  >查验时间（结束）</th>
            <th data-options="field:'lastDate',width:80"  >有效时间</th>
             <th data-options="field:'state',width:80" >设备状态</th>
        </tr>  
      </thead>  
</table>
<div id="toolbar2"  style="padding:5px;height:auto">
	<div style="margin-bottom:5px">
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEdit()">修改预登记信息</a>
    </div>
    <div style="margin-bottom:5px">
    	车辆识别代号：<input type="text" class="easyui-textbox" id="clsbdh" >
    	<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="queryClsbdh()">查询</a>
    </div>
</div>
<script type="text/javascript">
$(function(){
	$('#deviceManager').datagrid();
});
</script>

