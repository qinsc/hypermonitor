<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<style type="text/css">
table.dataTable tbody tr.selected {
    background-color: #B0BED9;
}
</style>

<div class="row">
	<div class="modal fade" id="hostGroupMgrModal" tabindex="-1" role="dialog"
		aria-labelledby="hostGroupMgrModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="hostGroupMgrModalLabel">主机组管理</h4>
				</div>
				<div class="modal-body">
					<!-- <div style="margin-top: -20px; padding-top: 16px;">
						<div class="btn-group" style="display: inline-block;">
							<button type="button" id="btn_add_host_group" class="btn btn-default">添加</button>
							<button type="button" id="btn_delete_host_group" class="btn btn-default">删除</button>
						</div>
						<div style="display: inline-block; float: right;">
							<h5>主机组</h5>
							<div id="hostGroup_filter" class="input-group" style="width: 217px;">
								<input type="search" id="searchText" class="form-control" placeholder="筛选">
								<span class="input-group-addon">
									<i class="fa fa-search" style="float: right;"></i>
								</span>
							</div>
						</div>
					</div> -->
					<div id= "tableDiv" class="table-responsive" style="min-height: 400px; width: 100%;">
						<table id="hostGroupMgrTable"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th width="40px"><input type="checkbox" class="group-checkable"></th>
									<th width="35%">主机组名称</th>
									<th>描述</th>
									<th width="50px;">修改</th>
								</tr>
							</thead>
							<tbody id="tbody">
							</tbody>
						</table>
					</div>
					<div id="formDiv" style="display: none;">
						<form role="form">
						  <div class="hide" id="groupId"></div>
						  <div class="form-group">
						    <label for="name">主机组名称</label>
						    <input type="text" class="form-control" id="groupName" placeholder="请输入主机组的名称">
						  </div>
						  <div class="form-group">
						    <label for="name">描述</label>
						    <textarea class="form-control" rows="6" id="groupDesc" placeholder="请输入主机组的描述"></textarea>
						  </div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="btn_dismiss" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-default" id="btn_cancel" style="display: none;">取消</button>
					<button type="button" class="btn btn-primary" id="btn_action" style="display: none;">添加</button>
				</div>
			</div>
		</div>
	</div>
</div>
