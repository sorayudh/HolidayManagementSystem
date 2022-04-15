package dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Department;
import model.Employee;

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
    
    public Employee getEmployeeDetail(int employeeId){
    	Employee employee =  em.find(Employee.class, employeeId); //em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    	//employee.setDepartment(em.find(Department.class, employee.getDepartment().getDepartmentId()));
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
    
    
}
