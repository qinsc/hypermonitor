
function render_host_operations(data, type, row){
	var OperationHtml = 
		'<div class="btn-group">' +    
		'	<button data-toggle="dropdown"' + 
		'		class="btn btn-info dropdown-toggle" aria-expanded="false">' +
		'		操作 <span class="caret"></span>' +
		'	</button>' +
		'	<ul class="dropdown-menu dropdown-menu-right">' +
		'		<li><a id="'+row.hostId+'" onclick="osLogout(this)">注销</a></li>' +
		'		<li><a id="'+row.hostId+'" onclick="osShutdown(this)">关机</a></li>' +
		'		<li><a id="'+row.hostId+'" onclick="osReboot(this)">重启</a></li>' +    
		'		<li class="divider"></li>' +    
		'		<li><a id="'+row.hostId+'" onclick="sendMsg(this)">发送消息</a></li>' +   
		'		<li class="divider"></li>' +     
		'		<li><a id="'+row.hostId+'" onclick="addHostDesc(this)">备注</a></li>' + 
		'	</ul>' + 
		'</div>'; 
	return OperationHtml;                                                          
}

var hostGrid = new Datatable();
var contextPath;
function initHostDatatables(ctxPath, tableId){
	contextPath = ctxPath;
	hostGrid.init({
        src: $("#"+tableId),
        idField:"hostId",
        dataTable: {
        	dom: 'fBrtip',
            "ajax": {
            	 url:contextPath+"/rest/hosts/",
            	 type:"GET"
             }, 
             "processing": true,
             "pageLength": 10,
             buttons: [ 
                  {
		              text: '注销',
		              action: function ( e, dt, node, config ) {
		            	  osLogout();
		              }
		          },
		          {
		              text: '关机',
		              action: function ( e, dt, node, config ) {
		            	  osShutdown();
		              }
		          },
		          {
		              text: '重启',
		              action: function ( e, dt, node, config ) {
		            	  osReboot();
		              }
		          },
		          {
		              text: '发送消息',
		              action: function ( e, dt, node, config ) {
		            	  sendMsg();
		              }
		          },
		          {
		              text: '添加主机',
		              action: function ( e, dt, node, config ) {
		            	  addHosts();
		              }
		          } ],
             "columns":[
                 {"data":"check"},
                 {"data" : "online", "render" : function(data, type, full, meta) {
                	 return data == 1?'<h style="color:green;">在线<h>':'<h style="color: red;">离线<h>';
        		 }},  
                 {"data" : "hostName"},  
                 {"data" : "manageIp"},  
                 {"data" : "os", "orderable": false},  
                 {"data" : "desc", "orderable": false},  
                 { render: render_host_operations, "orderable":false} 
             ],
        }
    });
}

var addHostGrid = new Datatable();
function initAddHostDatatables(tableId){
	addHostGrid.init({
        src: $("#"+tableId),
        idField:"hostId",
        dataTable: {
        	dom: 'rtip',
//            "ajax": {
//            	 url:contextPath+"/rest/hosts/",
//            	 type:"GET"
//             }, 
             "pageLength": 5,
             "columns":[
                 {"data":"check"},
                 {"data" : "hostName"},  
                 {"data" : "manageIp"},  
             ],
        }
    });
}

$(document).delegate(".btn.btn-primary","click",function(){
	var opt = $("#myModal #opt").val();
	var optMsg = "";
	if (opt == "sendMsg"){
		optMsg = "发送此消息";
	} else {
		optMsg = "修改此备注";
	}
	
	$.messager.confirm("确认", "确定要"+ optMsg+"吗?", function() { 
		var ids = $("#myModal #ids").val(); 
		var opt = $("#myModal #opt").val(); 
		var text = $("#myModal #text").val();
		
		debugger;
		var url = contextPath+"/rest/hosts/";
		var data = {};
		var message = "";
		if (opt == "sendMsg"){
			url += "message";
			data.hostIds = [];
			var idsArray = ids.split(",");
			$.each(idsArray, function(i, val){
				data.hostIds.push(val);
			});
			data.message = text;
			message = "消息发送";
		} else if (opt == "addDesc"){
			url += "desc";
			data.hostId = ids;
			data.desc = text;
			message = "备注修改";
		} else {
			return;
		}
		
		$.ajax({
			url: url,
			type: "post",
			contentType: "application/json",
			data:JSON.stringify(data),
			success: function(ret){
				// $.messager.alert("消息", message+"成功");
				$('#myModal').modal("hide");
			},
			error: function(ret){
				$.messager.alert("消息", message+"失败");
			}
		});
	});
})

function osLogout(row) {
	doOsOpertaion(row, "注销");
}

function osShutdown(row) {
	doOsOpertaion(row, "关闭");
}

function osReboot(row) {
	doOsOpertaion(row, "重启");
}

function doOsOpertaion(row, opt){
	var hostIds = getselectHostIds(row);
	if (hostIds.length == 0){
		$.messager.alert("提示", "请选择要操作的主机");
		return;
	}
	$.messager.confirm("确认", "确定要"+ opt + "所选机器吗？", function() { 
		var url = contextPath+"/rest/hosts/";
		if (opt == "注销"){
			url += "logoff";
		} else if (opt == "关闭"){
			url += "shutdown";
		} else if (opt == "重启"){
			url += "reboot";
		} else {
			return
		}
		
		$.ajax({
			url: url,
			type: "post",
			contentType: "application/json",
			data:JSON.stringify(hostIds),
			success: function(ret){
				$.messager.alert("消息", "主机"+opt+"成功");
				hostGrid.reload();
			},
			error: function(ret){
				$.messager.alert("消息", "主机"+opt+"失败");
			}
		});
    });
}

function sendMsg(row) {
	var hostIds = getselectHostIds(row);
	if (hostIds.length == 0){
		$.messager.alert("提示", "请选择要操作的主机");
		return;
	}
	
	$("#myModal #myModalLabel").text("给主机发送消息"); 
	$("#myModal #btn_action").html("发送"); 
	$("#myModal #text").attr("placeholder","长度不超过200字");
	$("#myModal #text").val("");
	$("#myModal #ids").val(hostIds); 
	$("#myModal #opt").val("sendMsg"); 
	
    $('#myModal').modal({keyboard:false,show:true});
}

function addHostDesc(row) {
	var host = hostGrid.getRowData(row.id);
	
	$("#myModal #myModalLabel").text("修改主机描述信息"); 
	$("#myModal #btn_action").html("保存"); 
	$("#myModal #text").attr("placeholder","长度不超过500字");
	$("#myModal #text").val(host.desc);
	$("#myModal #ids").val(row.id); 
	$("#myModal #opt").val("addDesc"); 
	
    $('#myModal').modal({keyboard:false,show:true});
}

function addHosts() {
	$("#addHostsModal #addHostsModalLabel").text("添加主机"); 
	
    $('#addHostsModal').modal({keyboard:false,show:true});
}

function getselectHostIds(row){
	var hostIds = [];
	if(row){
		hostIds.push(row.id);
	} else {
		var selectdHosts = hostGrid.getSelectedRows();
		$.each(selectdHosts, function(i,val){
			hostIds.push(val.hostId);
		})
	}
	return hostIds;
}
