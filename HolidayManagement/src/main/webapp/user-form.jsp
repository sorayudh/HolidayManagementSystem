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

<div class="wrapper">
    <div class="container">
     <c:if test="${employee != null}">
   		<form action="HolidayManagementServlet?action=update" method="post">
     </c:if>
     <c:if test="${employee == null}">
   		<form action="insert" method="post">
	</c:if>
	<h2>
              <c:if test="${employee != null}">
               Edit User
              </c:if>
              <c:if test="${employee == null}">
               Add New User
              </c:if>
             </h2>
             
             <br/>
             
        <form class="form" method = "post">
         <c:if test="${employee != null}">
           <input type="hidden" name="employeeId" value="<c:out value='${employee.employeeId}' />" />
          </c:if>
          
          First Name: <input type = "text" name = "firstName" value="<c:out value='${employee.firstName}' />" /> 
         
                  
          Last Name: <input type="text" name="lastName" size="45"
                   value="<c:out value='${employee.lastName}' />"
                  />
                  
                  Select Department:&nbsp; <select name="cbxDepartment" style="width: 200px">
					<c:forEach items="${Departmentlist}" var="department">
						<option value="${department.departmentId}" selected="${employee.getDepartment().departmentId}">${department.name}</option>
					</c:forEach>
				</select>
				
				Select Role:&nbsp; <select name="cbxRole" style="width: 200px">
					<c:forEach items="${Rolelist}" var="role">
						<option value="${role.roleId}" selected="${employee.getRole().roleId}">${role.name}</option>
					</c:forEach>
				</select>
				
				 Date of Birth: <input type="date" name="dob" size="45"
                   value="<c:out value='${employee.dob}' />"
                  />
                  
                   Date of Joining: <input type="date" name="dateOfJoining" size="45"
                   value="<c:out value='${employee.dateOfJoining}' />"
                  />
                  
                  Holiday Remaining:  <input type="number" name="holidaysRemaining" size="45"
                   value="<c:out value='${employee.holidaysRemaining}' />"
                  />
                  
                   Phone Number: <input type="number" name="phoneNo" size="45"
                   value="<c:out value='${employee.phoneNo}' />"
                  />
                  
                   Email: <input type="text" name="email" size="45"
                   value="<c:out value='${employee.email}' />"
                 />
                 
                  Password: <input type="text" name="password" size="45"
                   value="<c:out value='${employee.password}' />"
                  />
<br/>
		<input type = "submit" value = "Save" />
        </form>
        <%
	@SuppressWarnings("unchecked")
	List<Department>Departmentlist = (List<Department>) session.getAttribute("Departmentlist");
	List<Role>Rolelist = (List<Role>) session.getAttribute("Rolelist");
	
	%>
    </div>

    <ul class="bg-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>
</body>
</html>

<style>

a:link {
   -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        outline: 0;
        border: 1px solid rgba(255, 255, 255, 0.4);
        background-color: rgba(255, 255, 255, 0.2);
        width: 250px;
        border-radius: 3px;
        padding: 10px 15px;
        margin: 0 auto 10px auto;
        display: block;
        text-align: center;
        font-size: 18px;
        color: white;
        transition-duration: 0.25s;
        font-weight: 300;
}

/* visited link */
a:visited {
   background-color: rgba(255, 255, 255, 0.4);
}

/* mouse over link */
a:hover {
   background-color: rgba(255, 255, 255, 0.4);
}

