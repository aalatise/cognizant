package com.packt.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.org.domain.Comp;
import com.packt.org.domain.repository.CompRepository;
import com.packt.org.dto.CompDto;
import com.packt.org.service.CompService;

@Service
public class CompServiceImpl implements CompService{
   
   @Autowired
   private CompRepository compRepository;

   public void create(CompDto compDto) {
      compRepository.create(compDto);
   }

   @Override
   public Comp read(String id) {
      return compRepository.read(id);
   }

   @Override
   public void update(String id, CompDto compDto) {
      compRepository.update(id, compDto);
   }

   @Override
   public void delete(String id) {
      compRepository.delete(id);
   }

   @Override
   public void addItem(String compId, String employeeId) {
      compRepository.addItem(compId, employeeId);
   }

   @Override
   public void removeItem(String compId, String employeeId) {
      compRepository.removeItem(compId, employeeId);
   }
}
