package com.packt.org.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.packt.org.domain.Employee;
import com.packt.org.domain.repository.EmployeeRepository;
import com.packt.org.service.EmployeeService;

@Controller
public class EmployeeController {

//	@Autowired
//	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;
	

	@RequestMapping("/employees")
	public String list(Model model) {
	   model.addAttribute("employees", employeeService.getAllEmployees());
	   return "employees";
	}

//	@RequestMapping("/update/employee")
//	public String updateStock(Model model) {
//	   employeeService.updateAllStock();
//	   return "redirect:/employees";
//	}

}
