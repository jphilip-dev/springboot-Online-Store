package com.jphilips.onlinestore.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jphilips.onlinestore.dto.ProductDTO;
import com.jphilips.onlinestore.entity.Product;
import com.jphilips.onlinestore.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@Override
	public Product findProductById(int id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product addProduct(ProductDTO productDTO) {
		Product product = ProductDTO.toEntity(productDTO);
		
		product.setImageFileName(saveImage(productDTO.getImageFile()));
		
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(int id,ProductDTO productDTO) {
		Product product = productRepository.findById(id).get();
		
		product.setName(productDTO.getName());
		product.setBrand(productDTO.getBrand());
		product.setCategory(productDTO.getCategory());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		
		if(!productDTO.getImageFile().isEmpty()) {
			product.setImageFileName(replaceImage(productDTO.getImageFile(), product.getImageFileName()));
		}
		
		
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(int id) {
		Product product = findProductById(id);
		productRepository.delete(product);
	}
	
	private String saveImage(MultipartFile image) {
	    String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();

	    try {
	        String dir = "src/main/resources/static/images/";
	        Path uploadPath = Paths.get(dir);

	        // Ensure the directory exists
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        // Save the file
	        try (InputStream inputStream = image.getInputStream()) {
	            Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
	        }

	    } catch (Exception e) {
	        // Log the exception (replace with your logger)
	        System.err.println("Error saving image: " + e.getMessage());
	        e.printStackTrace();
	        return null; // Return null if saving fails
	    }

	    return fileName;
	}
	
	private String replaceImage(MultipartFile newImage, String oldImageFileName) {
		String dir = "src/main/resources/static/images/";
		Path oldImagePath = Paths.get(dir + oldImageFileName);
		try {
			Files.delete(oldImagePath);
			
		}catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		
		//save new Image
		return saveImage(newImage);
	}
		

}
