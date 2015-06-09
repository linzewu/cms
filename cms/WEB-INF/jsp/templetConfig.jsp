<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form action="baseManager!!saveBean.action" id="templetForm" name="templetForm" method="post">
<div style="width: 740px; font: 12px; margin: 0 auto;">
	<div style="width: 720px; height: 120px;">
		<span>模板名称：</span> 
		<input name="templetName" value="" 	style="width: 200px;" class="easyui-textbox" required="true" /><br>
		<br>
		<table class="printTable" cellpadding="0" cellspacing="0"
			style="width: 740px; border-bottom: 1px black solid; border-right: 1px black solid; padding: 2px 2px 2px 2px;">
			<tr>
				<td style="width: 40px"
					style=" border-top: 1px black solid;border-left: 1px black solid;">类别</td>
				<td style="width: 40px;"
					style=" border-top: 1px black solid;border-left: 1px black solid;">序号</td>
				<td style="width: 120px;"
					style=" border-top: 1px black solid;border-left: 1px black solid;">查验项目</td>
				<td style="width: 120px;"
					style=" border-top: 1px black solid;border-left: 1px black solid;">判定</td>
				<td style="width: 70px;"
					style=" border-top: 1px black solid;border-left: 1px black solid;">类别</td>
				<td style="width: 40px;"
					style=" border-top: 1px black solid;border-left: 1px black solid;">序号</td>
				<td style="width: 120px;"
					style=" border-top: 1px black solid;border-left: 1px black solid;">查验项目</td>
				<td style="width: 120px;"
					style=" border-top: 1px black solid;border-left: 1px black solid;">判定</td>
			</tr>
			<tr>
				<td rowspan="9"
					style="border-top: 1px black solid; border-left: 1px black solid;">通用<br>项目
				</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">1</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">车辆识别代码</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst1" type="radio" value="0"><span>√</span>
					<input name="rst1" type="radio" value="2"><span>X</span>
					<input name="rst1" type="radio" value="1"><span>—</span>
				</td>
				<td rowspan="4"
					style="border-top: 1px black solid; border-left: 1px black solid;">大中型客车、危险化学品运输车</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">15</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">灭火器</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst15" type="radio" value="0"><span>√</span>
					<input name="rst15" type="radio" value="2"><span>X</span>
					<input name="rst15" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">2</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">发动机型号/号码</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst2" type="radio" value="0"><span>√</span>
					<input name="rst2" type="radio" value="2"><span>X</span>
					<input name="rst2" type="radio" value="1"><span>—</span>
				</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">16</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">行驶记录装置、车内外录像监控装置</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst16" type="radio" value="0"><span>√</span>
					<input name="rst16" type="radio" value="2"><span>X</span>
					<input name="rst16" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">3</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">车辆品牌/型号</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst3" type="radio" value="0"><span>√</span>
					<input name="rst3" type="radio" value="2"><span>X</span>
					<input name="rst3" type="radio" value="1"><span>—</span>
				</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">17</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">应急出口/应急锤、乘客门</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst17" type="radio" value="0"><span>√</span>
					<input name="rst17" type="radio" value="2"><span>X</span>
					<input name="rst17" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">4</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">车身颜色</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst4" type="radio" value="0"><span>√</span>
					<input name="rst4" type="radio" value="2"><span>X</span>
					<input name="rst4" type="radio" value="1"><span>—</span>
				</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">18</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">外部标示/文字、喷涂</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst18" type="radio" value="0"><span>√</span>
					<input name="rst18" type="radio" value="2"><span>X</span>
					<input name="rst18" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">5</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">核定载人数</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst5" type="radio" value="0"><span>√</span>
				<input name="rst5" type="radio" value="2"><span>X</span>
				<input name="rst5" type="radio" value="1"><span>—</span>
				</td>
				<td rowspan="2"
					style="border-top: 1px black solid; border-left: 1px black solid;">其他</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">19</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">标志灯具、警报器</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst19" type="radio" value="0"><span>√</span>
					<input name="rst19" type="radio" value="2"><span>X</span>
					<input name="rst19" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">6</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">车辆类型</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst6" type="radio" value="0"><span>√</span>
					<input name="rst6" type="radio" value="2"><span>X</span>
					<input name="rst6" type="radio" value="1"><span>—</span>
				</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">20</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">安全技术检验合格证明</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst20" type="radio" value="0"><span>√</span>
				<input name="rst20" type="radio" value="2"><span>X</span>
				<input name="rst20" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">7</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">号牌/车辆外观形状</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst7" type="radio" value="0"><span>√</span>
					<input name="rst7" type="radio" value="2"><span>X</span>
					<input name="rst7" type="radio" value="1"><span>—</span>
				</td>
				<td colspan="4" rowspan="3"
					style="border-top: 1px black solid; border-left: 1px black solid;">查验结论：</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">8</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">轮胎完好情况</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst8" type="radio" value="0"><span>√</span>
					<input name="rst8" type="radio" value="2"><span>X</span>
					<input name="rst8" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">9</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">安全带、三角警告牌</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst9" type="radio" value="0"><span>√</span>
					<input name="rst9" type="radio" value="2"><span>X</span>
					<input name="rst9" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td rowspan="5"
					style="border-top: 1px black solid; border-left: 1px black solid;">货车<br>挂车
				</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">10</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">外廓尺寸、轴数、轴距</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst10" type="radio" value="0"><span>√</span>
				<input name="rst10" type="radio" value="2"><span>X</span>
				<input name="rst10" type="radio" value="1"><span>—</span>
				</td>
				<td rowspan="3" colspan="4"
					style="border-top: 1px black solid; border-left: 1px black solid;">查验员：</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">11</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">整备质量</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst11" type="radio" value="0"><span>√</span>
					<input name="rst11" type="radio" value="2"><span>X</span>
					<input name="rst11" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">12</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">轮胎规格</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst12" type="radio" value="0"><span>√</span>
				<input name="rst12" type="radio" value="2"><span>X</span>
				<input name="rst12" type="radio" value="1"><span>—</span>
				</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">13</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">侧后部防护装置</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst13" type="radio" value="0"><span>√</span>
					<input name="rst13" type="radio" value="2"><span>X</span>
					<input name="rst13" type="radio" value="1"><span>—</span>
				</td>
				<td rowspan="2" colspan="2"
					style="border-top: 1px black solid; border-left: 1px black solid;">复查合格</td>
				<td colspan="2" rowspan="2"
					style="border-top: 1px black solid; border-left: 1px black solid;">查验员：</td>
			</tr>
			<tr>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">14</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">车身反光标识和车辆<br>尾部标识板、喷涂
				</td>
				<td
					style="border-top: 1px black solid; border-left: 1px black solid;">
					<input name="rst14" type="radio" value="0"><span>√</span>
					<input name="rst14" type="radio" value="2"><span>X</span>
					<input name="rst14" type="radio" value="1"><span>—</span>
				</td>
			</tr>
		</table>
		
		<div style="width: 740px;text-align: center;" >
			<br>
			<a href="javascript:configSave()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
			<a href="javascript:loadCenter({title:'车辆预登记',action:'baseManager!templetConfigList!toPage.action'})" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">返回</a>
		</div>
	</div>
	</div>
	<input  type="hidden" name="id" >
	<input  type="hidden" name="bType" value="defaultItemTempletConfig" >
	<input  type="hidden" name="templet" id="templet" >
</form>



<script type="text/javascript">

$(function(){
	$('#templetForm').form({
		success:function(data){
			var datajson = $.parseJSON(data);
			if (datajson['state'] == "200") {
				$("#id").val(datajson['sid']);
				$.messager.alert("提示","保存成功","info",function(){
					loadCenter({title:'车辆预登记',action:'baseManager!templetConfigList!toPage.action'});
				});
			}
		}
	});
	if(templetRow!=null){
		var ds= templetRow['templet'].split("|");
		for(var i=1;i<ds.length;i++){
			templetRow['rst'+i]=ds[i-1];
		}
		$('#templetForm').form("load",templetRow);
	}
	
});

function configSave(){
	
	var templet="";
	
	for(var i=1;i<=20;i++){
		var value = $("input:radio[name=rst"+i+"]:checked ").val();
		if(value==null){
			$.messager.alert("提示","模板第"+i+"项必须选择！");
			return ;
		}
		templet+=value+"|";
	}
	$("#templet").val(templet);
	
	$("#templetForm").form("submit");
	
}

</script>
