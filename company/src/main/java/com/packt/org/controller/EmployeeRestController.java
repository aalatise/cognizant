package com.packt.org.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


import org.springframework.http.HttpStatus;

import com.packt.org.domain.Comp;
import com.packt.org.domain.Employee;
import com.packt.org.domain.repository.EmployeeRepository;
import com.packt.org.dto.CompDto;
import com.packt.org.dto.EmployeeDto;
import com.packt.org.service.EmployeeService;


//@Controller
@RestController
@RequestMapping("/rest/emp")
public class EmployeeRestController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	 @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	 public Employee read(@PathVariable(value = "employeeId") String employeeId) {
		      return employeeService.read(employeeId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	List<Employee> getEmployees(){
		return employeeService.getAllEmployees();
	}
  
	 @RequestMapping(value = "/add/{employeeId}", method = RequestMethod.PUT)
	 @ResponseStatus(value = HttpStatus.OK)
	 public void addItem(@PathVariable String employeeId, HttpSession session) {
	//		 compService.addItem(session.getId(),employeeId);
		 	 Employee employee = new Employee();
		 	 employee.setEmployeeId(employeeId);
			 employeeService.addEmployee(employee);
	 }

	
	 @RequestMapping(value = "/update/{employeeId}", method = RequestMethod.PUT)
	 @ResponseStatus(value = HttpStatus.OK)
	   public void update(@PathVariable(value = "employeeId") String employeeId,
										   @RequestParam(value= "salary", required = false) long salary,
										   @RequestParam("name") String name,
										   @RequestParam(value="description", required = false) String description,
										   @RequestParam(value="inactive", required = false) boolean inactive,
										   @RequestBody EmployeeDto employeeDto) {
		 employeeDto.setId(employeeId);
		 employeeDto.setSalary(salary);
		 employeeDto.setName(name);
		 employeeDto.setDescription(description);
		 employeeDto.setInactive(inactive);
		
		 employeeRepository.updateSalary(employeeDto);
	 }
	 
   @RequestMapping(value = "/delete/{employeeId}", method = RequestMethod.DELETE)
   @ResponseStatus(value = HttpStatus.OK)
   public void delete(@PathVariable(value = "employeeId") String employeeId) {
	   employeeRepository.removeEmployee(employeeId);
   }

}
