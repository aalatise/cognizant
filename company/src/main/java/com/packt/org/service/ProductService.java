package com.packt.org.service;

import java.util.List;
import java.util.Map;

import com.packt.org.domain.Employee;
import com.packt.org.domain.Product;

public interface ProductService {

	void updateAllStock();
	

	
	List<Product> getAllProducts();
	
	List<Product> getProductsByCategory(String category);
	
	void addProduct(Product product);
	
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	Product getProductById(String productID);
	
	//Rest service
	Product read(String productID);
		
	
//	void updateAllEmployees();
	
	//Employee getProductById(String employeeID);
	
	//Employee getEmployeeById(String employeeID);
	

	
	//void addEmployee(Employee employee);
	
}
