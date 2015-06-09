function array2str(array,sp){
	var str="";
	for(var i in array){
		str+=array[i];
		if(i!=array.length-1){
			str+=sp;
		}
	}
	return str;
}

function formatterDate(value,row,index){
	var date = new Date(value.time);
	return date.toLocaleString();
}

function formatterDateStrs(value,row,index){
	var date = new Date(value.time);
	if (date != null){
		return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()
		+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}else{
		return value;
	}
}


function ajaxFileUpload() {
	$.ajaxFileUpload({
		url : 'upload!uploadFile.action',
		secureuri : false,
		fileElementId : 'uploadFile',
		dataType : 'json',
		success : function(data, status) {
			if (data[0].message == "success") {
				createUploadDiv(data[1],true);
				var ufjVla = $("#uplodFilesJson").val();
				if(ufjVla.length>5){
					ufjVla=ufjVla.substring(5,ufjVla.length);
				}
				var jsonData=null;
				if(""!=ufjVla){
					jsonData = $.parseJSON(ufjVla);
				}else{
					jsonData=[];
				}
				jsonData.push(data[1]);
				$("#uplodFilesJson").val("data_"+JSON.stringify(jsonData)+"");
				console.log( $("#uplodFilesJson").val());
			}
			if (data[0].message == "error") {
				$.messager.show({
					title : '错误！',
					msg : "上传文件出错，请检查你的附件大小是否大于20M，如果不是，请联系管理员！"
				});
			}
		},
		error : function(data, status, e) {
			$.messager.show({
				title : '错误！',
				msg : "状态" + status + "错误信息：" + e
			});
		}
	});
}

function createUploadDiv(data,isdelete){

	var del = "<a href='javaScript:void(0)' onClick=\"removeFile(this,'" + data.id
	+ "');\">删除</a>";
	
	var fn=encodeURI(encodeURI(data.filename));
	
	var download="<a href='upload!download.action?fileId="+data.id+"&fn="+fn+"'\">下载</a>";
	
	$(".fileInfo").append("<div>" + data.filename + (isdelete==true?del:"") +download + "</div>");
	
}


function removeFile(e,id) {
	$.ajax({
		type : "POST",
		url : "upload!deleteUploadFile.action",
		data : {'fileId':id},
		success : function(msg) {
			if(msg=="success"){
				var ufjVla = $("#uplodFilesJson").val();
				var index=-1;
				ufjVla=ufjVla.substring(5,ufjVla.length);
				var jsonData=$.parseJSON(ufjVla);
				$.each(jsonData,function(i,n){
					if(n.id==id){
						index=i;
					}
				});
				if(index!=-1){
					jsonData.splice(index,1);
				}
				$("#uplodFilesJson").val("data_"+JSON.stringify(jsonData));
				$(e).parent().remove();
				console.log( $("#uplodFilesJson").val());
			}
			else{
				$.messager.show({
					title : '提示！',
					msg : "删除失败"
				});
			}
		}
	});
}


function loadInfo(url,actionType,beid,formId,callback){
	$.post(url,{'be.actionType':actionType,'be.id':beid},function(data){
		if(data.state==200){
			var formData=data.data;
			$("#"+formId).form("load",formData);
			$(".readField").each(function(i,n){
				var value = formData[$(n).attr("name")];
				$(n).html(value);
			});
			dateTimeFormater(formData);
			dataFormater();
			fileJsonFormater();
			
			if(callback){
				callback(formData);
			}
		}else{
			$.messager.alert("错误","数据加载错误，请刷新页面重试", "error");
		}
	},"json");
}

function dataFormater(){
	$(".dataFormater").each(function(i,n){
		var key = $(n).attr("dataKey");
		var value= $(n).text();
		var data = datacode[key];
		var strs=value.split(",");
		var temp="";
		for(var s in strs){
			$.each(data,function(j,k){
				if(k.value==strs[s]){
					temp+=k.label+",";
				}
			});
		}
		if(temp!=""){
			temp=temp.substring(0, temp.length-1);
		}
		$(n).text(temp);
	});
}

function dateTimeFormater(formData){
	$(".dateTimeFormater").each(function(i,n){
		var dateJson = formData[$(n).attr("name")];
		var fd =getDateStringByTime(dateJson.time) ;
		$(n).text(fd);
	});
}

function getDateStringByTime(time){
	var date=new Date(time);
	return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()
	+":"+date.getMinutes()+":"+date.getSeconds();
}

function fileJsonFormater(){
	$(".fileInfo").each(function(i,n){
		var value = $(n).html();
		$(n).html('');
		if(value.length>5){
			var jsonData=$.parseJSON(value.substring(5,value.length));
			$.each(jsonData,function(j,k){
				createUploadDiv(k);
			});
		}
	});
}

