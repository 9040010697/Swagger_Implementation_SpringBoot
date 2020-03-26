package com.ps.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.model.Product;
import com.ps.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	public List<Product> getAllProduct() {
		return productRepo.findAll();
	}

	public void addProduct(Product product) {
		productRepo.save(product);
	}

	public Product updateProduct(UUID productId, Product product) {
		Optional<Product> findById = productRepo.findById(productId);
		if (findById.isPresent()) {

			if (product.getProductName() != null)
				findById.get().setProductName(product.getProductName());

			if (product.getPrice() != null)
				findById.get().setPrice(product.getPrice());

			productRepo.save(findById.get());

			return findById.get();
		}

		throw new RuntimeException("Product Id not found !!!");
	}

	public void removeProduct(UUID productId) {
		Optional<Product> findById = productRepo.findById(productId);

		if (findById.isPresent()) {
			productRepo.delete(findById.get());
		} else {
			throw new RuntimeException("Product Id not found !!!");
		}

	}

	public Product getProductBuId(UUID productId) {
		Optional<Product> findById = productRepo.findById(productId);

		if (findById.isPresent()) {
			return findById.get();
		}

		throw new RuntimeException("Product Id not found !!!");
	}
}
