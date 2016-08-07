<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<nav class="navbar navbar-default top-navbar" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".sidebar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<div class="navbar-brand" id="navbar" href="#" style="cursor: hand">
			<div>
				<i class="fa"></i> <strong>Hyper Monitor </strong>
			</div>
		</div>
	</div>

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown">
			<a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#"><i class="fa fa-gear fa-fw"></i> 用户配置</a></li>
				<li class="divider"></li>
				<li><a href="<%=request.getContextPath()%>/rest/users/logout"><i
						class="fa fa-sign-out fa-fw"></i> 登出 </a></li>
			</ul>
		</li>
	</ul>
</nav>