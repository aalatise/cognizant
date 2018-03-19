package com.packt.org.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Employee implements Serializable {

	private static final long serialVersionUID = 3678107792576131001L;

	private String employeeId;
	private String name;
	private String description;
	private long salary; 
	private boolean inactive; 

	public Employee() {
		super();
	}

	public Employee(String employeeId, String name, long salary) {
		this.employeeId = employeeId;
		this.name = name;
		this.salary = salary;
	}
	
	public Employee(String employeeId, String name, String description, long salary,boolean inactive) {
		this.employeeId = employeeId;
		this.name = name;
		this.description=description;
		this.salary = salary;
		this.inactive=inactive;
	}
	
	public boolean getInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	
	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		return result;
	}

}
