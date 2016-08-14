<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../common/common.jsp"%>

<div class="row">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">向主机发送消息</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="Ids" id="Ids" >
					<textarea id="text" class="form-control" style="height: 300px;" maxlength="200" placeholder="最多200字"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">发送消息</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">主机管理</div>
		<div class="panel-body">
			<div class="table-responsive" style="min-height: 400px;">
				<table id="hostTable"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th width="5%"><input type="checkbox" class="group-checkable"></th>
							<th width="5%">状态</th>
							<th width="15%">主机名</th>
							<th width="15%">管理IP</th>
							<th width="15%">系统</th>
							<th width="35%">备注</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>
				<div style="height: 200px;"></div>
			</div>
		</div>
	</div>
</div>

<script src="<%=contextPath%>/assets/js/dt.js"></script> 
<script src="<%=contextPath%>/views/host/js/host_mgr.js"></script>
<script type="text/javascript">
	var contextPath = "<%=contextPath%>";
	$(function() {
		// initDatatables("hostTable", option);
		initHostDatatables(contextPath, "hostTable"); 
	})
	
	/* var option = {
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
	         {"data":"check", "render" : function(data, type, full, meta) {return '<input type="checkbox" checked="checked"></th>'}},
	         {"data" : "online", "render" : function(data, type, full, meta) {
	        	 return data == 1?'<h style="color:green;">在线<h>':'<h style="color: red;">离线<h>';
			 }},  
	         {"data" : "hostName"},  
	         {"data" : "manageIp"},  
	         {"data" : "os"},  
	         {"data" : "desc"},  
	         { render: render_host_operations} 
	     ]
     };
	
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
	
	$(function() {
		initDatatables("hostTable", option);
	}) */
</script>

