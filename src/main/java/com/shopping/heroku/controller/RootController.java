package com.shopping.heroku.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.heroku.model.ShoppingCategory;
import com.shopping.heroku.model.User;
import com.shopping.heroku.repository.CategoryRepository;
import com.shopping.heroku.repository.OrderRepository;
import com.shopping.heroku.repository.ProductsRepository;
import com.shopping.heroku.service.OrderService;
import com.shopping.heroku.service.UserService;
import com.shopping.heroku.service.UtilService;

@Controller
@RequestMapping(value = "/")
public class RootController {
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
	UserService userService;

	@GetMapping(value = "/")
	public String index(Model model, HttpServletRequest request) {
		List<ShoppingCategory> lCategory = categoryRepo.findByIsShowTrue();
		model.addAttribute("lCategory", lCategory);
		// Optional<Order> orders= orderRepo.findById(1L);
		model.addAttribute("orders", orderService.createOrUpdateOrder(utilService.findClientIp(request)));

		String remoteAddr = utilService.findClientIp(request);
		System.out.println(remoteAddr);
		return "index";
	}

//	@GetMapping(value = "/login")
//	public String adminLogin(Model model, HttpServletRequest request) {
//
//		return "admin/login";
//	}

	/*@GetMapping(value = "/registration")
	public String registration(Model model) {
		// ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		model.addAttribute("user", user);
		return "admin/register";
	}

	@PostMapping(value = "/registrationform")
	public String createNewUser(@Valid User user,Model model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			//modelAndView.setViewName("registration");
			return "admin/register";
		}
		System.out.println("I am here 1");
		User userExists = userService.findUserByUserName(user.getUserName());
		System.out.println("I am here 2");
		if (userExists != null) {
			bindingResult.rejectValue("userName", "error.user",
					"There is already a user registered with the user name provided");
		}
		System.out.println("I am here 3");
		if (bindingResult.hasErrors()) {
			//modelAndView.setViewName("registration");
			return "admin/register";
		} else {
			userService.saveUser(user);
			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("user", new User());
			return "admin/register";

		}
		
	}*/
	
	   @RequestMapping(value="/registration", method = RequestMethod.GET)
	    public ModelAndView registration(){
	        ModelAndView modelAndView = new ModelAndView();
	        User user = new User();
	        modelAndView.addObject("user", user);
	        modelAndView.setViewName("register");
	        return modelAndView;
	    }

	    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
	        ModelAndView modelAndView = new ModelAndView();
	        User userExists = userService.findUserByUserName(user.getUserName());
	        if (userExists != null) {
	            bindingResult
	                    .rejectValue("userName", "error.user",
	                            "There is already a user registered with the user name provided");
	        }
	        if (bindingResult.hasErrors()) {
	            modelAndView.setViewName("register");
	        } else {
	            userService.saveUser(user);
	            modelAndView.addObject("successMessage", "User has been registered successfully");
	            modelAndView.addObject("user", new User());
	            modelAndView.setViewName("register");

	        }
	        return modelAndView;
	    }
	    
	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public ModelAndView login(){
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("login");
	        return modelAndView;
	    }
	    
}
