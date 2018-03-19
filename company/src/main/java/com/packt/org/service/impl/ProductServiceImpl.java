package com.packt.org.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.org.domain.Employee;
import com.packt.org.domain.Product;
import com.packt.org.domain.repository.ProductRepository;
import com.packt.org.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

   @Autowired
   private ProductRepository productRepository;
   
   @Override
   public Product read(String id) {
      return productRepository.read(id);
   }
   
   @Override
   public void updateAllStock() {
      List<Product> allProducts = productRepository.getAllProducts();
     
      for(Product product : allProducts) {
         if(product.getUnitsInStock()<500)
        	 productRepository.updateStock(product.getProductId(), product.getUnitsInStock()+1000);
      }
   }
   @Override
   public List<Product> getAllProducts() {
         return productRepository.getAllProducts();
   }
   
   public List<Product> getProductsByCategory(String category) {
	   return productRepository.getProductsByCategory(category);
	}   
   
   public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
	      return productRepository.getProductsByFilter(filterParams);
	}
   
   @Override
   public Product getProductById(String productID) {
      return productRepository.getProductById(productID);
   }
   
   @Override
   public void addProduct(Product product) {
	   productRepository.addProduct(product);
   }
   

}
