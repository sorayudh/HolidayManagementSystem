<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Submit a Holiday Request</title>
</head>
<body>

<h2>Submit a Holiday Request</h2>
	<br/>
	<form method = "GET">
		Reason: <input type = "text" name = "reason"><br/><br/>
		From Date: <input type = "date" name = "fromdate"><br/><br/>
		To Date: <input type = "date" name = "todate"><br/><br/>
		
		<input type = "submit" value = "Submit" />
	</form>
	
	<%
	if (request.getParameter("reason") != null)
	{
		RequestDispatcher rd = request.getRequestDispatcher("HolidayManagementServlet?action=submitRequest");
		request.setAttribute("action", "submitRequest");
		request.setAttribute("reason", request.getParameter("reason"));
		request.setAttribute("fromdate", request.getParameter("fromdate"));
		request.setAttribute("todate", request.getParameter("todate"));
		rd.forward(request, response);
		
	}
	%>


</body>
</html>