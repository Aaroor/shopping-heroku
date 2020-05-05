package com.shopping.heroku.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.heroku.model.Products;
import com.shopping.heroku.repository.ProductsRepository;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductsRepository productsRepo;
	@Override
	public Iterable<Products> getAllProducts() {
		return productsRepo.findAll();
	}

	@Override
	public Optional<Products> getProduct(UUID id) {
		 return productsRepo
		          .findById(id);
	}

	@Override
	public Products save(Products product) {
		return productsRepo.save(product);
	}

}
