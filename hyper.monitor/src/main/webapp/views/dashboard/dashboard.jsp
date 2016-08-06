<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="row">
	<div class="col-md-3 col-sm-12 col-xs-12">
		<div
			class="panel panel-primary text-center no-boder bg-color-green green">
			<div class="panel-left pull-left green">
				<i class="fa fa-bar-chart-o fa-5x"></i>

			</div>
			<div class="panel-right pull-right">
				<h3>8,457</h3>
				<strong> 主机总数 </strong>
			</div>
		</div>
	</div>
	<div class="col-md-3 col-sm-12 col-xs-12">
		<div
			class="panel panel-primary text-center no-boder bg-color-blue blue">
			<div class="panel-left pull-left blue">
				<i class="fa fa-shopping-cart fa-5x"></i>
			</div>

			<div class="panel-right pull-right">
				<h3>52,160</h3>
				<strong> 在线主机数 </strong>

			</div>
		</div>
	</div>
	<div class="col-md-3 col-sm-12 col-xs-12">
		<div class="panel panel-primary text-center no-boder bg-color-red red">
			<div class="panel-left pull-left red">
				<i class="fa fa fa-comments fa-5x"></i>

			</div>
			<div class="panel-right pull-right">
				<h3>15,823</h3>
				<strong> 主机组数 </strong>

			</div>
		</div>
	</div>
	<div class="col-md-3 col-sm-12 col-xs-12">
		<div
			class="panel panel-primary text-center no-boder bg-color-brown brown">
			<div class="panel-left pull-left brown">
				<i class="fa fa-users fa-5x"></i>

			</div>
			<div class="panel-right pull-right">
				<h3>36,752</h3>
				<strong>主机标签数</strong>

			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="panel panel-default">
			<div class="panel-heading"> 最近事件 </div>
			<div class="panel-body">
				<div class="table-responsive">
					<table id="eventTable" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th width="5%">序号</th>
								<th width="10%">时间</th>
								<th width="15%">主机</th>
								<th width="15%">IP地址</th>
								<th width="50%">事件</th>
								<th width="5%">级别</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>John</td>
								<td>Doe</td>
								<td>John15482</td>
								<td>name@site.com</td>
								<td>信息</td>
							</tr>
							<tr>
								<td>2</td>
								<td>Kimsila</td>
								<td>Marriye</td>
								<td>Kim1425</td>
								<td>name@site.com</td>
								<td>信息</td>
							</tr>
							<tr>
								<td>3</td>
								<td>Rossye</td>
								<td>Nermal</td>
								<td>Rossy1245</td>
								<td>name@site.com</td>
								<td>信息</td>
							</tr>
							<tr>
								<td>4</td>
								<td>Richard</td>
								<td>Orieal</td>
								<td>Rich5685</td>
								<td>name@site.com</td>
								<td>信息</td>
							</tr>
							<tr>
								<td>5</td>
								<td>Jacob</td>
								<td>Hielsar</td>
								<td>Jac4587</td>
								<td>name@site.com</td>
								<td>信息</td>
							</tr>
							<tr>
								<td>6</td>
								<td>Wrapel</td>
								<td>Dere</td>
								<td>Wrap4585</td>
								<td>name@site.com</td>
								<td>信息</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function () {
		initDatatables("eventTable");
	});
</script>
