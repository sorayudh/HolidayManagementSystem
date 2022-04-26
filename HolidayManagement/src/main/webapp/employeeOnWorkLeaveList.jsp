<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Employee on Leave and Work List</title>
</head>
<body>
<section>
  <!--for demo wrap-->
  <h1>List of Employees on Leave</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
        <th>Employee ID</th>
                <th>Name</th>
                <th>Department</th>
                  <th>Role</th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <c:forEach var="employee" items="${notWorkingUsers}">
                <tr>
                    <td><c:out value="${employee.employeeId}" /></td>
                    
                    <td><c:out value="${employee.firstName}" /></td>
                    
                    <td><c:out value="${employee.getDepartment().name}" /></td>
                    
                     <td><c:out value="${employee.getRole().name}" /></td>
                </tr>
            </c:forEach>
      </tbody>
    </table>
  </div>
</section>

<section>
  <!--for demo wrap-->
  <h1>List of Employees Working</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <th>Employee ID</th>
                <th>Name</th>
                <th>Department</th>
                <th>Role</th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <c:forEach var="employee" items="${workingUsersList}">
               <tr>
                    <td><c:out value="${employee.employeeId}" /></td>
                    
                    <td><c:out value="${employee.firstName}" /></td>
                    
                    <td><c:out value="${employee.getDepartment().name}" /></td>
                    
                     <td><c:out value="${employee.getRole().name}" /></td>
                </tr>
            </c:forEach>
      </tbody>
    </table>
  </div>
</section>

</body>
</html>

<style>

h1{
  font-size: 30px;
  color: #fff;
  text-transform: uppercase;
  font-weight: 300;
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
