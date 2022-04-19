package dao;

import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
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
    public Boolean checkUser(String username,String password) 
    {
    	TypedQuery<Employee> query = em.createQuery(
    			  "SELECT e FROM Employee e WHERE e.email = :username" , Employee.class);
    			Employee employee = query.setParameter("username", username).getSingleResult();
    			//HttpSession session = request.getSession();
    			return employee.getPassword().equals(password);
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
    
    public void updateEmployee(Employee employeeObj) 
    {
    	em.find(Employee.class, employeeObj.getEmployeeId());
    	em.merge(employeeObj);
    	
    }
    
    public void deleteUser(int employeeId){
    	Employee s=em.find(Employee.class,employeeId);
    	em.remove(s);
    	
    }
    

    public void approveRequest(int holidayRequestId){
    	HolidayRequest hr = em.find(HolidayRequest.class,holidayRequestId);
    	hr.setRequestStatus(em.find(RequestStatus.class, 1));
    	em.merge(hr);
    }
    
    public void rejectRequest(int holidayRequestId){
    	HolidayRequest hr = em.find(HolidayRequest.class,holidayRequestId);
    	hr.setRequestStatus(em.find(RequestStatus.class, 2));
    	em.merge(hr);
    }
    
    
    public void submitRequest(String reason, Date fromdate, Date todate)
    {
    	HolidayRequest h = new HolidayRequest();
    	
    	RequestStatus rs = em.find(RequestStatus.class, 3); // 3 is waiting for approval
    	
    	h.setReason(reason);
    	h.setFromDate(fromdate);
    	h.setToDate(todate);
    	h.setRequestStatus(rs);
    	h.setRequestTime(new Date());
    	h.setTotalDays((int)TimeUnit.DAYS.convert(h.getFromDate().getTime() - h.getToDate().getTime(), TimeUnit.MILLISECONDS));
    	//userId
    	//noconstainttime
    	//breakingcontraints
    	//priority
    	em.persist(h);
    }
    
    
}
