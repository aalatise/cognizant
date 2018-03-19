package com.packt.org.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.packt.org.domain.Comp;
import com.packt.org.dto.CompDto;
import com.packt.org.service.CompService;



@RestController
@RequestMapping(value = "rest/comp")
public class CompRestController {

   @Autowired
   private CompService compService;
   
//   @RequestMapping(method = RequestMethod.POST)
//   @ResponseStatus(value = HttpStatus.CREATED)
//   public void create(@RequestBody CompDto compDto) {
//      compService.create(compDto);
//   }

   @RequestMapping(value = "/{compId}", method = RequestMethod.GET)
   public Comp read(@PathVariable(value = "compId") String compId) {
      return compService.read(compId);
   }

   @RequestMapping(value = "/{compId}", method = RequestMethod.PUT)
   @ResponseStatus(value = HttpStatus.OK)
   public void update(@PathVariable(value = "compId") String compId, @RequestBody CompDto compDto) {
      compDto.setId(compId);
      compService.update(compId, compDto);
   }

   @RequestMapping(value = "/{compId}", method = RequestMethod.DELETE)
   @ResponseStatus(value = HttpStatus.OK)
   public void delete(@PathVariable(value = "compId") String compId) {
      compService.delete(compId);
   }
      
   @RequestMapping(value = "/add/{employeeId}", method = RequestMethod.PUT)
   @ResponseStatus(value = HttpStatus.OK)
   public void addItem(@PathVariable String employeeId, HttpSession session) {
      compService.addItem(session.getId(),employeeId);
   }
   
   @RequestMapping(value = "/remove/{employeeId}", method = RequestMethod.PUT)
   @ResponseStatus(value = HttpStatus.OK)
   public void removeItem(@PathVariable String employeeId, HttpSession session) {
      compService.removeItem(session.getId(),employeeId);
   }
}
