
function render_host_operations(data, type, row){
	var OperationHtml = 
		'<div class="btn-group">' +    
		'	<button data-toggle="dropdown"' + 
		'		class="btn btn-info dropdown-toggle" aria-expanded="false">' +
		'		操作 <span class="caret"></span>' +
		'	</button>' +
		'	<ul class="dropdown-menu dropdown-menu-right">' +
		'		<li><a id="'+row.hostId+'" onclick="sendMsg(this)">发送消息</a></li>' +   
		'		<li class="divider"></li>' +    
		'		<li><a id="'+row.hostId+'" onclick="osLogout(this)">注销</a></li>' +
		'		<li><a id="'+row.hostId+'" onclick="osShutdown(this)">关机</a></li>' +
		'		<li><a id="'+row.hostId+'" onclick="osReboot(this)">重启</a></li>' +    
		'		<li class="divider"></li>' +     
		'		<li><a id="'+row.hostId+'" onclick="addHostDesc(this)">备注</a></li>' + 
		'		<li><a id="'+row.hostId+'" onclick="removeHosts(this)">移除主机</a></li>' + 
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
		          } ,
		          {
		              text: '移除主机',
		              action: function ( e, dt, node, config ) {
		            	  removeHosts();
		              }
		          } 
		          
		          ],
             "columns":[
                 {"data":"check"},
                 {"data" : "online", "render" : function(data, type, full, meta) {
                	 return data == 1?'<h style="color:green;">在线<h>':'<h style="color: red;">离线<h>';
        		 }},  
                 {"data" : "hostName","render" : function(data, type, full, meta) {
                	 return '<a id="'+full.hostId+'" href="#" onclick="toShowHostDetail(this)">'+data+'</a>';
        		 }},
                 {"data" : "manageIp"},  
        		 {render: function(data, type, full, meta) {
        			 var remoteCtlHtml =  
	        			'<div class="btn-group" style="min-width:80px;">                                                                                                           ' +
	        			'  <button type="button" class="btn btn-success" id="'+full.hostId+'" onclick="vnc(this)">VNC</button>      						   ' +
	        			'  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> ' +
	        			'    <span class="caret"></span>                                                                                                   ' +
	        			'    <span class="sr-only">Toggle Dropdown</span>                                                                                  ' +
	        			'  </button>                                                                                                                       ' +
	        			'  <ul class="dropdown-menu" style="background-color: #5cb85c; min-width: 80px;">                                                                                                      ' +
	        			'    <li><a id="'+full.hostId+'" style="background-color: #5cb85c; font-size: 14px; color: #fff;" onclick="rdp(this)">RDP</a></li>                                                			   ' +
	        			'  </ul>                                                                                                                           ' +
	        			'</div>';
        			 return remoteCtlHtml;
        		 }},
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
// "ajax": {
// url:contextPath+"/rest/hosts/",
// type:"GET"
// },
        	 "processing": false,
             "pageLength": 5,
             "columns":[
                 {"data":"check"},
                 {"data" : "hostName"},  
                 {"data" : "manageIp"},  
             ],
        }
    });
}

function osLogout(row) {
	doOsOpertaion(row, "注销");
}

function osShutdown(row) {
	doOsOpertaion(row, "关闭");
}

function osReboot(row) {
	doOsOpertaion(row, "重启");
}

function toShowHostDetail(row){
	$("#detailHostsModal #detailHostsModalLabel").text("主机详情");
	$("#detailHostsModal #info").html(""); 
    $('#detailHostsModal').modal({keyboard:false,show:true});
    
	$.ajax({
		url: contextPath+"/rest/hosts/detail/"+row.id,
		type: "get",
		contentType: "application/json",
		success: function(ret){
			$("#detailHostsModal #info").html(formatHtml(ret)); 
		},
		error: function(err){
			// $.messager.alert("消息", "获取主机详情失败");
			alert("Error :" + JSON.stringify(err))
		}
	});
}

function formatHtml(html){
	var s = html.replace(/\s/g, "&nbsp;")
	// s = s.replace("　", "&nbsp;")
	return s;
}

