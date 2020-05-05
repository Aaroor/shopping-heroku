package com.shopping.heroku;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.shopping.heroku.model.Order;
import com.shopping.heroku.model.OrderProduct;
import com.shopping.heroku.model.Products;
import com.shopping.heroku.model.Role;
import com.shopping.heroku.model.ShoppingCategory;
import com.shopping.heroku.repository.CategoryRepository;
import com.shopping.heroku.repository.OrderProductRepo;
import com.shopping.heroku.repository.OrderRepository;
import com.shopping.heroku.repository.ProductsRepository;
import com.shopping.heroku.repository.RoleRepository;
import com.shopping.heroku.service.FileStorage;
import com.shopping.heroku.service.OrderService;
import com.shopping.heroku.service.ProductService;

@Component
public class ApplicationStartupRunner implements ApplicationRunner {
	@Autowired
	OrderService orderService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderProductRepo orderProduct;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	ProductsRepository productRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired 
	FileStorage fileStorage;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//fileStorage.init();
		System.out.println(System.getProperty("java.io.tmpdir"));
		//System.out.println("Done");
		//Path rootLocation = Paths.get("upload");
		
		//System.out.println(rootLocation.getRoot());
		/*List<ShoppingCategory> lCat = new ArrayList<ShoppingCategory>();
		ShoppingCategory cat = new ShoppingCategory("Soft Drinks");
		ShoppingCategory cat1 = new ShoppingCategory("Coffee");
		ShoppingCategory cat2 = new ShoppingCategory("Beer");
		ShoppingCategory cat3 = new ShoppingCategory("Wine");
		ShoppingCategory cat4 = new ShoppingCategory("Others");
		lCat.add(cat);
		lCat.add(cat1);
		lCat.add(cat2);
		lCat.add(cat3);
		lCat.add(cat4);
		categoryRepo.saveAll(lCat);
		System.out.println("Added category");*/
		
		/*List<ShoppingCategory> lCategory = categoryRepo.findByCategoryName("Soft Drinks");
		System.out.println(lCategory.get(0).getId());
		
		List<Products> lPro = new ArrayList<Products>();
		
		Products pro1=new Products("Sprite Can",1.65,"/image/soft-drinks/sprite.jpg","/image/product/select-sprite.jpg",lCategory.get(0));
		Products pro2=new Products("Coca Cola Can 355ml",2.25,"/image/soft-drinks/coca-cola.jpg","/image/product/select-coke.jpg",lCategory.get(0));
		lPro.add(pro1);
		lPro.add(pro2);
		productRepo.saveAll(lPro);
		System.out.println("Added product");*/
		
		//INSERT INTO roles() VALUES (1,'ADMIN');
		//Role role = new Role("ADMIN");
		//roleRepo.save(role);
		
	}

}
