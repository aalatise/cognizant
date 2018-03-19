package com.packt.org.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.packt.org.domain.CompResource;
import com.packt.org.service.EmployeeService;
import com.packt.org.service.ProductService;

public class CompItemMapper implements RowMapper<CompResource> {
   private EmployeeService employeeService;
   private ProductService productService;
   
   public CompItemMapper( ProductService productService) {
      this.productService = productService;
   }

   public CompItemMapper( EmployeeService employeeService) {
	      this.employeeService = employeeService;
	   }
   
   @Override
   public CompResource mapRow(ResultSet rs, int rowNum) throws SQLException {
//      CompResource compResource = new CompResource(rs.getString("ID"));
//      compResource.setProduct(productService.getProductById(rs.getString("PRODUCT_ID")));
//      compResource.setQuantity(rs.getInt("QUANTITY"));
     CompResource compResource = new CompResource(rs.getString("COMP_ID"));
     compResource.setEmployee(employeeService.getEmployeeById(rs.getString("EMPLOYEE_ID")));
//     compResource.setQuantity(rs.getInt("QUANTITY"));

      return compResource;
   }
}
