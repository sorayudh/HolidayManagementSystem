package service;


import java.io.StringWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXB;

import dao.HolidayManagementDTO;
import model.Department;
import model.Employee;
import model.Role;

public class EmployeeServiceImpl{
	@PersistenceContext(unitName="HolidayManagement")
	EntityManager em;
	
	
	
	public boolean verifyLoginUser(String username, String password) {
		try {
			EntityManager entityManager = 
	    			Persistence.createEntityManagerFactory("HolidayManagement").createEntityManager();
    		Employee employeeNew = new Employee();
    		TypedQuery<Employee> query = entityManager.createQuery(
      			  "SELECT e FROM Employee e WHERE e.email = :username" , Employee.class);
      			Employee employee = query.setParameter("username", username).getSingleResult();
      			if(employee.getPassword().equals(password)) {
      				
      				return true;
      			}
      				
      			else
      				return false;
      			
    	}
    	catch(Exception e) {
    		return false;
    	}
	}
}
