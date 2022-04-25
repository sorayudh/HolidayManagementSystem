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
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@WebMethod
	public boolean addEmployee(String idDepartment, String idRole, String firstName, String lastName, String dob,String phone, String employeeEmail, String password) {
		return employeeService.addNewEmployee(idDepartment,idRole,firstName,lastName,dob,phone,employeeEmail,password);
	}
	
	@WebMethod
	public Employee verifyLoginUser(String username, String password) {
		return employeeService.verifyLoginUser(username,password);
	}
	
}
