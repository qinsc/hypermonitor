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
					<input type="hidden" id="ids" >
					<input type="hidden" id="opt" >
					<textarea id="text" class="form-control" style="height: 300px;" maxlength="200" placeholder="最多200字"></textarea>
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
			<div class="table-responsive" style="min-height: 400px;">
				<table id="hostTable"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th width="40px"><input type="checkbox" class="group-checkable"></th>
							<th width="80px">状态</th>
							<th width="150px">主机名</th>
							<th width="150px">管理IP</th>
							<th width="80px">远程控制</th>
							<th width="120px">系统</th>
							<th >备注</th>
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

<script src="<%=contextPath%>/assets/js/dt.js"></script> 
<script src="<%=contextPath%>/views/host/js/host_mgr.js"></script>
<script type="text/javascript">
	$(function() {
		var contextPath = "<%=contextPath%>";
		initHostDatatables(contextPath, "hostTable"); 
		initAddHostDatatables("addHostTable");
	})
</script>