/* selected link */
a:active {
  background-color: rgba(255, 255, 255, 0.4);
}

    @font-face {
        font-family: 'Source Sans Pro';
        font-style: normal;
        font-weight: 200;
        src: url(https://fonts.gstatic.com/s/sourcesanspro/v19/6xKydSBYKcSV-LCoeQqfX1RYOo3i94_wlxdr.ttf) format('truetype');
    }
    @font-face {
        font-family: 'Source Sans Pro';
        font-style: normal;
        font-weight: 300;
        src: url(https://fonts.gstatic.com/s/sourcesanspro/v19/6xKydSBYKcSV-LCoeQqfX1RYOo3ik4zwlxdr.ttf) format('truetype');
    }
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
        font-weight: 300;
    }
    body {
        font-family: "Source Sans Pro", sans-serif;
        color: white;
        font-weight: 300;
    }
    body ::-webkit-input-placeholder {
        /* WebKit browsers */
        font-family: "Source Sans Pro", sans-serif;
        color: white;
        font-weight: 300;
    }
    body :-moz-placeholder {
        /* Mozilla Firefox 4 to 18 */
        font-family: "Source Sans Pro", sans-serif;
        color: white;
        opacity: 1;
        font-weight: 300;
    }
    body ::-moz-placeholder {
        /* Mozilla Firefox 19+ */
        font-family: "Source Sans Pro", sans-serif;
        color: white;
        opacity: 1;
        font-weight: 300;
    }
    body :-ms-input-placeholder {
        /* Internet Explorer 10+ */
        font-family: "Source Sans Pro", sans-serif;
        color: white;
        font-weight: 300;
    }
    .wrapper {
        background: #50a3a2;
        background: linear-gradient(to bottom right, #50a3a2 0%, #53e3a6 100%);
        /*position: absolute;*/
        top: 50%;
        left: 0;
        width: 100%;
        /*height: 400px;*/
        /*margin-top: -200px;*/
        overflow: hidden;
    }
    .wrapper.form-success .container h1 {
        transform: translateY(85px);
    }
    .container {
        /*max-width: 600px;*/
        margin: 0 auto;
        padding: 7% 0;
        /*height: 400px;*/
        text-align: center;
    }
    .container h1 {
        font-size: 40px;
        transition-duration: 1s;
        font-weight: 200;
    }
    form {
        padding: 20px 0;
        position: relative;
        z-index: 2;
    }
    
        form select {
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        outline: 0;
        border: 1px solid rgba(255, 255, 255, 0.4);
        background-color: rgba(255, 255, 255, 0.2);
		background-image: url(data:image/svg+xml;base64,PHN2ZyBmaWxsPSdibGFjaycgaGVpZ2h0PScyNCcgdmlld0JveD0nMCAwIDI0IDI0JyB3aWR0aD0nMjQnIHhtbG5zPSdodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2Zyc+PHBhdGggZD0nTTcgMTBsNSA1IDUtNXonLz48cGF0aCBkPSdNMCAwaDI0djI0SDB6JyBmaWxsPSdub25lJy8+PC9zdmc+);        
		background-repeat: no-repeat;
  background-position-x: 100%;
  background-position-y: 5px;
		width: 250px;
        border-radius: 3px;
        padding: 10px 15px;
        margin: 0 auto 10px auto;
        display: block;
        text-align: center;
        font-size: 18px;
        color: white;
        transition-duration: 0.25s;
        font-weight: 300;
    }
    form select:hover {
        background-color: rgba(255, 255, 255, 0.4);
    }
    form select:focus {
        background-color: white;
        width: 300px;
        color: #53e3a6;
    }
    
    form input {
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        outline: 0;
        border: 1px solid rgba(255, 255, 255, 0.4);
        background-color: rgba(255, 255, 255, 0.2);
        width: 250px;
        border-radius: 3px;
        padding: 10px 15px;
        margin: 0 auto 10px auto;
        display: block;
        text-align: center;
        font-size: 18px;
        color: white;
        transition-duration: 0.25s;
        font-weight: 300;
    }
    form input:hover {
        background-color: rgba(255, 255, 255, 0.4);
    }
    form input:focus {
        background-color: white;
        width: 300px;
        color: #53e3a6;
    }
    form button {
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        outline: 0;
        background-color: white;
        border: 0;
        padding: 10px 15px;
        color: #53e3a6;
        border-radius: 3px;
        width: 250px;
        cursor: pointer;
        font-size: 18px;
        transition-duration: 0.25s;
    }
    form button:hover {
        background-color: #f5f7f9;
    }
    .bg-bubbles {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: 1;
    }
    .bg-bubbles li {
        position: absolute;
        list-style: none;
        display: block;
        width: 40px;
        height: 40px;
        background-color: rgba(255, 255, 255, 0.15);
        bottom: -160px;
        -webkit-animation: square 25s infinite;
        animation: square 25s infinite;
        transition-timing-function: linear;
    }
    .bg-bubbles li:nth-child(1) {
        left: 10%;
    }
    .bg-bubbles li:nth-child(2) {
        left: 20%;
        width: 80px;
        height: 80px;
        -webkit-animation-delay: 2s;
        animation-delay: 2s;
        -webkit-animation-duration: 17s;
        animation-duration: 17s;
    }
    .bg-bubbles li:nth-child(3) {
        left: 25%;
        -webkit-animation-delay: 4s;
        animation-delay: 4s;
    }
    .bg-bubbles li:nth-child(4) {
        left: 40%;
        width: 60px;
        height: 60px;
        -webkit-animation-duration: 22s;
        animation-duration: 22s;
        background-color: rgba(255, 255, 255, 0.25);
    }
    .bg-bubbles li:nth-child(5) {
        left: 70%;
    }
    .bg-bubbles li:nth-child(6) {
        left: 80%;
        width: 120px;
        height: 120px;
        -webkit-animation-delay: 3s;
        animation-delay: 3s;
        background-color: rgba(255, 255, 255, 0.2);
    }
    .bg-bubbles li:nth-child(7) {
        left: 32%;
        width: 160px;
        height: 160px;
        -webkit-animation-delay: 7s;
        animation-delay: 7s;
    }
    .bg-bubbles li:nth-child(8) {
        left: 55%;
        width: 20px;
        height: 20px;
        -webkit-animation-delay: 15s;
        animation-delay: 15s;
        -webkit-animation-duration: 40s;
        animation-duration: 40s;
    }
    .bg-bubbles li:nth-child(9) {
        left: 25%;
        width: 10px;
        height: 10px;
        -webkit-animation-delay: 2s;
        animation-delay: 2s;
        -webkit-animation-duration: 40s;
        animation-duration: 40s;
        background-color: rgba(255, 255, 255, 0.3);
    }
    .bg-bubbles li:nth-child(10) {
        left: 90%;
        width: 160px;
        height: 160px;
        -webkit-animation-delay: 11s;
        animation-delay: 11s;
    }
    @-webkit-keyframes square {
        0% {
            transform: translateY(0);
        }
        100% {
            transform: translateY(-700px) rotate(600deg);
        }
    }
    @keyframes square {
        0% {
            transform: translateY(0);
        }
        100% {
            transform: translateY(-700px) rotate(600deg);
        }
    }

</style>
