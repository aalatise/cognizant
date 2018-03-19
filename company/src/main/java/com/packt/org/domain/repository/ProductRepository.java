package com.packt.org.domain.repository;

import java.util.List;
import java.util.Map;

import com.packt.org.domain.Product;

public interface ProductRepository {

	List <Product> getAllProducts();
	void updateStock(String productId, long noOfUnits);
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(Map<String,List<String>> filterParams);
	Product getProductById(String productID);
	void addProduct(Product product);
	
	public Product read(String id);
	
//	List <Employee> getAllEmployees();
//	List<Employee> getEmployeesByCategory(String category);
//	List<Employee> getEmployeesByFilter(Map<String,List<String>> filterParams);
//	Employee getEmployeeById(String employeeID);
//	public void addEmployee(Employee employee); 
}
