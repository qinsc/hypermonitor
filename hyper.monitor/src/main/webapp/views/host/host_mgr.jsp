<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

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
							<th width="20%">IP地址</th>
							<th width="15%">系统</th>
							<th width="10%">主机组</th>
							<th width="15%">标签</th>
							<th width="10%" >操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
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
										<li><a href="#">注销</a></li>
										<li><a href="#">关机</a></li>
										<li><a href="#">发送消息</a></li>
									</ul>
								</div>
							</td>
						</tr>
						<tr  >
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
										<li><a href="#">注销</a></li>
										<li><a href="#">关机</a></li>
										<li><a href="#">发送消息</a></li>
									</ul>
								</div>
							</td>
						</tr>
						<tr  >
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
										<li><a href="#">注销</a></li>
										<li><a href="#">关机</a></li>
										<li><a href="#">发送消息</a></li>
									</ul>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var option = {
		"columnDefs": 
		[
			{
            	"render": function(data, type, row) {
                	return data + ' (' + row[3] + ')';
            	},
            	"targets": 2
	        },
	        {
	            "visible": false,
	            "targets": [3]
	        }
	 	]
	 };
	$(document).ready(function () {
		initDatatables("hostTable");
	});
	
	function addRow(){
		
	}
</script>

