<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Holiday Request Management</title>
</head>
<body>
<form method="POST">

	<center>
		<h1>Holiday Request Management</h1>
		
		<a href="HolidayManagementServlet?action=filterByEmployee">View Request Filter by Employee</a>
        
	</center>
	
	<section>
	<h2>List of Holiday Request</h2>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
         <th>Request ID</th>
                <th>Employee First Name</th>
                <th>Request Status</th>
                <%-- <th>No Constraint time</th> --%>
                <th>Request Time</th>
                <%-- <th>Breaking Constraints</th> --%>
                <th>Reason</th>
                <th>Total Days</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Priority</th>
                
                <th>Action</th>
        </tr>
      </thead>
    </table>
  </div>
  
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <c:forEach var="holidayrequest" items="${listHolidayRequest}">
                <tr>
                    <td><c:out value="${holidayrequest.holidayRequestId}" /></td>
                    <td><c:out value="${holidayrequest.getEmployee().firstName}" /></td>
                    <td><c:out value="${holidayrequest.getRequestStatus().status}" /></td>
                    <%-- <td><c:out value="${holidayrequest.noConstraintTime}" /></td> --%> 
                    <td><c:out value="${holidayrequest.requestTime}" /></td>
                    <%--<td><c:out value="${holidayrequest.breakingConstraints}" /></td> --%> 
                    <td><c:out value="${holidayrequest.reason}" /></td>
                    <td><c:out value="${holidayrequest.totalDays}" /></td>
                    <td><c:out value="${holidayrequest.fromDate}" /></td>
                    <td><c:out value="${holidayrequest.toDate}" /></td>
                    <td><c:out value="${holidayrequest.priority}" /></td>
                  
                    <td>
                    <c:if test="${holidayrequest.getRequestStatus().requestStatusId != 1 && holidayrequest.getRequestStatus().requestStatusId != 2 && holidayrequest.getRequestStatus().requestStatusId != 4 }">
                    <a href="HolidayManagementServlet?action=approveRequest?holidayRequestId=<c:out value='${holidayrequest.holidayRequestId}' />">Approve</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    </c:if>
                     <c:if test="${holidayrequest.getRequestStatus().requestStatusId != 1 && holidayrequest.getRequestStatus().requestStatusId != 2 && holidayrequest.getRequestStatus().requestStatusId != 4 }">
                    	<a href="HolidayManagementServlet?action=rejectRequest?holidayRequestId=<c:out value='${holidayrequest.holidayRequestId}' />">Reject</a>
                    	</c:if>                    	
                    </td>
                </tr>
            </c:forEach>
      </tbody>
    </table>
  </div>
	
    </section>
    
    
    
    <section>
	<h2>List of Approved Holiday Request</h2>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
         <th>Request ID</th>
                <th>Employee First Name</th>
                <th>Request Status</th>
                <%-- <th>No Constraint time</th> --%>
                <th>Request Time</th>
                <%-- <th>Breaking Constraints</th> --%>
                <th>Reason</th>
                <th>Total Days</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Priority</th>
        </tr>
      </thead>
    </table>
  </div>
  
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
         <c:forEach var="holidayrequest" items="${allApprovedRequestList}">
                <tr>
                    <td><c:out value="${holidayrequest.holidayRequestId}" /></td>
                    <td><c:out value="${holidayrequest.getEmployee().firstName}" /></td>
                    <td><c:out value="${holidayrequest.getRequestStatus().status}" /></td>
                    <%-- <td><c:out value="${holidayrequest.noConstraintTime}" /></td> --%> 
                    <td><c:out value="${holidayrequest.requestTime}" /></td>
                    <%--<td><c:out value="${holidayrequest.breakingConstraints}" /></td> --%> 
                    <td><c:out value="${holidayrequest.reason}" /></td>
                    <td><c:out value="${holidayrequest.totalDays}" /></td>
                    <td><c:out value="${holidayrequest.fromDate}" /></td>
                    <td><c:out value="${holidayrequest.toDate}" /></td>
                    <td><c:out value="${holidayrequest.priority}" /></td>
                </tr>
            </c:forEach>
      </tbody>
    </table>
  </div>
	
    </section>
    
    
    
    <section>
	<h2>List of Rejected Holiday Request</h2>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
                <th>Request ID</th>
                <th>Employee First Name</th>
                <th>Request Status</th>
                <%-- <th>No Constraint time</th> --%>
                <th>Request Time</th>
                <%-- <th>Breaking Constraints</th> --%>
                <th>Reason</th>
                <th>Total Days</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Priority</th>
            </tr>
      </thead>
    </table>
  </div>
  
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
         <c:forEach var="holidayrequest" items="${allRejectedRequestList}">
                <tr>
                    <td><c:out value="${holidayrequest.holidayRequestId}" /></td>
                    <td><c:out value="${holidayrequest.getEmployee().firstName}" /></td>
                    <td><c:out value="${holidayrequest.getRequestStatus().status}" /></td>
                    <%-- <td><c:out value="${holidayrequest.noConstraintTime}" /></td> --%> 
                    <td><c:out value="${holidayrequest.requestTime}" /></td>
                    <%--<td><c:out value="${holidayrequest.breakingConstraints}" /></td> --%> 
                    <td><c:out value="${holidayrequest.reason}" /></td>
                    <td><c:out value="${holidayrequest.totalDays}" /></td>
                    <td><c:out value="${holidayrequest.fromDate}" /></td>
                    <td><c:out value="${holidayrequest.toDate}" /></td>
                    <td><c:out value="${holidayrequest.priority}" /></td>
                </tr>
            </c:forEach>
      </tbody>
    </table>
  </div>
	
    </section>
   
   
   
    <section>
	<h2>List of Cancelled Holiday Request</h2>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
                <th>Request ID</th>
                <th>Employee First Name</th>
                <th>Request Status</th>
                <%-- <th>No Constraint time</th> --%>
                <th>Request Time</th>
                <%-- <th>Breaking Constraints</th> --%>
                <th>Reason</th>
                <th>Total Days</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>Priority</th>
            </tr>
      </thead>
    </table>
  </div>
  
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
         <c:forEach var="holidayrequest" items="${allCancelledHolidayRequest}">
                <tr>
                    <td><c:out value="${holidayrequest.holidayRequestId}" /></td>
                    <td><c:out value="${holidayrequest.getEmployee().firstName}" /></td>
                    <td><c:out value="${holidayrequest.getRequestStatus().status}" /></td>
                    <%-- <td><c:out value="${holidayrequest.noConstraintTime}" /></td> --%> 
                    <td><c:out value="${holidayrequest.requestTime}" /></td>
                    <%--<td><c:out value="${holidayrequest.breakingConstraints}" /></td> --%> 
                    <td><c:out value="${holidayrequest.reason}" /></td>
                    <td><c:out value="${holidayrequest.totalDays}" /></td>
                    <td><c:out value="${holidayrequest.fromDate}" /></td>
                    <td><c:out value="${holidayrequest.toDate}" /></td>
                    <td><c:out value="${holidayrequest.priority}" /></td>
                </tr>
            </c:forEach>
      </tbody>
    </table>
  </div>
	
    </section>
    
    </form>
