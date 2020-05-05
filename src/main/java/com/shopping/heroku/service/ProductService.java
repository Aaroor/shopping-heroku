package com.shopping.heroku.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.shopping.heroku.model.Products;
@Service
public interface ProductService {
	 public Iterable<Products> getAllProducts();
	 public Optional<Products> getProduct(UUID id);
	 public Products save(Products product);
}
