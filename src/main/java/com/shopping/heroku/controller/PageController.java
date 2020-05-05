package com.shopping.heroku.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.heroku.model.Order;
import com.shopping.heroku.model.Products;
import com.shopping.heroku.model.ShoppingCategory;
import com.shopping.heroku.repository.CategoryRepository;
import com.shopping.heroku.repository.OrderRepository;
import com.shopping.heroku.repository.ProductsRepository;
import com.shopping.heroku.service.OrderService;
import com.shopping.heroku.service.UtilService;

@Controller
@RequestMapping(value="/view")
public class PageController {

	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	ProductsRepository productRepo;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	UtilService utilService;
	@Autowired
	OrderService orderService;
	
	
	@GetMapping(value="/shop")
	public String shop(Model model,HttpServletRequest request) {
		List<Products> lProducts= productRepo.findByIsShowTrue();
		List<ShoppingCategory> lCategory = categoryRepo.findByIsShowTrue();
	//	Optional<Order> orders= orderRepo.findById(1L);
		model.addAttribute("orders", orderService.createOrUpdateOrder(utilService.findClientIp(request)));
		model.addAttribute("lProducts", lProducts);
		model.addAttribute("lCategory", lCategory);
		return "shop";
	}
	
	@GetMapping(value="/continue/{orderId}")
	public String shopContinueOrder(@PathVariable UUID orderId,Model model,HttpServletRequest request) {
		List<Products> lProducts= productRepo.findByIsShowTrue();
		List<ShoppingCategory> lCategory = categoryRepo.findByIsShowTrue();
	//	Optional<Order> orders= orderRepo.findById(1L);
		model.addAttribute("orders", orderRepo.findById(orderId).get());
		model.addAttribute("lProducts", lProducts);
		model.addAttribute("lCategory", lCategory);
		return "shop";
	}
	
	@GetMapping(value="/product/{id}")
	public String shop(@PathVariable UUID id,Model model,HttpServletRequest request) {
		System.out.println("## Category id : "+id);
		List<Products> lProducts= productRepo.findByShoppingCategoryId(id);
		List<ShoppingCategory> lCategory = categoryRepo.findByIsShowTrue();
		//Optional<Order> orders= orderRepo.findById(1L);
		model.addAttribute("orders", orderService.createOrUpdateOrder(utilService.findClientIp(request)));
		model.addAttribute("lProducts", lProducts);
		model.addAttribute("lCategory", lCategory);
		model.addAttribute("total",0);
		System.out.println("## list of product : "+lProducts);
		return "shop";
	}
	
	@GetMapping(value="/shopping-cart")
	public String shoppingCart() {
		return "shopping-cart";
	}
	
	@GetMapping(value="/shopping-cart/{orderId}")
	public String shoppingCartForOrder(@PathVariable UUID orderId,Model model,HttpServletRequest request) {
		Optional<Order> optionalOrder = orderRepo.findById(orderId);
		if(optionalOrder.isPresent() && !optionalOrder.get().isComplete()) {
			List<ShoppingCategory> lCategory = categoryRepo.findByIsShowTrue();
			model.addAttribute("lCategory", lCategory);
			model.addAttribute("orders",optionalOrder.get());
			return "shopping-cart";
		}else {
			return "redirect:/shop";
		}
		
		
		
	}
	
	@GetMapping(value="/check-out/{orderId}")
	public String checkOutForOrder(@PathVariable UUID orderId,Model model,HttpServletRequest request) {
		
		Optional<Order> optionalOrder = orderRepo.findById(orderId);
		if(optionalOrder.isPresent() && !optionalOrder.get().isComplete()) {
			List<ShoppingCategory> lCategory = categoryRepo.findByIsShowTrue();
			model.addAttribute("lCategory", lCategory);
			model.addAttribute("orders",optionalOrder.get());
			return "check-out";
		}else {
			return "redirect:view/shop";
		}
		
	}
	
	@GetMapping(value="/order-confirmed/{orderId}")
	public String deliveryConfirmed(@PathVariable UUID orderId,Model model,HttpServletRequest request) {
		List<ShoppingCategory> lCategory = categoryRepo.findByIsShowTrue();
		model.addAttribute("lCategory", lCategory);
		model.addAttribute("orders",orderRepo.findById(orderId).get());
		return "delivery-success";
	}
	
	@GetMapping(value="/cart-payment/{orderId}")
	public String cartPayment(@PathVariable UUID orderId,Model model,HttpServletRequest request) {
		Optional<Order> optionalOrder = orderRepo.findById(orderId);
		if(optionalOrder.isPresent() && !optionalOrder.get().isComplete()) {
			List<ShoppingCategory> lCategory = categoryRepo.findByIsShowTrue();
			model.addAttribute("lCategory", lCategory);
			model.addAttribute("orders",optionalOrder.get());
			return "card-payment";
		}else {
			return "redirect:view/shop";
		}
		
	}
	
	@GetMapping(value="/check-out")
	public String checkOut() {
		return "check-out";
	}
	
}
