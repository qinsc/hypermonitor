<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<style type="text/css">
table.dataTable tbody tr.selected {
    background-color: #B0BED9;
}
</style>

<div class="row">
	<div class="modal fade" id="hostGroupEditModal" tabindex="-1" role="dialog"
		aria-labelledby="hostGroupEditModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="hostGroupEditModalLabel">修改主机组</h4>
				</div>
				<div class="modal-body">
					<div>
						<h5>请选择需要修改为的主机组</h5>
					</div>
					<div class="table-responsive" style="min-height: 400px;">
						<table id="hostGroupEditTable"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th width="35%">主机组名称</th>
									<th width="65%">描述</th>
								</tr>
							</thead>
							<tbody id="tbody">
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="btn_add_hosts_group" class="btn btn-primary">修改</button>
				</div>
			</div>
		</div>
	</div>
</div>
