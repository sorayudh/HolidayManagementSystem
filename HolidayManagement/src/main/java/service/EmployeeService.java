package service;

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
	public boolean verifyLoginUser(String username, String password) {
		return employeeService.verifyLoginUser(username,password);
	}
	
}
