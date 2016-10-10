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
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="ids"> <input type="hidden"
						id="opt">
					<textarea id="text" class="form-control" style="height: 300px;"
						maxlength="200" placeholder="最多200字"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="btn_action" class="btn btn-primary">发送</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">主机管理</div>
		<div class="panel-body">
			<div class="form-inline">
				<div class="btn-group">
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown" style="width: 80px;">
							电源<span class="caret" style="margin-left: 10px;"></span>
						</button>
						<ul class="dropdown-menu" role="menu" style="min-width: 80px;">
							<li><a href="#" onclick="osLogout()">注销</a></li>
							<li><a href="#" onclick="osReboot()">重启</a></li>
							<li><a href="#" onclick="osShutdown()">关机</a></li>
						</ul> 
					</div>
					
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown" style="width: 80px;">
							主机<span class="caret" style="margin-left: 10px;"></span>
						</button>
						<ul class="dropdown-menu" role="menu" style="min-width: 80px;">
							<li><a href="#" onclick="addHosts()">添加</a></li>
							<li><a href="#" onclick="removeHosts()">移除</a></li>
						</ul>
					</div>
					
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown" style="width: 90px;">
							主机组<span class="caret" style="margin-left: 10px;"></span>
						</button>
						<ul class="dropdown-menu" role="menu" style="min-width: 90px;">
							<li><a href="#" onclick="editGroup()">修改</a></li>
							<li><a href="#" onclick="mgrGroup()">管理</a></li>
						</ul>
					</div>
					
					<button type="button" class="btn btn-default" onclick="sendMsg()">发送消息</button>
				</div>
	
				<div id="hostTable_filter" class="input-group" style="width: 217px; float:right;">
					<input type="search" id="searchText" class="form-control" placeholder="筛选">
					<span class="input-group-addon">
						<i class="fa fa-search" style="float: right;"></i>
					</span>
				</div>
			</div>

			<div class="table-responsive" style="min-height: 400px; width: 100%;">
				<table id="hostTable"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th width="40px"><input type="checkbox"
								class="group-checkable"></th>
							<th width="80px">状态</th>
							<th width="150px">主机名</th>
							<th width="150px">管理IP</th>
							<th width="80px">远程控制</th>
							<th width="120px">系统</th>
							<th width="200px">主机组</th>
							<th>备注</th>
							<th width="60px">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>
				<div style="height: 140px;"></div>
			</div>
		</div>
	</div>
</div>

<%@include file="host_add.jsp"%>
<%@include file="host_detail.jsp"%>
<%@include file="host_group_edit.jsp"%>

<script src="<%=contextPath%>/assets/js/dt.js"></script>
<script src="<%=contextPath%>/views/host/js/host_mgr.js"></script>
<script type="text/javascript">
	$(function() {
		var contextPath = "<%=contextPath%>";
		initHostDatatables(contextPath, "hostTable");
		initAddHostDatatables("addHostTable");
		initHostGroupEditDatatables();
	})
</script>

