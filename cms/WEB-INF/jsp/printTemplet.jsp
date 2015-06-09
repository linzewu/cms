<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

var printList=[];

$(function(){
	var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	var pc=LODOP.GET_PRINTER_COUNT();
	
	for(var i=0;i<pc;i++){
		var data={};
		data.value=i;
		data.label=LODOP.GET_PRINTER_NAME(i);
		printList.push(data);
	}
	
});

function createPrinObject(){
	
	var printObje="<object  id=\"LODOP_OB\" classid=\"clsid:2105C259-1E0C-4534-8141-A753534CB4CA\" width=0 height=0>"+  
  	 			"<embed id=\"LODOP_EM\" type=\"application/x-print-lodop\" width=0 height=0></embed> "+"</object>";
   
  	return $(printObje);
	
}

function pintTempletA3(){
	
	
	var printIndex= $("#print").combobox("getValue");
	
	var printCount=$("#count").numberbox("getValue");
	
	if(printCount==""){
		$.messager.alert("提示" ,"请输入打印份数");
		return;
	}
	if(printCount>200){
		$.messager.alert("提示" ,"一次最多打印200份");
		return;
	}
	
	$.messager.progress({
		title : '提示',
		msg : '查验单打印中...'
	});
	
	try{
		for(var i=0;i<printCount;i++){
			var param={};
			param.mType = "preCarRegisterManager";
			param.method = "getSeq";
			
			var rt =  $.ajax({
				url : "baseManager!!multipleManager.action?timestamp="
						+ new Date().getTime(),
				async : false,
				data : param
			}).responseText;
			var obj = $.parseJSON(rt);
			if (obj != null && obj.state==200) {
				var id = obj.data;
				if(id!=""){
					var data =  $.ajax({
						url : "a3.jsp?timestamp="
								+ new Date().getTime(),
						async : false,
						data : {codeId:id}
					}).responseText;
					
					var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
					
					
					LODOP.SET_PRINTER_INDEX(printIndex);
					LODOP.SET_PRINT_COPIES(1);
					
					LODOP.ADD_PRINT_HTM(-10, 
									10,
									1024,
									1100,
									data);
					LODOP.PRINT();
				}else{
					$.messager.alert("提示","无法获取防伪码！");
					return;
				}
			}
		}
	}catch(err){
		$.messager.progress('close');
	}finally{
		$.messager.progress('close');
	}
}

function pintSetup(){
	var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	LODOP.PRINTA();
}

</script>

<label>打印机：</label>
<input  class="easyui-combobox"  id="print" data-options="data:printList,valueField:'value',textField:'label'" style="width: 500px;" /><br><br>
<label>打印份数：</label>
<input  class="easyui-numberbox"  id="count" max="200"  min="1" value="1"/>
<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print',onClick:pintTempletA3">打印</a>
