<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../common/common.jsp"%>

<div class="row">
 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">向主机发送消息</h4>
            </div>
            <div class="modal-body">
                <textarea class="form-control"></textarea>
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
			<div class="table-responsive">
				<table id="hostTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<th width="5%">
								<input type="checkbox"/ id="headSelect">
							</th>
							<th width="5%">状态</th>
							<th width="20%">主机名</th>
							<th width="20%">管理IP</th>
							<th width="15%">系统</th>
							<th width="10%">CPU使用率</th>
							<th width="15%">内存使用率</th>
							<th width="10%" >操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr>
							<td>
								<input type="checkbox"/ id="rowSelect">
							</td>
							<td style="color: red">离线</td>
							<td>host1</td>
							<td>192.168.99.10.1</td>
							<td>windows7</td>
							<td>group1</td>
							<td>tag1</td>
							<td>
								<div class="btn-group">
									<button data-toggle="dropdown"
										class="btn btn-info dropdown-toggle" aria-expanded="false">
										操作 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="javascript:osLogout()">注销</a></li>
										<li><a href="javascript:osShutdown()">关机</a></li>
										<li><a href="javascript:sendMsg()">发送消息</a></li>
									</ul>
								</div>
							</td>
						</tr> -->
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function heredoc(fn) {
	    return fn.toString().split('\n').slice(1,-1).join('\n') + '\n'
	}

	var operations = heredoc(function(){/*
		<div class="btn-group">
			<button data-toggle="dropdown"
				class="btn btn-info dropdown-toggle" aria-expanded="false">
				操作 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li><a href="javascript:osLogout()">注销</a></li>
				<li><a href="javascript:osShutdown()">关机</a></li>
				<li><a href="javascript:sendMsg()">发送消息</a></li>
			</ul>
		</div>
	*/});

	var option = {
		dom: 'fBrtip',
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
		          }
		      ],
		      "columns" : [
		                   {"data" : "selected"},  
		                   {"data" : "online"},  
		                   {"data" : "hostName"},  
		                   {"data" : "manageIp"},  
		                   {"data" : "os"},  
		                   {"data" : "cpuUsage"},  
		                   {"data" : "memUsage"},  
		                   {"data" : function (e) {
		                	   return operations;
		                   	}
		                   }
		                   ],
             "columnDefs": [ 
                           {
                        	   "targets": 0,
   			          	       "render": function ( data, type, full, meta ) {
   			          	    	   if (data){
   			          	    		   return '<input type="checkbox" checked="checked" >';
   			          	    	   } else {
   			          	    			return '<input type="checkbox">';
   			          	    	   }
   			          	       }
                           },
			               {
			          	    "targets": 1,
			          	    "render": function ( data, type, full, meta ) {
			          	    	if (data == 1){
			          	    		return '<h style="color:green;">在线<h>';
			          	    	} else {
			          	    		return '<h style="color: red;">离线<h>';
			          	    	}
			          	      }
			          	  	}
			               ]
	};
	
	$(document).ready(function () {
		initDatatables("hostTable", option);
		loadHosts();
	});
	
	function osLogout(){
		 alert("osLogout");
	}
	
	function osShutdown(){
		 alert("osShutdown");
	}
	
	function sendMsg(){
		 alert("sendMsg");
	}
	
	function addHosts(){
		 alert("addHosts");
	}
	
	function loadHosts(){
		$("#hostTable").DataTable().ajax.url("<%=contextPath%>/rest/hosts/").load();
	}
</script>

