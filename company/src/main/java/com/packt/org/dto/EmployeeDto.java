package com.packt.org.dto;

import java.io.Serializable;




public class EmployeeDto implements Serializable{

   private static final long serialVersionUID = -2018182726290898588L;

   private String id;
   private long salary; 
	private String name;
	private String description;
	private boolean inactive; 
	
   public EmployeeDto() {}

   public EmployeeDto(String id, long salary) {
      this.id = id;
      this.salary = salary;
   }

   public EmployeeDto(String employeeId, String name, String description, long salary,boolean inactive) {
		this.id = employeeId;
		this.name = name;
		this.description=description;
		this.salary = salary;
		this.inactive=inactive;
	}
   
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
 }

