package com.packt.org.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public class Comp implements Serializable{

   private static final long serialVersionUID = 6554623865768217431L;

   private String id;
   private List<CompResource> compResources;
   private BigDecimal grandTotal;

   public Comp(String id) {
      this.id = id;
   }

   public String getId() {
      return id;
   }

//   public BigDecimal getGrandTotal() {
//      updateGrandTotal();
//      return grandTotal;
//   }

   public void setGrandTotal(BigDecimal grandTotal) {
      this.grandTotal = grandTotal;
   }

   public List<CompResource> getCompResources() {
      return compResources;
   }

   public void setCompResources(List<CompResource> compResources) {
      this.compResources = compResources;
   }

   
	 public CompResource getItemByEmployeeId(String employeeId) {
	   return compResources.stream().filter(compItem -> compItem.getEmployee().getEmployeeId().equals(employeeId))
	                      .findAny()
	                      .orElse(null);
	}
   
//   public CompResource getItemByEmployeeId(String employeeId) {
//	      return compResources.stream().filter(cartItem -> cartItem.getProduct().getEmployeeId().equals(employeeId))
//	                         .findAny()
//	                         .orElse(null);
//   }
   
   
   
//   public CompResource getItemByProductId(String productId) {
//      return compResources.stream().filter(cartItem -> compResources.getProduct().getEmployeeId().equals(productId))
//                         .findAny()
//                         .orElse(null);
//   }

//   public void updateGrandTotal() {
//
//      Function<CompResource, BigDecimal> totalMapper = cartItem -> cartItem.getTotalPrice();
//
//      BigDecimal grandTotal = compResources.stream()
//                                 .map(totalMapper)
//                                 .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//      this.setGrandTotal(grandTotal);
//   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Comp other = (Comp) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      return true;
   }


}
