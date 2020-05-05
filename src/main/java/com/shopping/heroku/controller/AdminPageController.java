package com.shopping.heroku.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.heroku.repository.CategoryRepository;
import com.shopping.heroku.repository.OrderRepository;
import com.shopping.heroku.repository.ProductsRepository;
import com.shopping.heroku.repository.UserRepository;
import com.shopping.heroku.service.OrderService;
import com.shopping.heroku.service.UtilService;

@Controller
@RequestMapping(value="/admin")
public class AdminPageController {
	
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
	@Autowired
	UserRepository userRepo;
	
	@GetMapping(value="/home")
	public String adminHome(Model model,HttpServletRequest request) {
		model.addAttribute("lProducts", productRepo.findAll());
		return "admin-home";
	}
	
	@GetMapping(value="/product-list")
	public String productList(Model model,HttpServletRequest request) {
		model.addAttribute("lProducts", productRepo.findAll());
		return "admin-home";
	}
	
	@GetMapping(value="/user-list")
	public String userList(Model model,HttpServletRequest request) {
		model.addAttribute("lusers", userRepo.findAll());
		return "user-list";
	}
	@GetMapping(value="/category-list")
	public String categoryList(Model model,HttpServletRequest request) {
		System.out.println(categoryRepo.findAll());
		model.addAttribute("lcategories", categoryRepo.findAll());
		return "product-category";
	}
	
	@GetMapping(value="/add-product")
	public String addProduct(Model model,HttpServletRequest request) {
		System.out.println(categoryRepo.findAll());
		model.addAttribute("lCategories", categoryRepo.findAll());
		return "add-product";
	}
	
	@GetMapping(value="/edit-product/{productId}")
	public String editProduct(@PathVariable UUID productId,Model model,HttpServletRequest request) {
		System.out.println(categoryRepo.findAll());
		model.addAttribute("lCategories", categoryRepo.findAll());
		model.addAttribute("product", productRepo.findById(productId).get());
		return "edit-product";
	}
	
	@GetMapping(value="/edit-user/{userId}")
	public String editUser(@PathVariable UUID userId,Model model,HttpServletRequest request) {
		model.addAttribute("user", userRepo.findById(userId).get());
		return "update-admin-user";
	}
	
	@GetMapping(value="/delivery-order")
	public String deliveryOrder(Model model,HttpServletRequest request) {
		model.addAttribute("lorders", orderRepo.findByDeliveryChargeNotAndIsCompleteTrue(0.0));
		return "delivery-order";
	}
	
	@GetMapping(value="/pickup-order")
	public String pickupOrder(Model model,HttpServletRequest request) {
		model.addAttribute("lorders", orderRepo.findByDeliveryChargeEqualsAndIsCompleteTrue(0.0));
		return "pickup-order";
	}

}
