<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<nav class="navbar-default navbar-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">
			<li><a id="menu_dashboard" class="active-menu" href="<%=request.getContextPath()%>/views/index.jsp?target=/dashboard/dashboard.jsp&menu=1"><i class="fa fa-dashboard"></i> 主页 </a></li>
			<li><a id="menu_host_mgr" href="<%=request.getContextPath()%>/views/index.jsp?target=/host/host_mgr.jsp&menu=2"><i class="fa fa-desktop"></i> 主机管理 </a></li>
		</ul>
	</div>
</nav>

<script src="../assets/js/jquery-1.10.2.js"></script>
<script type="text/javascript">
	var menu = <%=request.getParameter("menu")%>;
	if (menu == null){
		menu = "1";
	}
	activeMenu();
	
	function activeMenu(){
		if (menu == "1"){
			$("#menu_dashboard").addClass("active-menu");
			$("#menu_host_mgr").removeClass("active-menu");
		} else {
			$("#menu_host_mgr").addClass("active-menu");
			$("#menu_dashboard").removeClass("active-menu");
		}
	}
</script>