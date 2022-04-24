package service;


import java.util.List;

import javax.inject.Inject;

import dao.HolidayManagementDTO;
import model.Employee;

public class EmployeeServiceImpl{

	@Inject
	private HolidayManagementDTO hmDAO;

	
	public List<Employee> getAllEmployees(){
		return hmDAO.allEmployeeList();	
	}
	
	public boolean addNewEmployee(String idDepartment, String idRole, String firstName, String lastName, String dob,String phone, String employeeEmail, String password) {
		return hmDAO.addNewEmployee(idDepartment,idRole,firstName,lastName,dob,phone,employeeEmail,password);
	}
	
	public boolean verifyLoginUser(String username, String password) {
		var output = hmDAO.checkUser(username,password);
		return output.getEmployeeId()!=0;
	}
}
