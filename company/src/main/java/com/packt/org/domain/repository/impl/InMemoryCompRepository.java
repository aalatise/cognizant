package com.packt.org.domain.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.org.domain.Comp;
import com.packt.org.domain.CompResource;
import com.packt.org.domain.Employee;
import com.packt.org.domain.repository.CompRepository;
import com.packt.org.dto.CompDto;
import com.packt.org.dto.CompResourceDto;
import com.packt.org.service.EmployeeService;

@Repository
public class InMemoryCompRepository implements CompRepository{
   
   @Autowired
   private NamedParameterJdbcTemplate jdbcTempleate;
   
   @Autowired
   private EmployeeService employeeService;
   
   // method to call in order to perform RestFull Create
   public void create(CompDto compdto) {
	   	//define company insert statement
        String INSERT_COMP_SQL = "INSERT INTO COMPANY (ID) VALUES (:id)";
        
        //define a map to hold companies
   	  	Map<String, Object> compParams = new HashMap<String, Object>();
   	  	//store id of company to create
   	  	compParams.put("id", compdto.getId());
   	  	//create companies in hash Map
   	  	jdbcTempleate.update(INSERT_COMP_SQL, compParams);
   	  	//create relationship between employees and companies using company data transfer object 
        ((CompDto) compdto).getCompResources() // get list of employees
        				   .stream()           // create a stream with list
        				   .forEach(compResourceDto ->  //for employee in Resource DTO
        {
			//get employee object using employee service
        	Employee employee = employeeService.getEmployeeById(compResourceDto.getEmployeeId());
			 String INSERT_COMP_ITEM_SQL = "INSERT INTO COMP_RESOURCE(EMPLOYEE_ID,COMP_ID) "
			                           + "VALUES (:employee_id, :comp_id)";
			//define a map to hold employees
			 Map<String, Object> compItemsParams = new HashMap<String, Object>();
//			 compItemsParams.put("id", compResourceDto.getId());
			 compItemsParams.put("employee_id", employee.getEmployeeId());
			 compItemsParams.put("comp_id", compdto.getId());
			//         compItemsParams.put("quantity", compResourceDto.getQuantity());
			jdbcTempleate.update(INSERT_COMP_ITEM_SQL, compItemsParams); 
               
        }); 
     
   }
   
   @Override
   public Comp read(String id) {
//      String SQL = "SELECT * FROM CART WHERE ID = :id";  
	   String SQL = "SELECT * FROM COMPANY WHERE ID = :id";  
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", id);  
      CompMapper compMapper = new CompMapper(jdbcTempleate, employeeService);
      
      try {
         return jdbcTempleate.queryForObject(SQL, params, compMapper);
      } catch (EmptyResultDataAccessException e) {
            return null;
      }      
   }
   
   
//   @Override
//   public void update(String id, CompDto compDto) {
//      
//      List<CompResourceDto> cartItems = compDto.getCartItems();
//      for(CompResourceDto cartItem :cartItems) {
//         
//         String   SQL = "UPDATE CART_ITEM SET QUANTITY = :quantity,  PRODUCT_ID = :productId WHERE ID = :id AND CART_ID = :cartId";
//         Map<String, Object> params = new HashMap<String, Object>();
//         params.put("id", cartItem.getId());
//         params.put("quantity", cartItem.getQuantity());  
//         params.put("productId", cartItem.getProductId());  
//         params.put("cartId", id);  
//      
//         jdbcTempleate.update(SQL, params);  
//      }   
//   }
   
	 @Override
	 public void update(String id, CompDto compDto) {
	    
	    List<CompResourceDto> compItems = compDto.getCompResources();
	    for(CompResourceDto compItem :compItems) {
	       
	       String   SQL = "UPDATE COMP_RESOURCE SET QUANTITY = :quantity,  EMPLOYEE_ID = :employeeId WHERE ID = :id AND COMP_ID = :compId";
	       Map<String, Object> params = new HashMap<String, Object>();
	       params.put("id", compItem.getId());
	       params.put("quantity", compItem.getQuantity());  
	       params.put("employeeId", compItem.getEmployeeId());  
	       params.put("compId", id);  
	    
	       jdbcTempleate.update(SQL, params);  
	    }   
	 }

