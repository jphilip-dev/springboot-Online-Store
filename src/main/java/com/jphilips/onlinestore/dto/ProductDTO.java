package com.jphilips.onlinestore.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.jphilips.onlinestore.entity.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {
	
	@NotBlank(message = "The name is required")
	private String name;
	@NotBlank(message = "The brand is required")
	private String brand;
	@NotBlank(message = "The category is required")
	private String category;
	
	@Min(0)
	private double price;
	
	@Size(min = 10, message = "The description should be at least 10 characters")
	@Size(max = 2000, message = "The description cannot exceed 2000 characters")
	private String description;
	
	private MultipartFile imageFile;
	
	public static Product toEntity(ProductDTO productDTO) {
		Product product = new Product();
		
		product.setName(productDTO.name);
		product.setBrand(productDTO.brand);
		product.setCategory(productDTO.category);
		product.setPrice(productDTO.price);
		product.setDescription(productDTO.description);
		product.setCreatedAt(LocalDate.now());
		
		return product;
	}
	
	public static ProductDTO fromEntity(Product product) {
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setName(product.getName());
		productDTO.setBrand(product.getBrand());
		productDTO.setCategory(product.getCategory());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		
		return productDTO;
	}
	
}