function vnc(row){
	var host = hostGrid.getRowData(row.id);
	window.open(contextPath+"/views/host/guacd.jsp?protocol=vnc&hostIp=" + host.manageIp);
}

function rdp(row){
	var host = hostGrid.getRowData(row.id);
	window.open(contextPath+"/views/host/guacd.jsp?protocol=rdp&hostIp=" + host.manageIp);
}

//function showHostDetail(detialInfo){
//	$("#detailHostsModal #detailHostsModalLabel").text("主机详情");
//	
//	$("#detailHostsModal #hostName").html(detialInfo.hostName); 
//	$("#detailHostsModal #hostDesc").html(detialInfo.desc); 
//	
//	$("#detailHostsModal #bootTime").html(getLocalTime(detialInfo.bootTime/1000)); 
//	$("#detailHostsModal #upTime").html(getDateDiff(detialInfo.upTime/1000)); 
//	
//	$("#detailHostsModal #os").html(detialInfo.os); 
//	$("#detailHostsModal #osPlatform").html(detialInfo.osPlatform); 
//	$("#detailHostsModal #osPlatformFamily").html(detialInfo.osPlatformFamily); 
//	$("#detailHostsModal #osPlatformVersion").html(detialInfo.osPlatformVersion); 
//	
//	$("#detailHostsModal #cpuCores").html(detialInfo.cpuCores); 
//	$("#detailHostsModal #cpuModelName").html(detialInfo.cpuModelName); 
//	$("#detailHostsModal #cpuMhz").html(detialInfo.cpuMhz); 
//	$("#detailHostsModal #cpuUsage").html(detialInfo.cpuUsage); 
//	
//	$("#detailHostsModal #memSize").html(detialInfo.memSize); 
//	$("#detailHostsModal #memUsed").html(detialInfo.memUsed); 
//	$("#detailHostsModal #memUsage").html(detialInfo.memUsage); 
//	
//	$("#detailHostsModal #nics").empty();
//	$("#detailHostsModal #nics").append($("<legend>网卡</legend>").get(0));  
//	$.each(detialInfo.nicInfos, function(i, val){
//		var nic = 
//			'<dl class="dl-horizontal">' +
//			'  <dt>网卡名称</dt> ' +
//			'  <dd>' + val.nicName + '</dd>'+						  
//			'  <dt>网卡IP地址</dt> ' +
//			'  <dd>' + val.ip + '</dd>'	 +			  
//			'  <dt>网卡物理地址</dt> ' +
//			'  <dd>' + val.mac + '</dd>'	 +			  
//			'</dl> ';
//		$("#detailHostsModal #nics").append($(nic).get(0));
//	});
//	
//	$("#detailHostsModal #disks").empty();
//	$("#detailHostsModal #disks").append($("<legend>硬盘</legend>").get(0));  
//	$.each(detialInfo.diskInfos, function(i, val){
//		var disk = 
//			'<dl class="dl-horizontal">' +
//			'  <dt>硬盘</dt> ' +
//			'  <dd>' + val.path + '</dd>'+						  
//			'  <dt>设备路径</dt> ' +
//			'  <dd>' + val.device + '</dd>'	 +			  
//			'  <dt>文件系统类型</dt> ' +
//			'  <dd>' + val.fsType + '</dd>'	 +			  
//			'  <dt>总大小</dt> ' +
//			'  <dd>' + val.diskSize + '</dd>'	 +			  
//			'  <dt>已使用</dt> ' +
//			'  <dd>' + val.diskUsed + '</dd>'	 +			  
//			'  <dt>使用率</dt> ' +
//			'  <dd>' + val.usedPercent + '</dd>' +			  
//			'</dl> ';
//		$("#detailHostsModal #disks").append($(disk).get(0));
//	});
//	
//    $('#detailHostsModal').modal({keyboard:false,show:true});
//}

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

