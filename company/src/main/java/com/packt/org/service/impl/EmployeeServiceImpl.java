package com.packt.org.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.org.domain.Comp;
import com.packt.org.domain.Employee;
import com.packt.org.domain.repository.EmployeeRepository;
import com.packt.org.dto.EmployeeDto;
import com.packt.org.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

   @Autowired
   private EmployeeRepository employeeRepository;
   
   @Override
   public Employee read(String id) {
      return employeeRepository.read(id);
   }
   

   
   @Override
   public void updateAllEmployees() {
	   List <Employee> allEmployees = employeeRepository.getAllEmployees();
	   for(Employee employee : allEmployees) {
//         if(employee.getInactive())
//            employeeRepository.updateSalary(employee.getEmployeeId(), 0);
      }
   }
   
   @Override
   public List<Employee> getAllEmployees() {
         return employeeRepository.getAllEmployees();
   }
   
   @Override
   public Employee getEmployeeById(String employeeID) {
      return employeeRepository.getEmployeeById(employeeID);
   }
   
	@Override
	public void addEmployee(Employee employee) {
	  employeeRepository.addEmployee(employee);   
   }
	
	@Override
	public void removeEmployee(String employeeID) {
		employeeRepository.removeEmployee(employeeID);
	}

//   public List<Employee> getProductsByCategory(String category) {
//	   return employeeRepository.getProductsByCategory(category);
//	}   
   
//   public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
//	      return employeeRepository.getProductsByFilter(filterParams);
//	}
   
//   @Override
//   public Employee getProductById(String productID) {
//      return employeeRepository.getProductById(productID);
//   }
}
