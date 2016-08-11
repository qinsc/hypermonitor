<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<% 
	String target = request.getParameter("target");
	target = (null == target || 0 == target.trim().length()) ? "/views/dashboard/dashboard.jsp" : "/views/" + target;
%>


<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Home</title>
	<!-- Bootstrap Styles-->
	<link href="../assets/css/bootstrap.css" rel="stylesheet" />
	<!-- FontAwesome Styles-->
	<link href="../assets/css/font-awesome.css" rel="stylesheet" />
	<!-- Custom Styles-->
	<link href="../assets/css/custom-styles.css" rel="stylesheet" />
	<!-- Datatables  Styles -->
	<link href="../assets/css/dataTables.bootstrap.css" rel="stylesheet" />
	<!-- datatables button -->
	<link href="../assets/css/buttons.bootstrap.min.css" rel="stylesheet" />
	<link href="../assets/css/buttons.dataTables.min.css rel="stylesheet" />
	
	<%@include file="common/common.jsp"%>
</head>

<body>
	<div id="wrapper">
		<%@include file="/views/common/header.jsp"%>
		<%@include file="/views/common/menu.jsp"%>
		
		<div id="page-wrapper">
			<div id="page-inner">
				<jsp:include page="<%=target %>" flush="true" />
			</div>
		</div>
	</div>
	
	<script src="../assets/js/jquery.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/custom-scripts.js"></script>
	<script src="../assets/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="../assets/js/dataTables/dataTables.bootstrap.min.js"></script>
	<script src="../assets/js/dataTables/extensions/Buttons/js/dataTables.buttons.min.js"></script>
	<script src="../assets/js/dataTables/extensions/Buttons/js/buttons.bootstrap.min.js"></script>
</body>

</html>