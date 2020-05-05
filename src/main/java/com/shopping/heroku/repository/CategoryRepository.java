package com.shopping.heroku.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.heroku.model.ShoppingCategory;
@Repository
public interface CategoryRepository extends JpaRepository<ShoppingCategory, UUID> {
	public List<ShoppingCategory> findByCategoryName(String name);
	public List<ShoppingCategory> findByIsShowTrue();
}
