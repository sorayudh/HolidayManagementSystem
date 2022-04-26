package service;


import java.io.StringWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXB;

import dao.HolidayManagementDTO;
import model.Department;
import model.Employee;
import model.HolidayRequest;
import model.RequestStatus;
import model.Role;

public class EmployeeServiceImpl{

	
	public int verifyLoginUser(String username, String password) {
		try {
			EntityManager entityManager = 
	    			Persistence.createEntityManagerFactory("HolidayManagement").createEntityManager();
    		Employee employeeNew = new Employee();
    		TypedQuery<Employee> query = entityManager.createQuery(
      			  "SELECT e FROM Employee e WHERE e.email = :username" , Employee.class);
      			Employee employee = query.setParameter("username", username).getSingleResult();
      			if(employee.getPassword().equals(password)) {
      				return employee.getEmployeeId();
      			}
      				
      			else
      				return 0;
      			
    	}
    	catch(Exception e) {
    		return 0;
    	}
	}
	
	 public boolean submitRequest(String reason, String fromdate, String todate,int employeeId)
	    {
		 try {
			 EntityManager em = 
		    			Persistence.createEntityManagerFactory("HolidayManagement").createEntityManager();
			 HolidayRequest h = new HolidayRequest();
		    	Employee employee = em.find(Employee.class, employeeId);
		    	RequestStatus rs = em.find(RequestStatus.class, 3);
		    	h.setReason(reason);
		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    	h.setFromDate(formatter.parse(fromdate));
		    	h.setToDate(formatter.parse(todate));
		    	h.setRequestStatus(rs);
		    	h.setRequestTime(new Date());
		    	h.setTotalDays((int)TimeUnit.DAYS.convert(h.getToDate().getTime() - h.getFromDate().getTime(), TimeUnit.MILLISECONDS) + 1);
		    	h.setEmployee(employee);
		    	h.setPriority(getPriority(employee.getHolidaysRemaining()));
		    	h.setNoConstraintTime((byte) (0));
		    	h.setBreakingConstraints((byte) (0));
		    	em.persist(h);
		    	em.flush();
		    	return true;
		 }
		 catch(Exception e) {
			 return false;
		 }
	    	
	    }
	 
	 public int getPriority(int noOfDaysLeft) {
	    	return noOfDaysLeft >= 26 && noOfDaysLeft <= 30 ? 1 : 
	    		noOfDaysLeft >= 21 && noOfDaysLeft <= 25 ? 2 :
	    			noOfDaysLeft >= 16 && noOfDaysLeft <= 20 ? 3 :
	    				noOfDaysLeft >= 11 && noOfDaysLeft <= 15 ? 4 :
	    					noOfDaysLeft >= 6 && noOfDaysLeft <= 10 ? 5 :
	    		6; 
	    }
	    
}
