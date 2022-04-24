package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HolidayManagementDTO;
import model.Department;
import model.Employee;
import model.HolidayRequest;
import model.Role;



/**
 * Servlet implementation class HolidayManagementServlet
 */
@WebServlet("/HolidayManagementServlet")
public class HolidayManagementServlet extends HttpServlet {
	@EJB
	private HolidayManagementDTO hmDTO;
    List<LocalDate> AllDates;
    List<HolidayRequest> listHolidayRequest;
    List<HolidayRequest> listApprovedHolidayRequest;
    List<HolidayRequest> listRejectedHolidayRequest;
    List<HolidayRequest> listCancelledHolidayRequest;
    List<HolidayRequest> listEmployeeRequest;
    List<Employee> allUserList;
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolidayManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
        AllDates = new ArrayList<LocalDate>();
        listHolidayRequest = new ArrayList<HolidayRequest>();
        listApprovedHolidayRequest = new ArrayList<HolidayRequest>();
        listRejectedHolidayRequest = new ArrayList<HolidayRequest>();
        listCancelledHolidayRequest = new ArrayList<HolidayRequest>();
        allUserList = new ArrayList<Employee>();
        checkDates();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String param_action = request.getParameter("action");
		String tableStr = new String();
		HttpSession userSession = request.getSession();
		//checkDates();
		
