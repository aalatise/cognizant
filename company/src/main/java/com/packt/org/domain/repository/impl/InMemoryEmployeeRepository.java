package com.packt.org.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.packt.org.domain.Comp;
import com.packt.org.domain.Employee;
import com.packt.org.domain.repository.EmployeeRepository;
import com.packt.org.dto.CompDto;
import com.packt.org.dto.EmployeeDto;
import com.packt.org.exception.EmployeeNotFoundException;

@Repository
public class  InMemoryEmployeeRepository implements EmployeeRepository{
  
   @Autowired
   private NamedParameterJdbcTemplate jdbcTemplate;

   //begin rest implementation
   @Override
   public Employee read(String id) {
	  return getEmployeeById(id);
   }
   
   @Override
   public List<Employee> getAllEmployees() {
      Map<String, Object> params = new HashMap<String, Object>();
        List<Employee> result = jdbcTemplate.query("SELECT * FROM employees", params, new EmployeeMapper());
      
        return result;
   }

   


   private static final class EmployeeMapper implements RowMapper<Employee> {
	      public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	 Employee employee = new Employee();
	    	 employee.setEmployeeId(rs.getString("ID"));
	    	 employee.setName(rs.getString("NAME"));
	    	 employee.setDescription(rs.getString("DESCRIPTION"));
	    	 employee.setSalary(rs.getLong("SALARY"));
	    	 employee.setInactive(rs.getBoolean("INACTIVE"));
	         return employee;
	      }
   }

   @Override
   public void updateEmployee(EmployeeDto employeeDto) { 
	   updateEmployee(employeeDto.getId(), employeeDto.getSalary(),employeeDto.getName(),
			   employeeDto.getDescription(),employeeDto.getInactive());
   }
   
   
   public void updateEmployee(String employeeId, Long salary
		   ,String name,
		   String description,Boolean inactive) { 
      String SQL = "UPDATE EMPLOYEES SET SALARY = :salary, NAME = :name, DESCRIPTION = :description, INACTIVE = :inactive WHERE ID = :id"; 
      Map<String, Object> params = new HashMap<>();
      params.put("salary", salary); 
      params.put("name", name);
      params.put("description", description);
      params.put("inactive", inactive);
      params.put("id", employeeId); 
     
      jdbcTemplate.update(SQL, params); 
   }
   
   @Override
   public void updateActiveFalse(String employeeId) { 
      String SQL = "UPDATE EMPLOYEES SET INACTIVE = :inactive WHERE ID = :id"; 
      Map<String, Object> params = new HashMap<>();
      params.put("inactive", false); 
      params.put("id", employeeId); 
     
      jdbcTemplate.update(SQL, params); 
   }
   
   @Override
   public Employee getEmployeeById(String employeeID){
	      String SQL = "SELECT * FROM EMPLOYEES WHERE ID = :id";  
	      Map<String, Object> params = new HashMap<>();
	      params.put("id", employeeID);  
	         
	      try {
	         return jdbcTemplate.queryForObject(SQL, params, new EmployeeMapper());
	      } catch (DataAccessException e) {
	         throw new EmployeeNotFoundException(employeeID);
	      }   
	   }
   
   @Override
   public void addEmployee(Employee employee) {
         String SQL = "INSERT INTO EMPLOYEES (ID, "
               + "NAME,"
               + "DESCRIPTION,"
               + "SALARY,"
               + "INACTIVE) "
               + "VALUES (:id, :name, :description, :salary, :inactive)";  
         
         Map<String, Object> params = new HashMap<>();
         params.put("id", employee.getEmployeeId());  
         params.put("name", employee.getName());  
         params.put("description", employee.getDescription());  
         params.put("salary", employee.getSalary());  
         params.put("inactive", employee.getInactive());  
         

         jdbcTemplate.update(SQL, params);     
      }
   
   @Override
   public void removeEmployee(String employeeID) {
	    String SQL = "DELETE FROM EMPLOYEES WHERE ID = :id";
	    //jdbcTemplate.
	    
	    Map<String, Object> params = new HashMap<>();
        params.put("id", employeeID); 
        int status = jdbcTemplate.update(SQL, params);
                
//	    SqlParameterSource namedParameters = new MapSqlParameterSource("id", employeeID);
//        int status = jdbcTemplate.update(SQL, namedParameters);
  
        if(status != 0){
            System.out.println("Employee data deleted for ID " + employeeID);
        }else{
            System.out.println("No Employee found with ID " + employeeID);
        }
   }

}
