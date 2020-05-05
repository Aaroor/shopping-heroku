package com.shopping.heroku.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.heroku.model.BillingDetails;
import com.shopping.heroku.model.Order;
import com.shopping.heroku.model.OrderProduct;
import com.shopping.heroku.model.PaymentDetails;
import com.shopping.heroku.model.Products;
import com.shopping.heroku.model.Response;
import com.shopping.heroku.model.ShoppingCategory;
import com.shopping.heroku.model.User;
import com.shopping.heroku.repository.BillingRepository;
import com.shopping.heroku.repository.CategoryRepository;
import com.shopping.heroku.repository.OrderProductRepo;
import com.shopping.heroku.repository.OrderRepository;
import com.shopping.heroku.repository.PaymentRepository;
import com.shopping.heroku.repository.ProductsRepository;
import com.shopping.heroku.repository.UserRepository;
import com.shopping.heroku.request.AddToCardRequest;
import com.shopping.heroku.request.BillingRequest;
import com.shopping.heroku.request.CategoryWrapper;
import com.shopping.heroku.request.ProductDetailsRequest;
import com.shopping.heroku.request.ProductFormWrapper;
import com.shopping.heroku.request.UserFormWrapper;
import com.shopping.heroku.service.FileStorage;
import com.shopping.heroku.service.OrderService;
import com.shopping.heroku.service.UtilService;

@RestController
@RequestMapping("/api/shopping")
public class RestShoppingController {
	@Autowired
	OrderService orderService;
	@Autowired
	UtilService utilService;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderProductRepo orderProductRepo;
	@Autowired
	BillingRepository billingRepo;
	@Autowired
	PaymentRepository paymentRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired 
	FileStorage fileStorage;
	
	@Autowired
	ProductsRepository prodRepo;

	@PostMapping(value = "/add-to-card")
	public Response postCustomer(@RequestBody AddToCardRequest request, HttpServletRequest serveletRequest) {
		System.out.println("quantity : " + request.getQuantity());
		System.out.println("product Id : " + request.getProductId());
		Order order = orderService.addToCard(request.getQuantity(), request.getProductId(),
				utilService.findClientIp(serveletRequest));
		// Create Response Object
		Response response = new Response("Done", order);
		return response;
	}

	@PostMapping(value = "/remove-add-to-card")
	public Response postRemoveProduct(@RequestBody AddToCardRequest request, HttpServletRequest serveletRequest) {
		System.out.println("orderId : " + request.getOrderId());
		System.out.println("product Id : " + request.getProductId());
		orderProductRepo.deleteOrderProduct(request.getProductId().toString(), request.getOrderId().toString());
		// Create Response Object
		Response response = new Response("Done", request);
		return response;
	}

	@PostMapping(value = "/update-quantity")
	public Response updateOrderProductQuantity(@RequestBody AddToCardRequest request,
			HttpServletRequest serveletRequest) {
		System.out.println("orderId : " + request.getOrderId());
		System.out.println("product Id : " + request.getProductId());
		System.out.println("quantity : " + request.getQuantity());
		orderProductRepo.updateOrderProductQuantity(request.getProductId().toString(), request.getOrderId().toString(),
				request.getQuantity());
		// orderProductRepo.deleteOrderProduct(request.getProductId(),
		// request.getOrderId());
		// Create Response Object
		Response response = new Response("Done", orderRepository.findById(request.getOrderId()));
		return response;
	}

	@GetMapping(value = "/order/{id}")
	public Response getOrder(@PathVariable UUID id, HttpServletRequest serveletRequest) {
		System.out.println("Id : " + id);
		// Create Response Object
		Optional<Order> or = orderRepository.findById(id);
		System.out.println("product size : ###" + or.get().getOrderProducts().size());
		// System.out.println("product size : "+order.getOrderProducts().size());
		Response response = new Response("Done", or.get());
		return response;
	}

