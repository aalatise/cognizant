package com.packt.org.domain.repository;

import com.packt.org.domain.Comp;
import com.packt.org.dto.CompDto;

public interface CompRepository {

   void create(CompDto compDto);
   
   Comp read(String id);
   
   void update(String id, CompDto compDto);
   
   void delete(String id);

   void addItem(String cartId, String productId);

   void removeItem(String cartId, String productId);
}
