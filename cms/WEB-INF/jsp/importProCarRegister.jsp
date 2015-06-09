<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form action="baseManager!!multipleManager.action" id="myformImpl" name="myformImpl" method="post">
	<div class="info_body3">
		<table border="0" class="base_table3" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td class="info_title3">业务类型：</td>
					<td class="info3"><input value="A" name="ywlx" id="ywlx" class="easyui-combobox" required="true" tabindex="999"
						data-options="data:datacode.ywlx,valueField:'value',textField:'label',onChange:changeBT,readonly:true" /></td>
						
					<td class="info_title3">号牌种类：</td>
					<td class="info3"><input name="hpzl" id="hpzl" class="easyui-combobox" required="true"
							data-options="data:datacode.hpzl,valueField:'value',textField:'label'" tabindex="999" /></td>
					<td class="info_title3">	车辆类型：</td>
					<td class="info3"><input  name="cllx" id="cllx" class="easyui-combobox" required="true"
						data-options="data:datacode.cllx,codevalueField:'value',textField:'label'" tabindex="999"  /></td>
				</tr>
				
				<tr>
					<td class="info_title3">使用性质：</td>
					<td class="info3"><input name="syxz" id="syxz" class="easyui-combobox" required="true"
						data-options="data:datacode.syxz,valueField:'value',textField:'label'" tabindex="999" /></td>
					<td class="info_title3">车辆型号：</td>
					<td class="info3">
						<input id="clxh"  name="clxh"  required="true" class="easyui-textbox" style="width: 100px;" />
						<a href="#" id="query_hphm" class="easyui-linkbutton" data-options="iconCls:'icon-search',onClick:loadParam">查询</a>
					</td>
					<td class="info_title3">车辆制造厂名称：</td>
					<td class="info3"><input	id="zzcmc" name="zzcmc" class="easyui-textbox" /></td>
				</tr>
				
				<tr>
					<td class="info_title3">车辆中文品牌：</td>
					<td class="info3"><input	id="clpp1" name="clpp1" class="easyui-textbox" /></td>
					<td class="info_title3">车辆英文品牌：</td>
					<td class="info3"><input	id="clpp2" name="clpp2" class="easyui-textbox" /></td>
					<td class="info_title3">车辆制造国：</td>
					<td class="info3"><input	id="zzg" name="zzg" class="easyui-combobox" required="true"
						data-options="data:datacode.gjdm,valueField:'value',textField:'label'" tabindex="999" /></td>
				</tr>
				
				
				<tr>
					<td class="info_title3">额定载人：</td>
					<td class="info3"><input name="hdzk" id="hdzk" class="easyui-textbox"   /></td> 
					<td class="info_title3">车身颜色：</td>
					<td class="info3"><input name="csys" id="csys"  class="easyui-combobox" data-options="data:datacode.csys,multiple:true,valueField:'label',textField:'label'"    required="true" tabindex="999"  /></td>
					<td class="info_title3">车辆识别代号：</td>
					<td class="info3">	<input	id="clsbdh" name="clsbdh" class="easyui-textbox"   required="true" data-options="validType:'length[17,17]'"></td>
				</tr>
				
				<tr>
					<td class="info_title3">发动机号：</td>
					<td class="info3">	<input	id="fdjh" name="fdjh" class="easyui-textbox"  ></td>
					<td class="info_title3">轴数：</td>
					<td class="info3">	<input id="zs" name="zs" class="easyui-textbox" data-options="validType:'cc'" /></td>
					<td class="info_title3">轮胎数：</td>
					<td class="info3">	<input	id="lts" name="lts" class="easyui-textbox"  data-options="validType:'cc'"  ></td>
				</tr>
				
				<tr>
					<td class="info_title3">轮距：</td>
					<td class="info3">前<input	id="qlj" name="qlj" class="easyui-textbox" data-options="validType:'cc'"   style="width: 50px" > 
									后 <input	 id="hlj" name="hlj" class="easyui-textbox" data-options="validType:'cc'"  style="width: 50px" /></td>
					<td class="info_title3">外廓尺寸：</td>
					<td class="info3" colspan="3">长<input	id="cwkc" name="cwkc" class="easyui-textbox"  style="width: 50px" data-options="validType:'cc'" > 
									宽 <input	 id="cwkk" name="cwkk" class="easyui-textbox" style="width: 50px" data-options="validType:'cc'"  />
									高 <input	 id="cwkg" name="cwkg" class="easyui-textbox" style="width: 50px"  data-options="validType:'cc'" /></td>
					
				</tr>
				
				<tr>
					<td class="info_title3">轴距：</td>
					<td class="info3">	<input id="zj" name="zj" class="easyui-textbox" data-options="validType:'cc'"  /></td>
					<td class="info_title3">整备质量：</td>
					<td class="info3">	<input	id="zbzl" name="zbzl" class="easyui-textbox" data-options="validType:'cc'"  ></td>
					<td class="info_title3">总质量：</td>
					<td class="info3">	<input	 id="zzl" name="zzl" class="easyui-textbox" data-options="validType:'cc'"  /></td>
				</tr>
				
				
				<tr>
					<td class="info_title3">发动机型号：</td>
					<td class="info3">	<input	id="fdjxh" name="fdjxh" class="easyui-textbox"  ></td>
					<td class="info_title3">排量：</td>
					<td class="info3">	<input	 id="pl" name="pl" class="easyui-textbox" data-options="validType:'cc'"  /></td>
					<td class="info_title3">燃料种类：</td>
					<td class="info3">	<input	id="rlzl" name="rlzl" class="easyui-combobox"  data-options="data:datacode.rlzl,multiple:true,valueField:'value',textField:'label'"  tabindex="999"   ></td>
				</tr>
				
				<tr>
					<td class="info_title3">功率：</td>
					<td class="info3">	<input	id="gl" name="gl" class="easyui-textbox" data-options="validType:'cc'"   ></td>
					<td class="info_title3">轮胎规格：</td>
					<td class="info3">	<input	 id="ltgg" name="ltgg" class="easyui-textbox"   /></td>
					<td class="info_title3">环保达标：</td>
					<td class="info3" >	<input	id="hbdbqk" name="hbdbqk" class="easyui-textbox"  ></td>
				</tr>
				
				
				<tr>
					
					<td class="info_title3">所有人：</td>
					<td class="info3"><input name="syr" id="syr" class="easyui-textbox" required="true" /></td>
					<td class="info_title3">身份证：</td>
					<td class="info3" colspan="3"><input name="sfz"  id="sfz" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td class="info_title3">地址：</td>
					<td class="info3" colspan="5">
					<input name="dz"  id="dz"  class="easyui-textbox longText" multiline="true" data-options="height:28" />
					<input	type="hidden" id="bType" name="bType" value="preCarRegister">  
					 <input type="hidden" id="method" name="method" value="saveRegister">
					 <input type="hidden" id="isPrint" value="false">
					 
					 <div style="display: none;" id="hideArea" >
					 	<input	type="hidden" id="gcjk" name="gcjk" value="B">
					 	                
					 	<input	type="hidden" id="hgzbh" name="hgzbh">
					 	<input	type="hidden" id="ccrq" name="ccrq">
					 	<input	type="hidden" id="zxxs" name="zxxs">
					 	<input	type="hidden" id="gbthps" name="gbthps">
					 	<input	type="hidden" id="hxnbcd" name="hxnbcd">
					 	<input	type="hidden" id="hxnbkd" name="hxnbkd">
					 	<input	type="hidden" id="hxnbgd" name="hxnbgd">
					 	<input	type="hidden" id="hdzzl" name="hdzzl">
					 	<input	type="hidden" id="zqyzl" name="zqyzl">
					 	<input	type="hidden" id="qpzk" name="qpzk">
					 	<input	type="hidden" id="hpzk" name="hpzk">
					 	<input	type="hidden" id="bz" name="bz">
						<input id="ewm"  name="ewm" type="hidden"/>
					 </div>
					 </td>
				</tr>
			</tbody>
		</table>
		<div align="center" class="actionbar">
			<a href="javascript:implSaveAndPring()" class="easyui-linkbutton" data-options="iconCls:'icon-print'">保存并打印</a> 
			<a href="javascript:loadCenter({title:'预登记列表',action:'baseManager!proCarRegisterList!toPage.action'})" class="easyui-linkbutton" data-options="iconCls:'icon-back'">返回</a>
		</div>
	</div>
</form>
<div id="printTemplet"  style="display: none;"></div>
<script type="text/javascript" src="print/LodopFuncs.js"></script>
<script type="text/javascript" src="js/carRegister.js"></script>
<script type="text/javascript" >
document.onkeydown=function(evt)
{ 
    var key = event.keyCode; 
    if(key==13)                 
    {
       event.keyCode=9;
    }
}                



$(function(){
	$("#win").window("move",{left:331,top: 10});
	$("#win").window("resize",{width:1024,height: 600});
	$("#win").window("setTitle","进口车产品技术参数");
	$("#win").window("refresh","baseManager!implCarParamList!toPage.action");
});

</script>
