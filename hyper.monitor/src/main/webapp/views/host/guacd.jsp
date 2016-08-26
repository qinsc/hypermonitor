<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />

<%@include file="../common/common.jsp"%>


<% 
	String hostIp = request.getParameter("hostIp");
	String protocol = request.getParameter("protocol");
%>

</head>

<body>

	<!-- Display -->
	<div id="display"></div>

	<!-- Guacamole JavaScript API -->
	<script src=../../assets/js/guacd.js></script>

	<!-- Init -->
	<script type="text/javascript">
		/* <![CDATA[ */
		var hostIp = "<%=hostIp%>";
		var protocol = "<%=protocol%>";
		var contextPath = "<%=contextPath%>";
		
		// Get display div from document
		var display = document.getElementById("display");

		// Instantiate client, using an HTTP tunnel for communications.
		var guac = new Guacamole.Client(new Guacamole.HTTPTunnel(contextPath + "/servlet/guacd"));

		// Add client to display div
		display.appendChild(guac.getDisplay().getElement());

		// Error handler
		guac.onerror = function(error) {
			alert("Error: " + JSON.stringify(error));
		};
		
		// Connect
		guac.connect("connectionInfo=" + protocol+":"+hostIp);

		// Disconnect on close
		window.onunload = function() {
			guac.disconnect();
		}

		// Mouse
		var mouse = new Guacamole.Mouse(guac.getDisplay().getElement());

		mouse.onmousedown = mouse.onmouseup = mouse.onmousemove = function(
				mouseState) {
			guac.sendMouseState(mouseState);
		};

		// Keyboard
		var keyboard = new Guacamole.Keyboard(document);

		keyboard.onkeydown = function(keysym) {
			guac.sendKeyEvent(1, keysym);
		};

		keyboard.onkeyup = function(keysym) {
			guac.sendKeyEvent(0, keysym);
		};

		/* ]]> */
	</script>

</body>

</html>