function tabOnSelect(title,index){
	var panel = $(this).tabs('getTab',index);
	var initData=panel.find("input[name=isInit]");
	var isInit=initData.val();
	if(isInit=='false'){
		var tableIds = initData.attr('tableId');
		var ids = tableIds.split(",");
		for(var i in ids){
			 $("#"+ids[i]).datagrid();
		}
		initData.val('true');
	}
}

function delBaseEntity(actionType,id,callback){
	console.log(actionType+"\t"+id);
	$.messager.confirm("提示","你确认删除该数据",function(r){
		if(r){
			$.post("baseManager!!deleteBaseEntity.action",{'be.actionType':actionType,'be.id':id},function(data){
				console.log(data);
				if(data.state==200){
					$.messager.alert("提示","数据删除成功","info",callback);
				}else{
					$.messager.alert("提示","数据删除失败，请刷新页面尝试。","error");
				}
		   },"json");
		}
	});
}

function savaDataByFrom(formId){
	var isOK = $('#'+formId).form("validate");
	if(isOK){
		$.messager.confirm("提示","请检查数据是否正确!<br>您确认提交该数据吗？", function(r){
    		if(r){
    			 $('#'+formId).submit();
    		}
    	});
	}
}




function baseEntityManager(id,actionType){
	
	this.id=id;
	this.actionType=actionType;
	this.editIndex=undefined;
	this.onClickRow=function(index) {
		if (this.editIndex != index) {
			if (this.endEditing()) {
				$("#"+this.id).datagrid('selectRow', index).datagrid(
						'beginEdit', index);
				this.editIndex = index;
			} else {
				$("#"+this.id).datagrid('selectRow', this.editIndex);
			}
		}
	};
	
	this.endEditing= function() {
			if (this.editIndex == undefined) {
				return true;
			}
			if ($('#'+this.id).datagrid('validateRow', this.editIndex)) {
				$('#'+this.id).datagrid('endEdit', this.editIndex);
				var rowData = $('#'+this.id).datagrid('getRows')[this.editIndex];
				rowData['be.actionType'] = this.actionType;
				
				$.each(rowData,function(key,value){
					if(value==""){
						rowData[key]=null;
					}
				});
				console.log(rowData);
				var dataId = saveData(rowData);
				if(dataId!=null){
					rowData['be.id'] = dataId;
					this.editIndex = undefined;
					return true;
				}else{
					$.messager.alert("提示","保存出错，请联刷新页面重试","error");
					$('#'+this.id).datagrid('selectRow', this.editIndex)
					.datagrid('beginEdit', this.editIndex);
					return false;
				}
			} else {
				return false;
			}
		};
		
		this.append = function(data) {
			console.log(this.id+"\t"+this.actionType);
			if (this.endEditing()) {
				$('#'+this.id).datagrid('appendRow', data);
				this.editIndex = $('#'+this.id).datagrid('getRows').length - 1;
				$('#'+this.id).datagrid('selectRow', this.editIndex)
						.datagrid('beginEdit', this.editIndex);
			}
		};
		
		this.remove = function() {
			if (this.editIndex == undefined) {
				return
			}
			var rowData = $('#'+this.id).datagrid('getRows')[this.editIndex];
			if(rowData['be.id']!=null&&rowData['be.id']!=undefined&&rowData['be.id']!=""){
				var  data = deleteData(this.actionType,rowData['be.id']);
				if(data.state!=200){
					$.messager.alert("删除出错",data.errorInfo,"error")
				}
			}
			$('#'+this.id).datagrid('cancelEdit', this.editIndex)
					.datagrid('deleteRow', this.editIndex);
			this.editIndex = undefined;
		};
		
		this.accept = function() {
			if (this.endEditing()) {
				$('#'+this.id).datagrid('acceptChanges');
			}
		};
		this.reject = function() {
			$('#'+this.id).datagrid('rejectChanges');
			this.editIndex = undefined;
		}
}
// 保存数据的方法
function saveData(data) {
	if(data['be.id']==null){
		data['be.id']=undefined;
	}
	var responseText = $.ajax({
		type: "POST",
		url : "baseManager!!saveBaseEntity.action",
		async : false,
		data : data
	}).responseText;
	
	var rdata = $.parseJSON(responseText);
	if(rdata.state==200){
		return rdata.sid;
	}else{
		$.messager.alert("错误","数据保存错误","error");
	}
	return null;
}

// 删除数据的方法
function deleteData(actionType,id) {
	var data={'be.actionType':actionType,'be.id':id};
	var text= $.ajax({
		type: "POST",
		url : "baseManager!!deleteBaseEntity.action",
		async : false,
		data : data
	}).responseText;
	
	return $.parseJSON(text);
}

