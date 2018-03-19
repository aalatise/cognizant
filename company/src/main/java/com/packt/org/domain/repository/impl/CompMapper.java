package com.packt.org.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.packt.org.domain.Comp;
import com.packt.org.domain.CompResource;
import com.packt.org.service.EmployeeService;

public class CompMapper implements RowMapper<Comp> {
   private CompItemMapper compItemMapper;
   private NamedParameterJdbcTemplate jdbcTempleate;
   
   public CompMapper(NamedParameterJdbcTemplate jdbcTempleate, EmployeeService employeeService) {
      this.jdbcTempleate = jdbcTempleate;
      compItemMapper = new CompItemMapper(employeeService);
   }

   public Comp mapRow(ResultSet rs, int rowNum) throws SQLException {
      String id = rs.getString("ID");
	   
//	    String id = rs.getString("COMP_ID");
        Comp comp = new Comp(id);
        
//        String SQL = String.format("SELECT * FROM CART_ITEM WHERE CART_ID = '%s'", id);
        String SQL = String.format("SELECT * FROM COMP_RESOURCE WHERE COMP_ID = '%s'", id);
        List<CompResource> compResources = jdbcTempleate.query(SQL, compItemMapper);
        comp.setCompResources(compResources);
        return comp;
   }
}
