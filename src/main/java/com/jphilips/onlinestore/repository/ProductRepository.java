package com.jphilips.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jphilips.onlinestore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
