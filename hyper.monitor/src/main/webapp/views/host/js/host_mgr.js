var option = {
	dom: 'fBrtip',
	showCheckBox:true,
	buttons: [ {
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
  "columns" : [ {
	                "data":"selected", render: function ( data, type, row ) {
	                    if ( type === 'display' ) {
	                        return '<input type="checkbox" class="editor-active">';
	                    }
	                    return data;
	                },
	                className: "dt-body-center"
	           },
               {"data" : "online"},  
               {"data" : "hostName"},  
               {"data" : "manageIp"},  
               {"data" : "os"},  
               {"data" : "desc"},  
               { render: render_host_operations} 
               ],
"columnDefs" : [ {
		"targets" : 0,
		'orderable': false,
		"render" : function(data, type, full, meta) {
			if (data) {
				return '<input type="checkbox" checked="checked" >';
			} else {
				return '<input type="checkbox">';
			}
		}
	}, {
		"targets" : 1,
		"render" : function(data, type, full, meta) {
			if (data == 1) {
				return '<h style="color:green;">在线<h>';
			} else {
				return '<h style="color: red;">离线<h>';
			}
		}
	} ],
	idField:"hostId",
};

function render_host_operations(data, type, row){
	// alert("row = " + JSON.stringify(row))
	var OperationHtml = 
		'<div class="btn-group">' +
		'	<button data-toggle="dropdown" class="btn btn-info dropdown-toggle" aria-expanded="false"> 操作 <span class="caret"></span> </button>' +
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
function initHostDatatables(tableId){
	alert("init table ...");
	hostGrid.init({
        src: $("#"+tableId),
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
         			if (data == 1) {
        				return '<h style="color:green;">在线<h>';
        			} else {
        				return '<h style="color: red;">离线<h>';
        			}
        		 }},  
                 {"data" : "hostName"},  
                 {"data" : "manageIp"},  
                 {"data" : "os"},  
                 {"data" : "desc"},  
                 { render: render_host_operations} 
             ]
        }
    });
}

$(document).delegate(".btn.btn-primary","click",function(){
    $("#myModal #myModalLabel").text("发送消息中 ....");
    // ajax
    $('#myModal').modal({keyboard:false,show:true})
})

function osLogout(row) {
	alert("osLogout = " + JSON.stringify(row));
}

function osShutdown(row) {
	alert("osShutdown");
}

function osReboot(row) {
	alert("osReboot");
}

function sendMsg(row) {
	$("#myModal #myModalLabel").text("给主机发送消息"); 
	/* $("#myModal #text").text("发送消息");  */
	$("#myModal #text").attr("placeholder","长度不超过200字");
	
    $('#myModal').modal({keyboard:false,show:true})
}

function addHosts(row) {
	alert("addHosts");
}

function addHostDesc(row) {
	$("#myModal #myModalLabel").text("为主机添加描述信息"); 
	$("#myModal #text").attr("placeholder","长度不超过500字");
	
    $('#myModal').modal({keyboard:false,show:true})
}

function loadHosts() {
	$("#hostTable").DataTable().ajax.url("<%=contextPath%>/rest/hosts/").load();
}