</body>
</html>

<style>

#filterByName {
  background-position: 10px 10px;
  background-repeat: no-repeat;
  width: 73%;
  font-size: 16px;
  padding: 12px 20px 12px 40px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}

h1{
  font-size: 30px;
  color: #fff;
  text-transform: uppercase;
  font-weight: 300;
  text-align: center;
  margin-bottom: 15px;
}

h2{
  font-size: 20px;
  color: #fff;
  font-weight: 250;
  text-align: center;
  margin-bottom: 15px;
}

table{
  width:100%;
  table-layout: fixed;
}
.tbl-header{
  background-color: rgba(255,255,255,0.3);
 }
.tbl-content{
  height:300px;
  overflow-x:auto;
  margin-top: 0px;
  border: 1px solid rgba(255,255,255,0.3);
}
th{
  padding: 20px 15px;
  text-align: left;
  font-weight: 500;
  font-size: 12px;
  color: #fff;
  text-transform: uppercase;
}
td{
  padding: 15px;
  text-align: left;
  vertical-align:middle;
  font-weight: 300;
  font-size: 12px;
  color: #fff;
  border-bottom: solid 1px rgba(255,255,255,0.1);
}


/* demo styles */

@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
body{
  background: -webkit-linear-gradient(left, #25c481, #25b7c4);
  background: linear-gradient(to right, #25c481, #25b7c4);
  font-family: 'Roboto', sans-serif;
}
section{
  margin: 50px;
}


/* follow me template */
.made-with-love {
  margin-top: 40px;
  padding: 10px;
  clear: left;
  text-align: center;
  font-size: 10px;
  font-family: arial;
  color: #fff;
}
.made-with-love i {
  font-style: normal;
  color: #F50057;
  font-size: 14px;
  position: relative;
  top: 2px;
}
.made-with-love a {
  color: #fff;
  text-decoration: none;
}
.made-with-love a:hover {
  text-decoration: underline;
}


/* for custom scrollbar for webkit browser*/

::-webkit-scrollbar {
    width: 6px;
} 
::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
} 
::-webkit-scrollbar-thumb {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
}
</style>