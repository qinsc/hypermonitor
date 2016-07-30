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
	<!-- Morris Chart Styles-->
	<link href="../assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
	<!-- Custom Styles-->
	<link href="../assets/css/custom-styles.css" rel="stylesheet" />
	<!-- Google Fonts-->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
	<%@include file="common/common.jsp"%>
</head>

<body>
	<div id="wrapper">
		<%@include file="/views/common/header.jsp"%>
		<%@include file="/views/common/menu.jsp"%>
		
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<jsp:include page="<%=target %>" flush="true" />
			</div>
		</div>
	</div>
	
	<script src="../assets/js/jquery-1.10.2.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/jquery.metisMenu.js"></script>
	
	 <script src="../assets/js/morris/raphael-2.1.0.min.js"></script>
	<script src="../assets/js/morris/morris.js"></script>
<!--	<script src="../assets/js/easypiechart.js"></script>
	<script src="../assets/js/easypiechart-data.js"></script> -->
	
	<script src="../assets/js/custom-scripts.js"></script>
</body>

</html>