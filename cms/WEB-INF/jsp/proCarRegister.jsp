<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form action="baseManager!!multipleManager.action" id="myform" name="myform" method="post">
	<div class="info_body">
		<table border="0" class="base_table" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td class="info_title">号牌种类：</td>
					<td class="info"><input name="hpzl" id="hpzl" class="easyui-combobox" required="true"
							data-options="data:datacode.hpzl,valueField:'value',textField:'label'" /></td>
					<td class="info_title">号牌号码：</td>
					<td class="info"><input name="hphm" id="hphm" class="easyui-textbox"  />
						<a href="#" id="query_hphm" class="easyui-linkbutton" data-options="iconCls:'icon-search',onClick:loadCarInfo">查询</a>
					</td>
				</tr>
				<tr>
					<td class="info_title">公告日期：</td>
					<td class="info" colspan="3" ><input style="width: 420px;" name="ggbh" id="ggbh" class="easyui-combobox" required="true"
						data-options="valueField:'BH',textField:'GGRQ',onChange:gongGaoChange,editable:false"/>
						<a href="#" id="query_info" class="easyui-linkbutton" data-options="iconCls:'icon-search',onClick:loadGonggao">查看</a>	
					</td>
				</tr>
				<tr>
					<td class="info_title">	车辆类型：</td>
					<td class="info"><input  name="cllx" id="cllx" class="easyui-combobox" required="true"
						data-options="data:datacode.cllx,codevalueField:'value',textField:'label'" /></td>
				<!-- 	<td class="info_title">使用性质：</td>
					<td class="info"><input name="syxz" id="syxz" class="easyui-combobox" 
						data-options="data:datacode.syxz,valueField:'value',textField:'label'" /></td> -->
					<td class="info_title">业务类型：</td>
					<td class="info" ><input name="ywlx" id="ywlx" class="easyui-combobox" required="true"
						data-options="data:datacode.ywlx,valueField:'value',textField:'label',onChange:changeBT" /></td>
				</tr>
				<tr>
					<td class="info_title">车辆型号：</td>
					<td class="info"><input id="clxh"  name="clxh" data-options="onChange:getGgbh,readonly:true" required="true" class="easyui-textbox" /></td>
					<td class="info_title">车辆识别代号：</td>
					<td class="info"><input name="clsbdh" id="clsbdh" class="easyui-textbox"  required="true"  data-options="readonly:true" /></td>
				</tr>
				<tr>
					<td class="info_title">额定载人：</td>
					<td class="info"><input name="hdzk" id="hdzk" class="easyui-textbox"  data-options="readonly:true"  /></td>
					<td class="info_title">车身颜色：</td>
					<td class="info"><input name="csys" id="csys" class="easyui-textbox" data-options="onChange:colorChange"  required="true"  />
						
					</td>
				</tr>
				<tr>
					<td class="info_title">所有人：</td>
					<td class="info"><input name="syr" id="syr" class="easyui-textbox" required="true" /></td>
					<td class="info_title">身份证：</td>
					<td class="info" colspan="2"><input name="sfz"  id="sfz" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td class="info_title">地址：</td>
					<td class="info" colspan="3">
					<input name="dz"  id="dz"  class="easyui-textbox longText" multiline="true" data-options="height:28" />
					<input	type="hidden" id="bType" name="bType" value="preCarRegister">  
					 <input type="hidden" id="method" name="method" value="saveRegister">
					 <input type="hidden" id="isPrint" value="false">
					 
					 <div style="display: none;" id="hideArea" >
					 	                      
					 	<input	type="hidden" id="hgzbh" name="hgzbh">
					 	<input	type="hidden" id="hbdbqk" name="hbdbqk">
					 	<input	type="hidden" id="ccrq" name="ccrq">
					 	<input	type="hidden" id="zzcmc" name="zzcmc">
					 	<input	type="hidden" id="clpp1" name="clpp1">
					 	<input	type="hidden" id="clpp2" name="clpp2">
					 	<input	type="hidden" id="fdjxh" name="fdjxh">
					 	<input	type="hidden" id="rlzl" name="rlzl">
					 	<input	type="hidden" id="pl" name="pl">
					 	<input	type="hidden" id="gl" name="gl">
					 	<input	type="hidden" id="zxxs" name="zxxs">
					 	<input	type="hidden" id="lts" name="lts">
					 	<input	type="hidden" id="gbthps" name="gbthps">
					 	<input	type="hidden" id="zs" name="zs">
					 	<input	type="hidden" id="hxnbcd" name="hxnbcd">
					 	<input	type="hidden" id="hxnbkd" name="hxnbkd">
					 	<input	type="hidden" id="hxnbgd" name="hxnbgd">
					 	<input	type="hidden" id="zzl" name="zzl">
					 	<input	type="hidden" id="hdzzl" name="hdzzl">
					 	<input	type="hidden" id="zbzl" name="zbzl">
					 	<input	type="hidden" id="zqyzl" name="zqyzl">
					 	<input	type="hidden" id="qpzk" name="qpzk">
					 	<input	type="hidden" id="hpzk" name="hpzk">
					 	<input	type="hidden" id="bz" name="bz">
					 	
					 	<input	type="hidden" id="fdjh" name="fdjh">
					 	<input name="cwkc" id="cwkc" type="hidden" />
						<input name="cwkk" id="cwkk" type="hidden" />
						<input name="cwkg" id="cwkg" type="hidden" />
						<input name="qlj" id="qlj" type="hidden"/>
						<input name="hlj" id="hlj" type="hidden" />
						<input name="zj" id="zj" type="hidden"  />
						<input name="ltgg" id="ltgg" type="hidden"/>
						
						<input	type="hidden" id="gcjk" name="gcjk" value="A">
						<input	type="hidden" id="dpid" name="dpid" >
						
						<input id="ewm"  name="ewm" type="hidden"/>
					 </div>
					 </td>
				</tr>
			</tbody>
		</table>
		<div align="center" class="actionbar">
			<a href="javascript:saveAndPring()" class="easyui-linkbutton" data-options="iconCls:'icon-print'">保存并打印</a> 
			<a href="javascript:loadCenter({title:'预登记列表',action:'baseManager!proCarRegisterList!toPage.action'})" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
		</div>
	</div>
</form>
<div id="printTemplet"  style="display: none;"></div>
<script type="text/javascript" src="print/LodopFuncs.js"></script>
<script type="text/javascript" src="js/carRegister.js"></script>
<script type="text/javascript" >

$(function(){
	$("#win").window("move",{left:331,top: 10});
	$("#win").window("resize",{width:800,height: 600});
	$("#win").window("setTitle","公告详细信息");
	$("#win").window("refresh","gonggaoInfo.html");
	qrtNumber=window.setInterval("readQrtext();",200);
});
</script>
