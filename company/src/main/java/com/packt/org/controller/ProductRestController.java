package com.packt.org.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.packt.org.domain.Comp;
import com.packt.org.domain.Product;
import com.packt.org.domain.repository.ProductRepository;
import com.packt.org.dto.CompDto;
import com.packt.org.service.ProductService;


@RestController
@RequestMapping(value = "rest/market")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;

  @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
   public Product read(@PathVariable(value = "productId") String productId) {
      return productService.read(productId);
   }
	  
  
//  @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
//  @ResponseStatus(value = HttpStatus.OK)
//  public void update(@PathVariable(value = "productId") String productId) {
//     
//     compService.update(compId, compDto);
//  }
  
  
	@RequestMapping("/products")
	public String list(Model model) {
	   model.addAttribute("products", productService.getAllProducts());
	   return "products";
	}
	
	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
	   productService.updateAllStock();
	   return "redirect:/products";
	}
	
	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
	   model.addAttribute("products", productService.getProductsByCategory(productCategory));
	   return "products";
	}
	
	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable(pathVar="params") Map<String,List<String>> filterParams, Model model) {
	   model.addAttribute("products", productService.getProductsByFilter(filterParams));
	   return "products";
	}
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
	   model.addAttribute("product", productService.getProductById(productId));
	   return "product";
	}
	
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
	   Product newProduct = new Product();
	   model.addAttribute("newProduct", newProduct);
	   return "addProduct";
	}
	   
	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result) {
		
		String[] suppressedFields = result.getSuppressedFields();
		   if (suppressedFields.length > 0) {
		      throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		   }
	
	   productService.addProduct(newProduct);
	   return "redirect:/rest/market/products";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
	   binder.setAllowedFields("productId",
	            "name",
	            "unitPrice",
	            "description",
	            "manufacturer",
	            "category",
	            "unitsInStock",
	            "condition");
	}
}
