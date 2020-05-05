package com.shopping.heroku.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.heroku.model.Order;
import com.shopping.heroku.model.OrderProduct;
import com.shopping.heroku.model.Products;
import com.shopping.heroku.repository.OrderProductRepo;
import com.shopping.heroku.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ProductService productService;
	@Autowired
	OrderProductRepo orderProductRepo;
	@Override
	public Iterable<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	@Override
	public Order create(Order order) {
		return this.orderRepository.save(order);
	}

	@Override
	public void update(Order order) {
		this.orderRepository.save(order);
		
	}

	@Override
	public Order createOrUpdateOrder(String ipAddress) {
		//check last order exist
		List<Order> lExistOrders = this.orderRepository.findByIpAddressAndIsCompleteFalse(ipAddress);
		Order addToCardOrder;
		if(lExistOrders.isEmpty()) {
			Order order = new Order(ipAddress,false,10.00);
			addToCardOrder=this.create(order);
		}else {
			addToCardOrder = lExistOrders.get(0);
		}
		return addToCardOrder;
	}

	@Override
	public Order addToCard(int quantity, UUID productId,String ipAddress) {
		List<OrderProduct> lOrderProducts = new ArrayList<OrderProduct>();
		Order successOrder = null;
		Order addToCardOrder = createOrUpdateOrder(ipAddress);
		Optional<Products> optionalProduct = productService.getProduct(productId);
		if(optionalProduct.isPresent()) {
			Products product = optionalProduct.get();
			OrderProduct orderProduct = new OrderProduct(addToCardOrder,product,quantity);
			orderProductRepo.save(orderProduct);
			lOrderProducts.add(orderProduct);
			addToCardOrder.setOrderProducts(lOrderProducts);
			successOrder=create(addToCardOrder);
		}
		return successOrder;
	}

}
