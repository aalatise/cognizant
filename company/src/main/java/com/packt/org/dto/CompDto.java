package com.packt.org.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompDto implements Serializable{

   private static final long serialVersionUID = -2017182726290898588L;

   private String id;
   private List<CompResourceDto> compItems;  //has a list of company resources

   public CompDto() {}

   public CompDto(String id) {
      this.id = id;
      compItems = new ArrayList<>();
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setCompResources(List<CompResourceDto> compItems) {
      this.compItems = compItems;
   }

   public List<CompResourceDto> getCompResources() {
      return compItems;
   }

   public List<CompResourceDto> EMPLOYEE_ID() {
      return compItems;
   }

   
   public void addCompResource(CompResourceDto compResourceDto) {
      this.compItems.add(compResourceDto);
   }
   
   

//   public Collection<CompResourceDto> getCompResource() {
//	// TODO Auto-generated method stub
//	   return compItems;
//   }

 
}
