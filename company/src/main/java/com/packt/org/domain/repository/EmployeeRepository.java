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
	
	public void updateActiveFalse(String employeeId);
	List <Employee> getAllEmployees();
	Employee getEmployeeById(String employeeID);
	
	
	// define rest methods
	public Employee read(String id);
	public void updateEmployee(EmployeeDto employeeDto);
	public void addEmployee(Employee employee); 
	public void removeEmployee(String employeeID);

	
}