   @Override
   public void delete(String id) {
//      String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
//      String SQL_DELETE_CART = "DELETE FROM CART WHERE ID = :id";
      
	   String SQL_DELETE_COMP_ITEM = "DELETE FROM COMP_RESOURCE WHERE EMPLOYEE_ID = :id";
	   String SQL_DELETE_COMP = "DELETE FROM COMPANY WHERE ID = :id";
     
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", id);
   
      jdbcTempleate.update(SQL_DELETE_COMP_ITEM, params);  
      jdbcTempleate.update(SQL_DELETE_COMP, params);  
   }
   
//   @Override
//   public void addItem(String cartId, String productId) {
//      
//      String SQL=null;
//      Comp comp = null;
//      
//      comp = read(cartId);
//      if(comp ==null) {
//         CompResourceDto newCartItemDto = new CompResourceDto();
//         newCartItemDto.setId(cartId+productId);
//         newCartItemDto.setProductId(productId);
//         newCartItemDto.setQuantity(1);
//         
//         CompDto newCartDto = new CompDto(cartId);
//         newCartDto.addCompResource(newCartItemDto);
//         create(newCartDto);
//         return;
//      }
   
   @Override
   public void addItem(String compId, String employeeId) {
      
      String SQL=null;
      Comp comp = null;
      
      comp = read(compId);
      if(comp ==null) {
         CompResourceDto newCompItemDto = new CompResourceDto();
         newCompItemDto.setId(compId+employeeId);
         newCompItemDto.setEmployeeId(employeeId);
         newCompItemDto.setQuantity(1);
         
         CompDto newCompDto = new CompDto(compId);
         newCompDto.addCompResource(newCompItemDto);
         create(newCompDto);
         return;
      }
      
      Map<String, Object> compItemsParams = new HashMap<String, Object>();
      
      
     	  
      if(comp.getItemByEmployeeId(employeeId) == null) {
         SQL = "INSERT INTO COMP_RESOURCE (ID, EMPLOYEE_ID , COMP_ID , QUANTITY) VALUES (:id, :employeeId, :compId, :quantity)";
         compItemsParams.put("id", compId+employeeId);
         compItemsParams.put("quantity", 1);
      } else {
         SQL = "UPDATE COMP_RESOURCE SET QUANTITY = :quantity WHERE COMP_ID = :compId AND EMPLOYEE_ID = :employeeId";
         CompResource existingItem = comp.getItemByEmployeeId(employeeId);
         compItemsParams.put("id", existingItem.getId());
//         compItemsParams.put("quantity", existingItem.getQuantity()+1);
      }
      
      compItemsParams.put("employeeId", employeeId);
      compItemsParams.put("compId", compId);
      
      jdbcTempleate.update(SQL, compItemsParams);     
   }

//   @Override
//   public void removeItem(String cartId, String productId) {
//      String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE PRODUCT_ID = :productId AND CART_ID = :id";
//      
//      Map<String, Object> params = new HashMap<String, Object>();   
//      params.put("id", cartId);
//      params.put("productId", productId);
//      
//      jdbcTempleate.update(SQL_DELETE_CART_ITEM, params);  
//   }
   
   @Override
   public void removeItem(String compId, String employeeId) {
      String SQL_DELETE_COMP_ITEM = "DELETE FROM COMP_RESOURCE WHERE EMPLOYEE_ID  = :employeeId AND COMP_ID = :id";
      
      Map<String, Object> params = new HashMap<String, Object>();   
      params.put("id", compId);
      params.put("employeeId", employeeId);
      
      jdbcTempleate.update(SQL_DELETE_COMP_ITEM, params);  
   }
}
