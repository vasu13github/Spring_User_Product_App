package org.jsp.UserProductApp.controller;

import java.util.Scanner;

import org.jsp.UserProductApp.UserConfig;
import org.jsp.UserProductApp.dao.ProductDao;
import org.jsp.UserProductApp.dao.UserDao;
import org.jsp.UserProductApp.dto.Product;
import org.jsp.UserProductApp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class User_Product_Controller {
	static Scanner sc=new Scanner(System.in);
	static UserDao udao;
	static ProductDao pdao;
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(UserConfig.class);
		udao=context.getBean(UserDao.class);
		pdao=context.getBean(ProductDao.class);
		
	}
	public static void main(String[] args) {
		System.out.println("1.save user");
		System.out.println("2.save product");
		System.out.println("enter the choice ");
		int choice =sc.nextInt();
		switch (choice) {
		case 1:
			saveUser();
			break;
		case 2:
			saveProduct();
			break;
		default:
			break;
		}
	}
	
	public static void saveUser() {
		System.out.println("enter the name,email,password");
		String name=sc.next();
		String email=sc.next();
		String password=sc.next();
		 User u=new User();
		 u.setEmail(email);
		u.setName(name);
		u.setPassword(password);
		u=udao.saveUser(u);
		System.out.println("user saved with the id ;"+u.getId());
	}
	
	public static void saveProduct() {
		System.out.println("enter userid ");
		int uid=sc.nextInt(); 
		System.out.println("enter brand ,categeory,cost");
		String brand=sc.next();
		String categeory=sc.next();
		double cost=sc.nextDouble();
		
		Product p=new Product();
		p.setBrand(brand);
		p.setCost(cost);
		p.setCategeory(categeory);
		p.setId(uid);
	Product	prod=pdao.saveProduct(p, uid);
		System.out.println("product saved with id :"+prod.getId());
	}

}
