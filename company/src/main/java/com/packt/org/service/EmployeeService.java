package com.packt.org.service;

import java.util.List;
import java.util.Map;

import com.packt.org.domain.Comp;
import com.packt.org.domain.Employee;
import com.packt.org.dto.EmployeeDto;



public interface EmployeeService {

//	void updateAllStock();
	
	void updateAllEmployees();
	List<Employee> getAllEmployees();
	Employee getEmployeeById(String employeeID);
	void addEmployee(Employee employee);
	public void removeEmployee(String employeeID);
	
	//Rest service
	Employee read(String employeeID);

	
//	List<Employee> getAllProducts();
//	List<Employee> getProductsByCategory(String category);
//	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
//	Employee getProductById(String employeeID);
//	void addProduct(Employee employee);
	
}