	@PostMapping(value = "/add-billing-details")
	public Response addBillingDetails(@RequestBody BillingRequest request, HttpServletRequest serveletRequest) {
		// System.out.println("orderId : " + request.getOrderId());
		// System.out.println("product Id : " + request.getProductId());
		// System.out.println("quantity : " + request.getQuantity());
		// orderProductRepo.updateOrderProductQuantity(request.getProductId().toString(),
		// request.getOrderId().toString(), request.getQuantity());
		// orderProductRepo.deleteOrderProduct(request.getProductId(),
		// request.getOrderId());
		// Create Response Object
		System.out.println("added billing details backend " + request.getOrderId());
		List<Order> lOrder = new ArrayList<Order>();
		Order order = orderRepository.findById(request.getOrderId()).get();
		lOrder.add(order);
		BillingDetails billingDetails = null;
		if (order.getBillingDetails() == null) {
			billingDetails = new BillingDetails(request.getFirstName(), request.getLastName(),
					request.getPostalZipCode(), request.getTownOrCity(), request.getEmailAddress(),
					request.getPhoneNumber(), request.getAddressLine1(), request.getAddressLine2(),
					request.getCountry(), lOrder);
		} else {
			billingDetails = order.getBillingDetails();
			billingDetails.setFirstName(request.getFirstName());
			billingDetails.setLastName(request.getLastName());
			billingDetails.setPostalZipCode(request.getPostalZipCode());
			billingDetails.setTownOrCity(request.getTownOrCity());
			billingDetails.setEmailAddress(request.getEmailAddress());
			billingDetails.setPhoneNumber(request.getPhoneNumber());
			billingDetails.setAddressLine1(request.getAddressLine1());
			billingDetails.setAddressLine2(request.getAddressLine2());
			billingDetails.setCountry(request.getCountry());
		}
		billingRepo.save(billingDetails);
		order.setBillingDetails(billingDetails);
		orderRepository.save(order);

//		if (request.isCashOnDelivery()) {
//			Order or = lOrder.get(0);
//			or.setComplete(true);
//			orderRepository.save(or);
//		}
		Response response = new Response("Done", request);
		return response;
	}

	@PostMapping(value = "/add-cart-payment")
	public Response addCartPayment(@RequestBody BillingRequest request, HttpServletRequest serveletRequest) {
		System.out.println("added billing details backend " + request.getOrderId());
		List<Order> lOrder = new ArrayList<Order>();
		Order order = orderRepository.findById(request.getOrderId()).get();
		lOrder.add(order);
		PaymentDetails payment = new PaymentDetails("Cart Payment",
				lOrder.get(0).getTotalOrderPriceWithDeliveryCharge(), true, lOrder.get(0));
		paymentRepo.save(payment);
		order.setPaymentDetails(payment);
		order.setComplete(true);
//		Order or = lOrder.get(0);
//		or.setComplete(true);
		orderRepository.save(order);
		Response response = new Response("Done", order);
		return response;
	}

	@PostMapping(value = "/add-delivery-type")
	public Response addDeliveryTypeToOrder(@RequestBody AddToCardRequest request, HttpServletRequest serveletRequest) {
		System.out.println("request " + request);
		System.out.println("order Id " + request.getOrderId());
		System.out.println("deliveryType " + request.getDeliveryType());
		Order order = orderRepository.findById(request.getOrderId()).get();
		if (request.getDeliveryType().toString().equalsIgnoreCase("delivery")) {
			order.setDeliveryCharge(10.00);
		} else if (request.getDeliveryType().toString().equalsIgnoreCase("pickup")) {
			order.setDeliveryCharge(0.0);
		}
		orderRepository.save(order);
		Response response = new Response("Done", request);
		return response;
	}

	@PostMapping(value = "/add-product")
	public Response addProductDetails(@RequestPart(value = "productImgFile") MultipartFile multipartFile,@RequestPart(value = "productImgFile2") MultipartFile multipartFile2,@ModelAttribute ProductFormWrapper model)
			throws IOException {

		 try {
			 	//fileStorage.init();
			    UUID uuid = UUID.randomUUID();
			 	String fileName = uuid.toString()+multipartFile.getOriginalFilename();
			 	String fileName2 = uuid.toString()+multipartFile2.getOriginalFilename();
		       // fileStorage.store(multipartFile,fileName);
		       // fileStorage.store(multipartFile2,fileName2);
		        Products pro1=new Products(model.getProductName(),model.getUnitPrice(),"/uploads/"+fileName,"/uploads/"+fileName2,categoryRepo.findById(UUID.fromString(model.getProductCategory())).get(),Boolean.parseBoolean(model.getProdShow()),model.getQuantity(),multipartFile.getBytes(),multipartFile2.getBytes());
		        prodRepo.save(pro1);
		        Response response = new Response("Done", model);
				return response;
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	Response response = new Response("Failure", model);
				return response;
		    } 
		
	}
	
	@PostMapping(value = "/add-product-visibility")
	public Response addProductVisibility(@RequestBody AddToCardRequest request) {
		Products exeProd = prodRepo.findById(request.getProductId()).get();
		exeProd.setShow(Boolean.parseBoolean(request.getIsShow()));
		prodRepo.save(exeProd);
		Response response = new Response("Done", request);
		return response;
	}
	
