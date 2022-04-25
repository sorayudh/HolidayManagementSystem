package service;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dao.HolidayManagementDTO;
import model.Employee;

public class EmployeeServiceImpl{
	@PersistenceContext(unitName="HolidayManagement")
	EntityManager em;
	
	private HolidayManagementDTO hmDAO;

	
	public List<Employee> getAllEmployees(){
		EntityManager entityManager = 
    			Persistence.createEntityManagerFactory("HolidayManagement").createEntityManager();
    	
    	List queryResults = entityManager.createQuery("SELECT e FROM Employee e").getResultList();
    	List<Employee> listResult = new ArrayList<Employee>();
    	
    	for(int i = 0; i < queryResults.size(); i++)
    	{
    		Employee e = new Employee();
    		e = (Employee)queryResults.get(i);
    		listResult.add(e);
    	}
    	
    	return listResult;
		//return hmDAO.allEmployeeList();	
	}
	
	public boolean addNewEmployee(String idDepartment, String idRole, String firstName, String lastName, String dob,String phone, String employeeEmail, String password) {
		return true;
		//return hmDAO.addNewEmployee(idDepartment,idRole,firstName,lastName,dob,phone,employeeEmail,password);
	}
	
	public Employee verifyLoginUser(String username, String password) {
		try {
    		Employee employeeNew = new Employee();
    		TypedQuery<Employee> query = em.createQuery(
      			  "SELECT e FROM Employee e WHERE e.email = '"+username+"'" , Employee.class);
      			Employee employee = query.setParameter("username", username).getSingleResult();
      			if(employee.getPassword().equals(password))
      				return employee;
      			else
      				return employeeNew;
      			
    	}
    	catch(Exception e) {
    		Employee employee = new Employee();
    		return employee;
    	}
	}
}
