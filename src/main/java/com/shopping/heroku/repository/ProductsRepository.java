package com.shopping.heroku.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.heroku.model.Order;
import com.shopping.heroku.model.Products;
@Repository
@Transactional
public interface ProductsRepository extends JpaRepository<Products, UUID> {
	 Optional<Products> findById(UUID Id);
	 List<Products> findByShoppingCategoryId(UUID categoryId);
	 List<Products> findByIsShowTrue();
}