	@PostMapping(value = "/add-category-visibility")
	public Response addCategoryVisibility(@RequestBody AddToCardRequest request) {
		
		ShoppingCategory exeCategory = categoryRepo.findById(request.getCategoryId()).get();
		exeCategory.setShow(Boolean.parseBoolean(request.getIsShow()));
		categoryRepo.save(exeCategory);
		Response response = new Response("Done", request);
		return response;
	}
	
	@PostMapping(value = "/add-user-visibility")
	public Response addUserVisibility(@RequestBody AddToCardRequest request) {
		
		User exeUser = userRepo.findById(request.getUserId()).get();
		exeUser.setActive(Boolean.parseBoolean(request.getIsShow()));
		userRepo.save(exeUser);
		Response response = new Response("Done", request);
		return response;
	}
	
	@PostMapping(value = "/add-delivery-status")
	public Response addDeliveryStatus(@RequestBody AddToCardRequest request) {
		
		Order exeOrder = orderRepository.findById(request.getOrderId()).get();
		exeOrder.setDeliveryOrPickupDone((Boolean.parseBoolean(request.getIsShow())));
		orderRepository.save(exeOrder);
		Response response = new Response("Done", request);
		return response;
	}
	
	@PostMapping(value = "/remove-user")
	public Response removeUser(@RequestBody AddToCardRequest request) {
		
		User exeUser = userRepo.findById(request.getUserId()).get();
		userRepo.delete(exeUser);
		Response response = new Response("Done", request);
		return response;
	}
	
	//add-category-visibility
	@PostMapping(value = "/edit-user")
	public Response editUser(@ModelAttribute UserFormWrapper model) {
		System.out.println("request : "+model);
		try {
			User user = userRepo.findById(model.getUserId()).get();
			if(!user.getPassword().equals(model.getPassword())) {
				user.setPassword(model.getPassword());
			}
			user.setName(model.getName());
			user.setUserName(model.getUserName());
			user.setLastName(model.getLastName());
			user.setEmail(model.getEmail());
			userRepo.save(user);
			Response response = new Response("Done", model);
			return response;
		}catch (Exception e) {
	    	e.printStackTrace();
	    	Response response = new Response("Failure", model);
			return response;
	    } 
	}
	
	@PostMapping(value = "/edit-product")
	public Response editProduct(@RequestPart(value = "productImgFile",required=false) MultipartFile multipartFile,@RequestPart(value = "productImgFile2",required=false) MultipartFile multipartFile2,@ModelAttribute ProductFormWrapper model) {
		 try {
			 System.out.println(model);
			 
			 	//fileStorage.init();
			    Products pro1 = prodRepo.findById(model.getProductId()).get();
			    UUID uuid = UUID.randomUUID();
			    String fileName = null;
			    String fileName2 = null;
			    if(!multipartFile.isEmpty()) {
			    	fileName= uuid.toString()+multipartFile.getOriginalFilename();
			    	fileStorage.store(multipartFile,fileName);
			    	pro1.setPictureUrl("/uploads/"+fileName);
			    	pro1.setImageData1(multipartFile.getBytes());
			    	
			    }
			    if(!multipartFile2.isEmpty()) {
			    	fileName2 = uuid.toString()+multipartFile2.getOriginalFilename(); 
			    	fileStorage.store(multipartFile2,fileName2);
			    	pro1.setAddToCardUrl("/uploads/"+fileName2);
			    	pro1.setImageData2(multipartFile2.getBytes());
			    }
			    pro1.setShoppingCategory(categoryRepo.findById(UUID.fromString(model.getProductCategory())).get());
			    pro1.setPrice(model.getUnitPrice());
			    pro1.setName(model.getProductName());
			    pro1.setShow(Boolean.parseBoolean(model.getProdShow()));
			    pro1.setAvailableQuantity(model.getQuantity());
		        prodRepo.save(pro1);
		        Response response = new Response("Done", model);
				return response;
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	Response response = new Response("Failure", model);
				return response;
		    } 
	}
	
	@PostMapping(value = "/add-category")
	public Response addCategory(@ModelAttribute CategoryWrapper categoryWrapper) {
		try {
			System.out.println("request : "+categoryWrapper);
			ShoppingCategory category = new ShoppingCategory();
			category.setCategoryName(categoryWrapper.getCategoryName());
			categoryRepo.save(category);
			return new Response("Done", categoryWrapper);
		}catch(Exception e) {
			return new Response("Failure", categoryWrapper);
		}
		
	}

}
