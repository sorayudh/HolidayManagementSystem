package dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import model.Department;
import model.Employee;
import model.HolidayRequest;
import model.RequestStatus;
import model.Role;


/**
 * Session Bean implementation class HolidayManagementDTO
 */
@Stateless
@LocalBean
public class HolidayManagementDTO {
	@PersistenceContext(unitName="HolidayManagement")
	EntityManager em;
    /**
     * Default constructor. 
     */
	
    public HolidayManagementDTO() {
        // TODO Auto-generated constructor stub
    }
    
    public Employee checkUser(String username,String password) 
    {
    	try {
    		Employee employeeNew = new Employee();
    		TypedQuery<Employee> query = em.createQuery(
      			  "SELECT e FROM Employee e WHERE e.email = :username" , Employee.class);
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
    
  
    public List<Employee> allEmployeeList(){
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
    }
    
    
    
    public List<Employee> allEmployee(){
    	List queryResults = em.createQuery("SELECT e FROM Employee e").getResultList();
    	List<Employee> listResult = new ArrayList<Employee>();
    	
    	for(int i = 0; i < queryResults.size(); i++)
    	{
    		Employee e = new Employee();
    		e = (Employee)queryResults.get(i);
    		listResult.add(e);
    	}
    	
    	return listResult;
    }
    
    public List<HolidayRequest> getAllHolidayByEmployeeId(int employeeId){
    	 Query query = em.createQuery("SELECT h FROM HolidayRequest h" 
     	          + " WHERE h.employee ="+employeeId);
     	  return getHolidayList(query.getResultList());
    }
    
    
    public List<HolidayRequest> getAllHolidayRequest(){
  	  Query query = em.createQuery("SELECT h FROM HolidayRequest h" 
  	          + " WHERE h.requestStatus = 3");
  	  return getHolidayList(query.getResultList());
  }
    
    public List<HolidayRequest> allApprovedRequest() {
        Query query = em.createQuery("SELECT h FROM HolidayRequest h" 
          + " WHERE h.requestStatus = 1");
        return getHolidayList(query.getResultList());
    }
    
    
    public List<HolidayRequest> allRejectedRequest() {
        Query query = em.createQuery("SELECT h FROM HolidayRequest h" 
          + " WHERE h.requestStatus = 2");
        return getHolidayList(query.getResultList());
    }
    
    public List<HolidayRequest> allCancelledRequest() {
        Query query = em.createQuery("SELECT h FROM HolidayRequest h" 
          + " WHERE h.requestStatus = 4");
        return getHolidayList(query.getResultList());
    }
    
    public List<HolidayRequest> getHolidayList(List output){
   	 List<HolidayRequest> listResult = new ArrayList<HolidayRequest>();
   	 for(int i = 0; i < output.size(); i++)
    	{
        	HolidayRequest e = new HolidayRequest();
    		e = (HolidayRequest)output.get(i);
    		listResult.add(e);
    	}
        return listResult;
   }
    
    public Employee getEmployeeDetail(int employeeId){
    	Employee employee =  em.find(Employee.class, employeeId); 
    	return employee;
    }
    
    public List<Employee> getAllEmployee(){
    	List<Employee> listEmployee = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    	return listEmployee;
    }
    
  
    
    public List<Department> allDepartment()
    {
    	List<Department> listDepartment = em.createNamedQuery("Department.findAll", Department.class).getResultList();
    	return listDepartment;
    }
    
    public List<Role> allRole()
    {
    	List<Role> listRole = em.createNamedQuery("Role.findAll", Role.class).getResultList();
    	return listRole;
    }
    
    public void insertEmployeeWithDetails(int idDepartment, int idRole, String firstName, String lastName, Date dob,BigInteger phone, String employeeEmail, String password)
    {
    	Department a = em.find(Department.class, idDepartment);
    	
    	
    	Role w = em.find(Role.class, idRole);
    	
    	
    	Employee c = new Employee();
    	c.setFirstName(firstName);
    	c.setLastName(lastName);
    	c.setDob(dob);
    	c.setPhoneNo(phone);
    	c.setEmail(employeeEmail);
    	c.setPassword(password);
    	c.setDateOfJoining(new Date());
    	c.setHolidaysRemaining(30);
    	
    	c.setDepartment(a);
    	c.setRole(w);
    	
    	em.persist(c);
    }
    
    public boolean addNewEmployee(String idDepartment, String idRole, String firstName, String lastName, String dob,String phone, String employeeEmail, String password) {
    	try {
    		Department a = em.find(Department.class, idDepartment);
        	Role w = em.find(Role.class, idRole);
        	Employee c = new Employee();
        	c.setFirstName(firstName);
        	c.setLastName(lastName);
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        	var dateofbirth = formatter.parse(dob);
        	c.setDob(dateofbirth);
        	c.setPhoneNo(new BigInteger(phone));
        	c.setEmail(employeeEmail);
        	c.setPassword(password);
        	c.setDateOfJoining(new Date());
        	c.setHolidaysRemaining(30);
        	c.setDepartment(a);
        	c.setRole(w);
        	try {
        		em.persist(c);
        		return true;
        	}
        	catch(Exception e) {
        		return false;
        	}
    	}
    	catch(Exception e) {
    		return false;
    	}
    	
    }
    
    public void updateEmployee(Employee employeeObj) 
    {
    	em.find(Employee.class, employeeObj.getEmployeeId());
    	em.merge(employeeObj);
    	
    }
    
//    public boolean checkHeadOrDeputyHeadOnDuty(int departmentId,Date fromdate, Date todate) {
//    	
//    	
//    }
    
    
    
    public void deleteUser(int employeeId){
    	Employee s=em.find(Employee.class,employeeId);
    	em.remove(s);
    	
    }
    

    public void approveRequest(int holidayRequestId){
    	HolidayRequest hr = em.find(HolidayRequest.class,holidayRequestId);
    	Employee e = hr.getEmployee();
    	e.setHolidaysRemaining(e.getHolidaysRemaining() - hr.getTotalDays());
    	hr.setRequestStatus(em.find(RequestStatus.class, 1));
    	hr.setEmployee(e);
    	em.merge(hr);
    }
    
    public void rejectRequest(int holidayRequestId){
    	HolidayRequest hr = em.find(HolidayRequest.class,holidayRequestId);
    	hr.setRequestStatus(em.find(RequestStatus.class, 2));
    	em.merge(hr);
    }
    
    
    public void submitRequest(String reason, Date fromdate, Date todate,Employee employee,Boolean noConstraints)
    {
    	HolidayRequest h = new HolidayRequest();
    	
    	RequestStatus rs = em.find(RequestStatus.class, 3); // 3 is waiting for approval
    	
    	
    	h.setReason(reason);
    	h.setFromDate(fromdate);
    	h.setToDate(todate);
    	h.setRequestStatus(rs);
    	h.setRequestTime(new Date());
    	h.setTotalDays((int)TimeUnit.DAYS.convert(h.getToDate().getTime() - h.getFromDate().getTime(), TimeUnit.MILLISECONDS) + 1);
    	h.setEmployee(employee);
    	h.setPriority(getPriority(employee.getHolidaysRemaining()));
    	h.setNoConstraintTime((byte) (noConstraints ? 1 : 0 ));
    	em.persist(h);
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
