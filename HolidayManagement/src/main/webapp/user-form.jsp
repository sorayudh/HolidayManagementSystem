<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="model.Department" %>
<%@ page import="model.Role" %>
<%@ page import="java.util.List" %>
<html>
<head>
 <title>User Management Application</title>
</head>
<body>
 <center>
  <h1>User Management</h1>
        <h2>
         <a href="new">Add New User</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list">List All Users</a>
         
        </h2>
 </center>
    <div align="center">
  <c:if test="${employee != null}">
   <form action="HolidayManagementServlet?action=update" method="post">
        </c:if>
        <c:if test="${employee == null}">
   <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${employee != null}">
               Edit User
              </c:if>
              <c:if test="${employee == null}">
               Add New User
              </c:if>
             </h2>
            </caption>
          <c:if test="${employee != null}">
           <input type="hidden" name="employeeId" value="<c:out value='${employee.employeeId}' />" />
          </c:if>            
            <tr>
                <th>First Name: </th>
                <td>
                 <input type="text" name="firstName" size="45"
                   value="<c:out value='${employee.firstName}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                 <input type="text" name="lastName" size="45"
                   value="<c:out value='${employee.lastName}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>Department: </th>
                <td>
                 <select name="cbxDepartment" style="width: 200px">
					<c:forEach items="${Departmentlist}" var="department">
						<option value="${department.departmentId}" selected="${employee.getDepartment().departmentId}">${department.name}</option>
					</c:forEach>
				</select>
                </td>
            </tr>
            <tr>
                <th>Role: </th>
                <td>
                 <select name="cbxRole" style="width: 200px">
					<c:forEach items="${Rolelist}" var="role">
						<option value="${role.roleId}" selected="${employee.getRole().roleId}">${role.name}</option>
					</c:forEach>
				</select>
                </td>
            </tr>
            <tr>
                <th>Date of Birth: </th>
                <td>
                 <input type="date" name="dob" size="45"
                   value="<c:out value='${employee.dob}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>Date of Joining: </th>
                <td>
                 <input type="date" name="dateOfJoining" size="45"
                   value="<c:out value='${employee.dateOfJoining}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>Phone No: </th>
                <td>
                 <input type="number" name="phoneNo" size="45"
                   value="<c:out value='${employee.phoneNo}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>User Email: </th>
                <td>
                 <input type="text" name="email" size="45"
                   value="<c:out value='${employee.email}' />"
                 />
                </td>
            </tr>
            <tr>
                <th>Holiday Remaining: </th>
                <td>
                 <input type="number" name="holidaysRemaining" size="45"
                   value="<c:out value='${employee.holidaysRemaining}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                 <input type="text" name="password" size="45"
                   value="<c:out value='${employee.password}' />"
                  />
                </td>
            </tr>
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Save" />
             </td>
            </tr>
        </table>
        </form>
    </div> 
    
    <%
	@SuppressWarnings("unchecked")
	List<Department>Departmentlist = (List<Department>) session.getAttribute("Departmentlist");
	List<Role>Rolelist = (List<Role>) session.getAttribute("Rolelist");
	
	%>
</body>
</html>