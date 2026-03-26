package com.infosys.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.inventory.entity.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>{

    List<Product> findByNameContainingIgnoreCase(String keyword);

}