package com.packt.org.domain.repository;

import java.util.List;
import java.util.Map;

import com.packt.org.domain.Comp;
import com.packt.org.domain.Employee;
import com.packt.org.dto.CompDto;
import com.packt.org.dto.EmployeeDto;

public interface EmployeeRepository {

//	List <Employee> getAllProducts();
//	void updateStock(String productId, long noOfUnits);

//	List<Employee> getEmployeesByCategory(String category);
//	List<Employee> getEmployeesByFilter(Map<String,List<String>> filterParams);
	
	void updateSalary(String productId, long salary);
	public void updateActiveFalse(String employeeId);
	List <Employee> getAllEmployees();
	Employee getEmployeeById(String employeeID);
	public void addEmployee(Employee employee); 
	public void removeEmployee(String employeeID);
	
	// define rest methods
	Employee read(String id);
	void updateSalary(EmployeeDto employeeDto);
	void create(EmployeeDto employeeDto);
	
}
