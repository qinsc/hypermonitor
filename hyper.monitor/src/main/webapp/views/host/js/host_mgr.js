
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
function initHostDatatables(contextPath, tableId){
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
		            	  osShutdown();
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

$(document).delegate(".btn.btn-primary","click",function(){
    $("#myModal #myModalLabel").text("发送消息中 ....");
    // ajax
    $('#myModal').modal({keyboard:false,show:true})
})

function osLogout(row) {
	if(row){
		var host = hostGrid.getRowData(row.id);
		alert("host = " + JSON.stringify(host));
	} else {
		alert(hostGrid.getSelectedRowsCount());
	}
}

function osShutdown(row) {
	alert("osShutdown");
}

function osReboot(row) {
	alert("osReboot");
}

function sendMsg(row) {
	debugger;
	$("#myModal #myModalLabel").text("给主机发送消息"); 
	$("#myModal #btn_action").html("发送"); 
	$("#myModal #text").attr("placeholder","长度不超过200字");
	
    $('#myModal').modal({keyboard:false,show:true})
}

function addHosts(row) {
	alert("addHosts");
}

function addHostDesc(row) {
	$("#myModal #myModalLabel").text("为主机添加描述信息"); 
	$("#myModal #btn_action").html("添加"); 
	$("#myModal #text").attr("placeholder","长度不超过500字");
	
    $('#myModal').modal({keyboard:false,show:true})
}
