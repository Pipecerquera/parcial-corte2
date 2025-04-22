package com.backend_parcial.backend_parcial.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.backend_parcial.backend_parcial.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
