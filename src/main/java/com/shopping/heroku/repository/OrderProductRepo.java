package com.shopping.heroku.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.heroku.model.OrderProduct;
import com.shopping.heroku.model.OrderProductPK;
import com.shopping.heroku.model.ShoppingCategory;

public interface OrderProductRepo extends JpaRepository<OrderProduct, OrderProductPK> {
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM order_product WHERE product_id=:productId AND order_id=:orderId", nativeQuery = true)
	void deleteOrderProduct(@Param("productId") String productId, @Param("orderId") String orderId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE order_product SET quantity=:quantity WHERE product_id=:productId AND order_id=:orderId", nativeQuery = true)
	void updateOrderProductQuantity(@Param("productId") String productId, @Param("orderId") String orderId,@Param("quantity") int quantity);
}