		switch(param_action) {
		//login
		case "login":
		{
			String username = request.getParameter("txtName");
			String password = request.getParameter("txtPwd");
			Employee output = hmDTO.checkUser(username, password);
			if(output.getEmployeeId()!=0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("adminHome.jsp");
				userSession.setAttribute("employeeDetail", output);
				dispatcher.forward(request, response);
			}
			else {
				tableStr += "<br/><strong>Please check username and password</strong>";
			}
			
		}
		break;
		case "employeelogin":
		{
			String username = request.getParameter("txtName");
			String password = request.getParameter("txtPwd");
			Employee output = hmDTO.checkUser(username, password);
			if(output.getEmployeeId()!=0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("employeeHome.jsp");
				userSession.setAttribute("employeeDetail", output);
				dispatcher.forward(request, response);
			}
			else {
				tableStr += "<br/><strong>Please check username and password</strong>";
			}
		}
		break;
		case "showAllEmployee":
		{
			List<Employee> employeelist = hmDTO.allEmployee();
			
			tableStr += "<h2>View all Employees</h2>";
			tableStr += "<table border='1'";
			tableStr += "<tr><td>Employe ID</td><td>Department Name</td><td>Role</td><td>First Name</td><td>Last Name</td><td>DOB</td><td>Date of Joining</td><td>Phone No</td><td>Email</td><td>Holidays Remaining</td></tr>";
			for(int i = 0; i < employeelist.size(); i++)
			{
				tableStr +="<tr><td>" + String.valueOf(employeelist.get(i).getEmployeeId()) + 
						"</td><td>" + employeelist.get(i).getDepartment().getName() + 
						"</td><td>" + employeelist.get(i).getRole().getName() +
						"</td><td>" + employeelist.get(i).getFirstName() +
						"</td><td>" + employeelist.get(i).getLastName() +
						"</td><td>" + employeelist.get(i).getDob() +
						"</td><td>" + employeelist.get(i).getDateOfJoining() +
						"</td><td>" + employeelist.get(i).getPhoneNo() +
						"</td><td>" + employeelist.get(i).getEmail() +
						"</td><td>" + employeelist.get(i).getHolidaysRemaining() +
						"</td><tr>";
			}
			tableStr += "</table>";
		}
		break;
		case "addEmployee":
		{
			
			List<Department> Departmentlist = hmDTO.allDepartment();
			List<Role> Rolelist =  hmDTO.allRole();
			
			HttpSession session = request.getSession();
			session.setAttribute("Departmentlist", Departmentlist);
			session.setAttribute("Rolelist", Rolelist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("addemployee.jsp");
			dispatcher.forward(request, response);
		}
		break;
		case "addEmployeeWithDetail":
		{
			Date date = new Date();
			String cbxDepartment = request.getParameter("deparmentID");
			int idDepartment = Integer.parseInt(cbxDepartment);
			String cbxRole = request.getParameter("roleID");
			int idRole = Integer.parseInt(cbxRole);
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String dob = request.getParameter("dob");
			String employeePhone = request.getParameter("employeePhone");
			BigInteger phone = new BigInteger(employeePhone);
			String employeeEmail = request.getParameter("employeeEmail");
			String employeePwd = request.getParameter("employeePwd");
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = formatter.parse(dob);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			hmDTO.insertEmployeeWithDetails(idDepartment, idRole, firstName, lastName, date, phone, employeeEmail, employeePwd);

			response.sendRedirect("HolidayManagementServlet?action=listUsers");
			
		}
		break;
		case "filterByEmployee":
		{
			List<Employee> Employeelist = hmDTO.getAllEmployee();
			 
			HttpSession session = request.getSession();
			session.setAttribute("Employeelist", Employeelist);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("filterByEmployee.jsp");
			dispatcher.forward(request, response);
			
		}
		break;
		
		case "filter":
		{
			String name = request.getParameter("filterByName");
			filterData(request,response,name.toLowerCase());
			break;
		}
		
		case "viewRequestByEmployee":
		{
			
		}
		break;
		case "listUsers":
		{
			 listUser(request, response);
	          
		}
		break;
		case "editUsers":{
			showEditForm(request, response);
			
		}
		break;
		case "deleteUser":{
			deleteUser(request,response);
		}
		
		break;
		
		case "update":{
			updateUser(request,response);
		}
		break;
		case "submitRequest":
		{
			String reason = request.getParameter("reason");
			String fromdate = request.getParameter("fromdate");
			String todate = request.getParameter("todate");
			Employee employee = (Employee) userSession.getAttribute("employeeDetail");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Boolean noConstraints = false;
			try {
				var fdate = formatter.parse(fromdate);
				var tdate = formatter.parse(todate);
				if(AllDates.contains(convertToLocalDateViaInstant(fdate)) && AllDates.contains(convertToLocalDateViaInstant(tdate))) {
					noConstraints=true;
				}
				
				hmDTO.submitRequest(reason, fdate, tdate,employee,noConstraints);
				
				response.sendRedirect("HolidayManagementServlet?action=employeeRequestList");
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				tableStr += "<br/><strong>Something went wrong</strong>";
			}
			
			
			
		}
		break;
		case "listHolidayRequests":
		{
			listHolidayRequest(request, response);
	          
		}
		break;
		case "approveRequest":
		{
			approveOrRejectRequest(request, response,"approve");
		}
		break;
		
		case "employeeRequestList":
		{
			listEmployeeRequest(request, response);
		}
		break;
		
		default:
			if(param_action.contains("edit")) {
				showEditForm(request, response);
			}
			else if(param_action.contains("delete")) {
				deleteUser(request,response);
			}
			else if(param_action.contains("approveRequest")) {
				approveOrRejectRequest(request, response,"approve");
			}
			else if(param_action.contains("rejectRequest")) {
				approveOrRejectRequest(request, response,"reject");
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Holiday Management System </title>");
		out.println("</head>");
		
		out.println("<body>");
		out.println(tableStr);
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		        //List < User > listUser = hmDTO.getAllUser();
		        List<Employee> listUser = hmDTO.getAllEmployee();
		        request.setAttribute("listUser", listUser);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("employeeListForm.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void filterData(HttpServletRequest request, HttpServletResponse response,String name) throws ServletException, IOException{
		if(name!=null) {
			var flist = listHolidayRequest.stream().filter(s-> s.getEmployee().getFirstName().toLowerCase().contains(name)).collect(Collectors.toList());
			 request.setAttribute("listHolidayRequest", flist);
			 
			 var alist = listApprovedHolidayRequest.stream().filter(s-> s.getEmployee().getFirstName().toLowerCase().contains(name)).collect(Collectors.toList());
			 request.setAttribute("allApprovedRequestList", alist);
			 
			 var rlist = listRejectedHolidayRequest.stream().filter(s-> s.getEmployee().getFirstName().toLowerCase().contains(name)).collect(Collectors.toList());
		     request.setAttribute("allRejectedRequestList", rlist);
		        
		     var clist = listCancelledHolidayRequest.stream().filter(s-> s.getEmployee().getFirstName().toLowerCase().contains(name)).collect(Collectors.toList());
		     request.setAttribute("allCancelledHolidayRequest", clist);
		     
		     RequestDispatcher dispatcher = request.getRequestDispatcher("holidayRequestListForm.jsp");
			 dispatcher.forward(request, response);
		}
	}
	
	private void listEmployeeRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
			HttpSession userSession = request.getSession();
			Employee employee = (Employee) userSession.getAttribute("employeeDetail");
			listEmployeeRequest = hmDTO.getAllHolidayByEmployeeId(employee.getEmployeeId());
			 request.setAttribute("listEmployeeRequest", listEmployeeRequest);
			RequestDispatcher dispatcher = request.getRequestDispatcher("employeeRequestList.jsp");
			dispatcher.forward(request, response);
	}
	
	private void listHolidayRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		        listHolidayRequest = hmDTO.getAllHolidayRequest();
		        listApprovedHolidayRequest = hmDTO.allApprovedRequest();
		        listRejectedHolidayRequest = hmDTO.allRejectedRequest();
		        listCancelledHolidayRequest = hmDTO.allCancelledRequest();
		        allUserList = hmDTO.getAllEmployee();
		        request.setAttribute("listHolidayRequest", listHolidayRequest);
		        request.setAttribute("allApprovedRequestList", listApprovedHolidayRequest);
		        request.setAttribute("allRejectedRequestList", listRejectedHolidayRequest);
		        request.setAttribute("allCancelledHolidayRequest", listCancelledHolidayRequest);

		        HttpSession session = request.getSession();
				session.setAttribute("userList", allUserList);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("holidayRequestListForm.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void approveOrRejectRequest(HttpServletRequest request, HttpServletResponse response,String toDo)
		    throws IOException {
		 var id = request.getQueryString().substring(request.getQueryString().lastIndexOf("holidayRequestId=")+17);
		 int holidayRequestId = Integer.parseInt(id);
		 if(toDo=="approve") {
			 hmDTO.approveRequest(holidayRequestId);
		 }
		 else if(toDo=="reject") {
			 hmDTO.rejectRequest(holidayRequestId);
		 }
		
		 response.sendRedirect("HolidayManagementServlet?action=listHolidayRequests");
		    }
	
		 private void deleteUser(HttpServletRequest request, HttpServletResponse response)
				    throws IOException {
			 var id = request.getQueryString().substring(request.getQueryString().lastIndexOf("employeeId=")+11);
			 int employeeId = Integer.parseInt(id);
			 hmDTO.deleteUser(employeeId);
			 response.sendRedirect("HolidayManagementServlet?action=listUsers");
				    }
	
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        //int id = Integer.parseInt(request.getParameter("employeeId"));
				//var id = request.getParameter("employeeId");
				var id = request.getQueryString().substring(request.getQueryString().lastIndexOf("employeeId=")+11);
		
				int employeeId = Integer.parseInt(id);
		        Employee existingEmployee = hmDTO.getEmployeeDetail(employeeId);
		        List<Department> Departmentlist = hmDTO.allDepartment();
				List<Role> Rolelist =  hmDTO.allRole();
				
		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		        request.setAttribute("employee", existingEmployee);
		        request.setAttribute("Departmentlist", Departmentlist);
		        request.setAttribute("Rolelist", Rolelist);
		        dispatcher.forward(request, response);

		    }
	
	 private void updateUser(HttpServletRequest request, HttpServletResponse response)
			    throws IOException {
		 			Employee emp = new Employee();
		 			emp.setDepartment(new Department());
		 			emp.setRole(new Role());
		 			Date dateofbirth= new Date();
		 			Date dateJoining = new Date();
		 			emp.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
			        emp.setFirstName((String)(request.getParameter("firstName")));
			        emp.setLastName((String)(request.getParameter("lastName")));
					emp.getDepartment().setDepartmentId(Integer.parseInt(request.getParameter("cbxDepartment")));
					emp.getRole().setRoleId(Integer.parseInt(request.getParameter("cbxRole")));
			    	emp.setPhoneNo(new BigInteger(request.getParameter("phoneNo")));
			        emp.setEmail((String)(request.getParameter("email")));
			        emp.setHolidaysRemaining(Integer.parseInt(request.getParameter("holidaysRemaining")));
			        emp.setPassword((String)(request.getParameter("password")));
			        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					try {
						emp.setDob(formatter.parse(request.getParameter("dob")));
						emp.setDateOfJoining(formatter.parse(request.getParameter("dateOfJoining")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					hmDTO.updateEmployee(emp);

					response.sendRedirect("HolidayManagementServlet?action=listUsers");
					
			       
			    }
	 
	 public void checkDates() {
		 Date date = new Date();
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 int year = calendar.get(Calendar.YEAR);
		 LocalDate startDate = LocalDate.parse(year+"-12-23");
		 LocalDate endDate = LocalDate.parse(year+1+"-01-03"); 
		 AllDates = startDate.datesUntil(endDate).collect(Collectors.toList());
		
	 }
	 
	 public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		    return dateToConvert.toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDate();
		}
	 
	 
}
