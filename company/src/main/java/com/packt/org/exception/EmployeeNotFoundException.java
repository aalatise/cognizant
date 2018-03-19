package com.packt.org.exception;

public class EmployeeNotFoundException extends RuntimeException{

   private static final long serialVersionUID = -694354952032299587L;
   
   private String productId;

   public EmployeeNotFoundException(String productId) {
      this.productId = productId;
   }
   
   public String getProductId() {
      return productId;
   }

}
