<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Holiday Request Management</title>
</head>
<body>
	<center>
		<h1>Holiday Request Management</h1>
        
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Holiday Request</h2></caption>
            <tr>
                <th>Request ID</th>
                <th>Employee First Name</th>
                <th>Request Status ID</th>
                <th>No Constraint time</th>
                <th>Request Time</th>
                <th>Breaking Constraints</th>
                <th>Reason</th>
                <th>Total Days</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Priority</th>
                
                <th>Action</th>
            </tr>
            <c:forEach var="holidayrequest" items="${listHolidayRequest}">
                <tr>
                    <td><c:out value="${holidayrequest.holidayRequestId}" /></td>
                    <td><c:out value="${holidayrequest.getEmployee().firstName}" /></td>
                    <td><c:out value="${holidayrequest.getRequestStatus().status}" /></td>
                    <td><c:out value="${holidayrequest.noConstraintTime}" /></td>
                    <td><c:out value="${holidayrequest.requestTime}" /></td>
                    <td><c:out value="${holidayrequest.breakingConstraints}" /></td>
                    <td><c:out value="${holidayrequest.reason}" /></td>
                    <td><c:out value="${holidayrequest.totalDays}" /></td>
                    <td><c:out value="${holidayrequest.fromDate}" /></td>
                    <td><c:out value="${holidayrequest.toDate}" /></td>
                    <td><c:out value="${holidayrequest.priority}" /></td>
                  
                    <td>
                    	<a href="HolidayManagementServlet?action=approveRequest?holidayRequestId=<c:out value='${holidayrequest.holidayRequestId}' />">Approve</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="HolidayManagementServlet?action=rejectRequest?holidayRequestId=<c:out value='${holidayrequest.holidayRequestId}' />">Reject</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
