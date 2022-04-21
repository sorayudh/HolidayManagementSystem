<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="model.Employee" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Filter by Employee</title>
</head>
<body>


	<form action="filterByEmployee.jsp" method = "GET">
		
		Select an Employee:&nbsp; <select name="cbxEmployee" style="width: 200px">
			<c:forEach items="${Employeelist}" var="employee">
				<option value="${employee.employeeId}">${employee.firstName}</option>
			</c:forEach>
		</select>
		<br/>
		
		<br/>
		<input type = "submit" value = "Select" />
	</form>
	<%
		if (request.getParameter("cbxEmployee") != null) {
			response.sendRedirect("HolidayManagementServlet?action=viewRequestByEmployee" + "&employeeID=" + 
									request.getParameter("cbxEmployee"));
	}
	%>
	
	<%
	@SuppressWarnings("unchecked")
	List<Employee>Employeelist = (List<Employee>) session.getAttribute("Employeelist");
	
	%>


</body>
</html>