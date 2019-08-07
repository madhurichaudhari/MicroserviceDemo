package com.microservice.MicroServiceApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductValueRepository productRepository;

	public List<Product> listAll() {
		return productRepository.findAll();
	}
	public Product save(Product product) {
		return	productRepository.save(product);
		
	}
	
	
	public Product findOne(Long id) throws UserFoundNotException {
		return productRepository.findById(id).get();
	}
	
	public  void delete(Long id) {
		 productRepository.deleteById(id);
	}
	
	/*
	 * public Product get(Long id) { return productRepository.findById(id).get(); }
	 * public void delete(Long id) { productRepository.deleteById(id); }
	 */
}
