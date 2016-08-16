<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<style type="text/css">
.dl-horizontal dt{
	width: 120px;
}

.dl-horizontal dd{
	margin-left: 140px;
}

legend {
	font-size: 18px;
}
</style>

<div class="row">
	<div class="modal fade" id="detailHostsModal" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="detailHostsModalLabel"></h4>
				</div>
				<div class="modal-body">
					<div style="overflow-y:auto; overflow-x:hidden; height: 500px; width: 570px;">
						<fieldset>
							<legend>基本信息</legend> 
					    	<dl class="dl-horizontal">
								  <dt>主机名</dt>
								  <dd id="hostName"></dd>							  
								  <dt>描述</dt>
								  <dd ><textarea id="hostDesc" rows=4 style="border: 0px; width: 410px; outline:none; resize: none;" readonly></textarea></dd>							  
							</dl>
						</fieldset>
						
						<fieldset>
							<legend>运行信息</legend> 
							<dl class="dl-horizontal">
								 <dt>开机时间</dt>
								 <dd id="bootTime"></dd>
								 <dt>已开机</dt>
								 <dd id="upTime"></dd>		
							 </dl>		
						<fieldset>
						
						<fieldset>
							<legend>系统</legend> 
							<dl class="dl-horizontal">
							  <dt>系统</dt>
							  <dd id="os"></dd>
							  <dt>系统平台</dt>
							  <dd id="osPlatform"></dd>										  
							  <dt>系统家族</dt>
							  <dd id="osPlatformFamily"></dd>										  
							  <dt>系统版本号</dt>
							  <dd id="osPlatformVersion"></dd>										  
							</dl>
						</fieldset>
						
						<fieldset>
							<legend>CPU</legend> 
					    	<dl class="dl-horizontal">
							  <dt>cpu核数</dt>
							  <dd id="cpuCores"></dd>							  
							  <dt>cpu型号</dt>
							  <dd id="cpuModelName"></dd>							  
							  <dt>cpu频率</dt>
							  <dd id="cpuMhz"></dd>	
							  <dt>cpu使用率</dt>
							  <dd id="cpuUsage"></dd>	
							</dl>
						</fieldset>
						
						<fieldset>
							<legend>内存</legend> 
					    	<dl class="dl-horizontal">
							  <dt>内存总量</dt>
						 	  <dd id="memSize"></dd>							  
							  <dt>已用内存</dt>
						 	  <dd id="memUsed"></dd>							  
							  <dt>内存使用率</dt>
						 	  <dd id="memUsage"></dd>							  
							</dl>
						</fieldset>
						
						<fieldset id="nics">
							<legend>网卡</legend> 
						</fieldset>
						
						<fieldset id="disks">
							<legend>硬盘</legend> 
						</fieldset>
					</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</div>
