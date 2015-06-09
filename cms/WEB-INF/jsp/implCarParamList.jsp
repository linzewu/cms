<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<table id="implCarParamList"   data-options="rownumbers:true,
		fitColumns:true,
		singleSelect:true,
		pagination:false,
		onDblClickRow:loadImplCarParam,
		url:'baseManager!!multipleManager.action'">
    <thead>
        <tr>  
            <th data-options="field:'bh',width:80"    style="width: 180px" >编号</th>
            <th data-options="field:'clxh',width:80" style="width: 180px"  >车辆型号</th>  
            <th data-options="field:'clpp1',width:80"   style="width: 180px"  >中文品牌</th>
            <th data-options="field:'cllx',width:80"  style="width: 180px" >车辆类型</th>
            <th data-options="field:'fdjxh',width:80"  style="width: 80px" >发动机型号</th>
            <th data-options="field:'hdzk',width:80" >载客量</th>
            <th data-options="field:'pc',width:80" >批次</th>
            <th data-options="field:'ggsxrq',width:80" >公告生效日期</th>
            <th data-options="field:'tzscrq',width:80" >停止生产日期</th>
            <th data-options="field:'zzcmc',width:80" >制造厂名称</th>
        </tr>  
      </thead>  
</table>

<input id="implCarParamListIsParse" value="false" type="hidden" />
<script type="text/javascript" src="js/carRegister.js"></script>
<script type="text/javascript"></script>