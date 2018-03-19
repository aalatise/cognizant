package com.packt.org.service;
import com.packt.org.domain.Comp;
import com.packt.org.dto.CompDto;

public interface CompService {
   
   void create(CompDto compDto);
   
   Comp read(String compId);
   
   void update(String compId, CompDto compDto);
   
   void delete(String id);

   void addItem(String compId, String employeeId);

   void removeItem(String compId, String employeeId);
}
