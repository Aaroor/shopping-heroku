package com.shopping.heroku.service;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.shopping.heroku.model.Order;
@Service
public interface OrderService {
	public Iterable<Order> getAllOrders();
	public Order create(Order order);
	public void update(Order order);
	public Order createOrUpdateOrder(String ipAddress);
	public Order addToCard(int quantity,UUID productId,String ipAddress);
}
