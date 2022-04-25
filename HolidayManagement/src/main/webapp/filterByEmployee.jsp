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
		<input type="text" id="filterByName" name="filterByName"
		placeholder="Search for names.." title="Type in a name"/>
		
		
		<br/>
		Filter by Date: <input type = "date" name = "dateOfWorking">
		
		<br/>
		
		<input type = "submit" value = "Select" />
	</form>
	<%
		if (request.getParameter("filterByName") != null || request.getParameter("dateOfWorking") != null)
		{
			if(request.getParameter("filterByName") != null && (request.getParameter("dateOfWorking") == null || request.getParameter("dateOfWorking") == "")){
				RequestDispatcher rd = request.getRequestDispatcher("HolidayManagementServlet?action=filterName");
				request.setAttribute("action", "filterName");
				request.setAttribute("filterByName", request.getParameter("filterByName"));
				rd.forward(request, response);
			}
			else if(request.getParameter("filterByName") == null && (request.getParameter("dateOfWorking") != null && request.getParameter("dateOfWorking") != "")){
				RequestDispatcher rd = request.getRequestDispatcher("HolidayManagementServlet?action=filterDate");
				request.setAttribute("action", "filterDate");
				request.setAttribute("dateOfWorking", request.getParameter("dateOfWorking"));
				rd.forward(request, response);
			}
			else if(request.getParameter("filterByName") != null && (request.getParameter("dateOfWorking") != null && request.getParameter("dateOfWorking") != "")){
				RequestDispatcher rd = request.getRequestDispatcher("HolidayManagementServlet?action=filterNameDate");
				request.setAttribute("action", "filterNameDate");
				request.setAttribute("filterByName", request.getParameter("filterByName"));
				request.setAttribute("dateOfWorking", request.getParameter("dateOfWorking"));
				rd.forward(request, response);
			}
			
		}
		
	%>
	
	 <%-- <%
		if (request.getParameter("cbxEmployee") != null) {
			response.sendRedirect("HolidayManagementServlet?action=viewRequestByEmployee" + "&employeeID=" + 
									request.getParameter("cbxEmployee"));
	}
	%>
	
	<%
	@SuppressWarnings("unchecked")
	List<Employee>Employeelist = (List<Employee>) session.getAttribute("Employeelist");
	
	%>  --%>


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

</style>