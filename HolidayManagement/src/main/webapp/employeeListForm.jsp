<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>User Management Application</title>
</head>
<body>
	<center>
		<h1>User Management</h1>
        <h2>
        	<a href="HolidayManagementServlet?action=addEmployee">Add New User</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Department</th>
                <th>Role</th>
                <th>Date of Birth</th>
                <th>Date of Joining</th>
                <th>Phone No</th>
                <th>Email</th>
                <th>Holidays Remaining</th>
                
                <th>Action</th>
            </tr>
            <c:forEach var="employee" items="${listUser}">
                <tr>
                    <td><c:out value="${employee.employeeId}" /></td>
                    <td><c:out value="${employee.firstName}" /></td>
                    <td><c:out value="${employee.lastName}" /></td>
                    <td><c:out value="${employee.getDepartment().name}" /></td>
                    <td><c:out value="${employee.getRole().name}" /></td>
                    <td><c:out value="${employee.dob}" /></td>
                    <td><c:out value="${employee.dateOfJoining}" /></td>
                    <td><c:out value="${employee.phoneNo}" /></td>
                    <td><c:out value="${employee.email}" /></td>
                    <td><c:out value="${employee.holidaysRemaining}" /></td>
                  
                    <td>
                    	<a href="HolidayManagementServlet?action=editUsers?employeeId=<c:out value='${employee.employeeId}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="HolidayManagementServlet?action=deleteUser?employeeId=<c:out value='${employee.employeeId}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
