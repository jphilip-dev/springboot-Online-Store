package com.jphilips.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jphilips.onlinestore.dto.ProductDTO;
import com.jphilips.onlinestore.entity.Product;
import com.jphilips.onlinestore.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	ProductService productService;

	@GetMapping({ "", "/" })
	public String getAllProducts(Model model) {

		model.addAttribute("products", productService.findAllProducts());

		return "products/index";
	}

	@GetMapping("/create")
	public String showCreatePage(Model model) {
		ProductDTO productDTO = new ProductDTO();
		model.addAttribute("productDTO", productDTO);

		return "products/create-product";
	}

	@PostMapping("/create")
	public String createProduct(@Valid @ModelAttribute ProductDTO productDTO, BindingResult bindingResult) {

		if (productDTO.getImageFile().isEmpty()) {
			bindingResult.addError(new FieldError("productDTO", "imageFile", "The Image file is empty"));
		}

		if (bindingResult.hasErrors()) {
			return "products/create-product";
		}
		
		productService.addProduct(productDTO);

		return "redirect:/products";
	}
	
	@GetMapping("/edit")
	public String showEditPage(Model model, @RequestParam int id) {
		
		try {
			Product product = productService.findProductById(id);
			model.addAttribute("product",product);
			
			ProductDTO productDTO = ProductDTO.fromEntity(product);
			model.addAttribute("productDTO",productDTO);
			
		}catch (Exception e) {
			System.err.println("Exception: " +  e.getMessage());
			return "redirect:/products";
		}
		
		return "products/edit-products";
	}
	
	@PostMapping("/edit")
	public String updateProduct(
			Model model,
			@RequestParam int id,
			@Valid @ModelAttribute ProductDTO productDTO, 
			BindingResult bindingResult) {
		
		try {
			Product product = productService.findProductById(id);
			model.addAttribute("product",product);
			
			
			if(bindingResult.hasErrors()) {
				return "products/edit-products"; 
			}
			
			productService.updateProduct(id,productDTO);
			
		}catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		
		
		
		return "redirect:/products";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam int id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

}
