<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="model.Department" %>
<%@ page import="model.Role" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add a New Employee</title>
</head>
<body>

	<h2>Add a New Employee</h2>
	<br/>
	<form action="addemployee.jsp" method = "GET">
		
		Select Department:&nbsp; <select name="cbxDepartment" style="width: 200px">
			<c:forEach items="${Departmentlist}" var="department">
				<option value="${department.departmentId}">${department.name}</option>
			</c:forEach>
		</select>
		<br/><br/>
		Select Role:&nbsp; <select name="cbxRole" style="width: 200px">
			<c:forEach items="${Rolelist}" var="role">
				<option value="${role.roleId}">${role.name}</option>
			</c:forEach>
		</select>
		<br/><br/>
		First Name: <input type = "text" name = "firstName"><br/><br/>
		Last Name: <input type = "text" name = "lastName"><br/><br/>
		Date of Birth: <input type = "date" name = "dob"><br/><br/>
		Phone Number: <input type = "number" name = "employeePhone"><br/><br/>
		Email: <input type = "text" name = "employeeEmail"><br/><br/>
		Password: <input type = "password" name = "employeePwd"><br/><br/>
		
		<br/>
		<input type = "submit" value = "Add" />
	</form>
	
	<%
		if (request.getParameter("cbxDepartment") != null && request.getParameter("cbxRole") != null) {
			response.sendRedirect("HolidayManagementServlet?action=addEmployeeWithDetail" + "&deparmentID=" + 
									request.getParameter("cbxDepartment") + 
									"&roleID=" + request.getParameter("cbxRole") +
									"&firstName=" + request.getParameter("firstName") +
									"&lastName=" + request.getParameter("lastName") +
									"&dob=" + request.getParameter("dob") +
									"&employeePhone=" + request.getParameter("employeePhone") +
									"&employeeEmail=" + request.getParameter("employeeEmail") +
									"&employeePwd=" + request.getParameter("employeePwd"));
	}
	%>
	
	<%
	@SuppressWarnings("unchecked")
	List<Department>Departmentlist = (List<Department>) session.getAttribute("Departmentlist");
	List<Role>Rolelist = (List<Role>) session.getAttribute("Rolelist");
	
	%>

</body>
</html>