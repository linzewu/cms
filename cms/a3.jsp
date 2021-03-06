<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
	<div style="width: 720px; font: 12px;" >
		<div style="width: 720px;height: 120px;">
			<img  class="2code"  src="/temp2code/${param.codeId }.jpg" alt="" style="width: 100px;height: 100px;float: left;">
			<span style="width: 350px;height:60px;float: left;text-align: right;margin-top: 60px;font-size: 32px;padding-left: 25px;">机动车查验记录表</span>
			<span style="width: 228px;height: 60px;float:right ;text-align: right;" class="codeId">${param.codeId }</span>
			<!-- <img id="1code" src="" alt="" style="width: 228px;height: 60px;float:right ;"></div> -->
		<div style="width: 720px;height: 5px;">
			<span style="float: left;width: 458">号牌号码（流水号或其他与车辆能对应的号码）：<label id="carIdCodeLabel"  style="width: 14px;"></label></span>
			<span style="float: right;width:104px; ">使用性质：<label id="userTypeLabel" style="font-size: 14px;"></label></span>
			<span style="float: right;width:158px;">号牌种类：<label id="plateTypeLabel" style="font-size: 14px;"></label></span>
		</div>
		<img border='0' src='http://10.39.147.6:8080/cms/images/sy_yc.jpg' style="z-index: -1;position: absolute;" />
		<table   class="printTable"  cellpadding="0" cellspacing="0"  style="width: 720px;border-bottom: 1px black solid;border-right: 1px black solid;padding: 2px 2px 2px 2px;background-repeat: no-repeat;background-image: url('images/sy_yc.jpg');" >
			<tr>
				<td colspan="8"  style="	border-top: 1px black solid;border-left: 1px black solid;">
					<span>业务类型:</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="A">注册登记</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="I">转入</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="B">转移登记</span>
					<span><input name="businessTypeCheckBox"  type="checkbox" value="4">变更迁出</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="5">变更车身颜色</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="6">更换车身或车架</span><br>&nbsp;&nbsp;&nbsp;&nbsp;
					<span><input name="businessTypeCheckBox" type="checkbox" value="7">更换发动机</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="8">变更使用性质</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="9">重新打刻VIN</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="10">重新打刻发动机号</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="11">更换整车</span><br>&nbsp;&nbsp;&nbsp;&nbsp;
					<span><input name="businessTypeCheckBox" type="checkbox" value="12">加装/拆除操纵辅助装置</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="13">申请登记证书</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="14">补领登记证书</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="15">监督解体</span>
					<span><input name="businessTypeCheckBox" type="checkbox" value="16">其他</span>
				</td>
			</tr>
			<tr>
				<td style="width: 40px" style=" border-top: 1px black solid;border-left: 1px black solid;">类别</td>
				<td style="width: 40px;" style=" border-top: 1px black solid;border-left: 1px black solid;">序号</td>
				<td style="width: 170px;" style=" border-top: 1px black solid;border-left: 1px black solid;">查验项目</td>
				<td style="width: 70px;" style=" border-top: 1px black solid;border-left: 1px black solid;">判定</td>
				<td style="width: 70px;" style=" border-top: 1px black solid;border-left: 1px black solid;">类别</td>
				<td style="width: 40px;" style=" border-top: 1px black solid;border-left: 1px black solid;">序号</td>
				<td style="width: 170px;" style=" border-top: 1px black solid;border-left: 1px black solid;">查验项目</td>
				<td style="width: 70px;" style=" border-top: 1px black solid;border-left: 1px black solid;">判定</td>
			</tr>
			<tr>
				<td rowspan="9" style=" border-top: 1px black solid;border-left: 1px black solid;">通用<br>项目</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">1</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">车辆识别代码</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td rowspan="4" style=" border-top: 1px black solid;border-left: 1px black solid;">大中型客车、危险化学品运输车</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">15</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">灭火器</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">2</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">发动机型号/号码</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">16</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">行驶记录装置、车内外录像监控装置</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">3</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">车辆品牌/型号</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">17</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">应急出口/应急锤、乘客门</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">4</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">车身颜色</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">18</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">外部标示/文字、喷涂</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">5</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">核定载人数</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td rowspan="2" style=" border-top: 1px black solid;border-left: 1px black solid;">其他</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">19</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">标志灯具、警报器</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
				<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">6</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">车辆类型</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">20</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">检验合格证明</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">7</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">号牌/车辆外观形状</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td colspan="4" rowspan="3" style=" border-top: 1px black solid;border-left: 1px black solid;">查验结论：</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">8</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">轮胎完好情况</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">9</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">安全带、三角警告牌</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
				<tr>
				<td rowspan="5" style=" border-top: 1px black solid;border-left: 1px black solid;">货车<br>挂车</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">10</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">外廓尺寸、轴数、轴距</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td rowspan="3" colspan="4" style=" border-top: 1px black solid;border-left: 1px black solid;">查验员：</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">11</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">整备质量</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">12</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">轮胎规格</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">13</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">侧后部防护装置</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td rowspan="2" colspan="2"  style=" border-top: 1px black solid;border-left: 1px black solid;">复检合格</td>
				<td colspan="2" rowspan="2" style=" border-top: 1px black solid;border-left: 1px black solid;">查验员：</td>
			</tr>
			<tr>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">14</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">车身反光标识和车辆<br>尾部标识板、喷涂</td>
				<td style=" border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
			
			<tr>
				<td colspan="6" style="text-align: center; height: 250px; border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
				<td colspan="2"  style="vertical-align: top; border-top: 1px black solid;border-left: 1px black solid;">备注：</td>
			</tr>
			<tr>
				<td colspan="8" style="text-align: center;height: 85px; border-top: 1px black solid;border-left: 1px black solid;">车辆识别代码（车架号）拓印膜<br>(注册登记、转移登记、转出、转入、更换车身或者车架、更换整车、申领登记证书、重新打刻VIN)</td>
			</tr>
			<tr>
				<td colspan="8" style="text-align: center;height: 85px; border-top: 1px black solid;border-left: 1px black solid;">&nbsp;</td>
			</tr>
		</table>
	</div>