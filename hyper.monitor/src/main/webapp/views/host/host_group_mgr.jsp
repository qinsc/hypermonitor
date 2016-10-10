<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="row">
	<div class="modal fade" id="hostGroupModal" tabindex="-1" role="dialog"
		aria-labelledby="hostGroupModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="hostGroupModalLabel"></h4>
				</div>
				<div class="modal-body">
					<div class="table-responsive" style="min-height: 400px;">
						<table id="hostGroupTable"
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
					
					<div class="row">
						<div class="col-lg-9 col-md-9 col-xs-9" style="padding-left: 0px; padding-right: 4px;">
							<div>
								<label for="startIp" class="sr-only">起始地址</label>
								<input type="text" id="startIp" name="startIp" class="form-control" placeholder="起始地址，例如 192.168.1.21/24" required autofocus>
							</div>
							<div style="margin-top: 4px;">
								<label for="endIp" class="sr-only">起始地址</label>
								<input type="text" id="endIp" name="endIp" class="form-control" placeholder="结束地址，例如 192.168.1.46/24" required autofocus>	
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-xs-3"  style="height: 72px; padding-right: 2px; padding-left: 4px;">
							<button id="btn_scan" class="btn btn-primary btn-block"  style="height: 68px;" type="button">扫 描</button>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="btn_add_hosts" class="btn btn-primary">添加</button>
				</div>
			</div>
		</div>
	</div>
</div>
