package com.jphilips.onlinestore.service;

import java.util.List;

import com.jphilips.onlinestore.dto.ProductDTO;
import com.jphilips.onlinestore.entity.Product;

public interface ProductService {
	// Basic CRUD
	List<Product> findAllProducts();
	Product findProductById(int id);
	Product addProduct(ProductDTO productDTO);
	Product updateProduct(int id, ProductDTO productDTO);
	void deleteProduct(int id);
}
