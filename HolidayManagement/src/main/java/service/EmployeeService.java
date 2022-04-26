package service;

import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

import model.Employee;

@WebService
public class EmployeeService {
	
	private EmployeeServiceImpl employeeService;
	
	public EmployeeService() {
		employeeService= new EmployeeServiceImpl();
	}
	

	
	@WebMethod
	public int verifyLoginUser(String username, String password) {
		return employeeService.verifyLoginUser(username,password);
	}
	
	@WebMethod
	public boolean addNewHolidayRequest(String reason, String fromdate, String todate,int employeeId) {
		return employeeService.submitRequest(reason, fromdate, todate, employeeId);
	}
	
}
