package com.packt.org.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/comp")
public class CompController {

   @RequestMapping
   public String get(HttpServletRequest request) {
      return "redirect:/comp/"+request.getSession(true).getId();
   }
   
   @RequestMapping(value = "/{compId}", method = RequestMethod.GET)
   public String getCart(@PathVariable(value = "compId") String compId, Model model) {
      model.addAttribute("compId",compId);
      return "comp";
   }
}
