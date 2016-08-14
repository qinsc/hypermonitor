<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" type="text/css" href="assets/css/signin.css" />

<div class="row">
	<div class="modal fade" id="addHostsModal" tabindex="-1" role="dialog"
		aria-labelledby="addHostsModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="addHostsModalLabel"></h4>
				</div>
				<div class="modal-body">
					<form class="form-signin" method="post" id="scanHosts"
						action="<%=contextPath%>/rest/hosts/scan">
						<div class="row">
							<div class="col-lg-9" style="padding-left: 0px; padding-right: 4px;">
								<div>
									<label for="startIP" class="sr-only">起始地址</label>
									<input type="text" id="startIP" name="startIP" class="form-control" placeholder="起始地址" required autofocus>
								</div>
								<div style="margin-top: 4px;">
									<label for="endIP" class="sr-only">起始地址</label>
									<input type="text" id="endIP" name="endIP" class="form-control" placeholder="结束地址" required autofocus>	
								</div>
							</div>
							<div class="col-lg-3"  style="height: 72px; padding-right: 2px; padding-left: 4px;">
								<button class="btn btn-primary btn-block"  style="height: 68px;" type="button" onclick="hostScan()">扫 描</button>
							</div>
						</div>
					</form>
					<div class="table-responsive" style="min-height: 400px;">
						<table id="addHostTable"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th width="10px;"><input type="checkbox" class="group-checkable"></th>
									<th width="45%">主机名</th>
									<th width="45%">管理IP</th>
								</tr>
							</thead>
							<tbody id="tbody">
							</tbody>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="btn_action" class="btn btn-primary">添加</button>
				</div>
			</div>
		</div>
	</div>
</div>
