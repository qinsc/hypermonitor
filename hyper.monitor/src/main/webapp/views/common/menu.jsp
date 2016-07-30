<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<nav class="navbar-default navbar-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">
			<li><a class="active-menu" href="<%=request.getContextPath()%>/views/index.jsp?target=/dashboard/dashboard.jsp"><i class="fa fa-dashboard"></i> 主页 </a></li>
			<li><a href="<%=request.getContextPath()%>/views/index.jsp?target=/host/host_mgr.jsp"><i class="fa fa-desktop"></i> 主机管理 </a></li>
		</ul>
	</div>
</nav>