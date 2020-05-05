package com.shopping.heroku.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.heroku.model.Order;


@Repository
public interface OrderRepository  extends JpaRepository<Order, UUID> {
	public List<Order> findByIpAddressAndIsCompleteFalse(String ipAddress);
	public List<Order> findByDeliveryChargeNotAndIsCompleteTrue(double deliveryCharge);
	public List<Order> findByDeliveryChargeEqualsAndIsCompleteTrue(double deliveryCharge);
	
}