function removeHosts(row){
	var hostIds = getselectHostIds(row);
	if (hostIds.length == 0){
		$.messager.alert("提示", "请选择要操作的主机");
		return;
	}
	$.messager.confirm("确认", "确定要移除所选机器吗？", function() { 
		var url = contextPath+"/rest/hosts/remove";
		
		$.ajax({
			url: url,
			type: "post",
			contentType: "application/json",
			data:JSON.stringify(hostIds),
			success: function(ret){
				$.messager.alert("消息", "主机移除成功");
				hostGrid.reload();
			},
			error: function(ret){
				$.messager.alert("消息", "主机移除失败");
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
	
	addHostGrid.dataTable.clear();
	
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

function checkIp(ip){
	return true;
}

function checkIpRange(startIp, endIp){
	return true;
}

$(document).delegate("#myModal  #btn_action","click",function(){
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
				hostGrid.reload();
			},
			error: function(ret){
				$.messager.alert("消息", message+"失败");
			}
		});
	});
});

$(document).delegate("#addHostsModal  #btn_scan","click",function(){
	var startIp = $("#addHostsModal #startIp").val();
	var endIp = $("#addHostsModal #endIp").val();
	
	if(startIp == ""){
		$.messager.alert("提示","必须填写起始地址");
		return;
	}
	
	if(endIp == ""){
		$.messager.alert("提示","必须填写结束地址");
		return;
	}
	
	if (isCIDR(startIp) && isCIDR(endIp)){
		startIp = startIp.replace("/",":");
		endIp = endIp.replace("/",":");
		addHostGrid.reload(contextPath+"/rest/hosts/scan/"+startIp+"/"+endIp);
	}
});

$(document).delegate("#addHostsModal #btn_add_hosts","click",function(){
	var selectedHosts = addHostGrid.getSelectedRows();
	if (selectedHosts.length == 0){
		$.messager.alert("消息", "请选择需要添加的主机");
		return
	}
	
	var hosts = [];
	$.each(selectedHosts,function(i,val){
		hosts.push({hostId: val.hostId, manageIp: val.manageIp, hostName: val.hostName});
	});
	$.ajax({
		url: contextPath+"/rest/hosts",
		type: "put",
		contentType: "application/json",
		data:JSON.stringify(hosts),
		success: function(ret){
			// $.messager.alert("消息", message+"成功");
			$('#addHostsModal').modal("hide");
			hostGrid.reload();
		},
		error: function(ret){
			$.messager.alert("消息", message+"失败");
		}
	});
});

function getLocalTime(nS) { 
	return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' '); 
} 


function getDateDiff(t) {
	var s = parseInt(t%60);
	var m = parseInt(t/60%60);
	var h = parseInt(t/60/60%24);
	var d = parseInt(t/60/60/24);
	var rs = "";
	if (d > 0){
		rs += (d + " 天 ");
	}
	if (h > 0){
		rs += (h + " 小时 ");
	}
	if (m > 0){
		rs += (m + " 分钟 ");
	}
	if (s > 0){
		rs += (s + " 秒 ");
	}
	if (rs == ""){
		return "1 秒";
	}
	return rs;
}

function isCIDR(ipMac){
	var cidr = ipMac.split("/");
	if (cidr.length != 2) {
		$.messager.alert("提示", "地址"+ ipMac + "不是一个合法的地址");
		return false;
	}
	return isIP(cidr[0]) && isMac(cidr[1]);
}


function isMac(mac){
    var macInt = parseInt(mac);
	if(macInt < 0 || macInt > 32){
		return false;
	}
	return true;
}

function isIP(addr){
    var part_addr=addr.split(".");
    if(part_addr.length != 4){
        return false;
    }else{
        var part;
        for(part in part_addr){
            if(isNumeric(part_addr[part])){
                if(parseInt(part_addr[part])<0 || parseInt(part_addr[part])>255){
                    return false;
                }
            }else{
                return false;
            }
        }
    }
    return true;
}


function isNumeric(str){
    if(str.length==0){
        return false;
    }
    for(var i=0;i<str.length;i++){
        if(str.charAt(i)<"0"||str.charAt(i)>"9"){
            return false;
        }
    }
    return true;
}