package org.jsp.UserProductApp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jsp.UserProductApp.dto.Product;
import org.jsp.UserProductApp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductDao {
	@Autowired
	private EntityManager manager;

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	
	public Product saveProduct(Product product,int user_id) {
		User u=manager.find(User.class, user_id);
		if(u!=null) {
			u.getProduct().add(product);//addding the products to the user
			product.setUser(u);//assigning the user to product
			EntityTransaction t=manager.getTransaction();
			manager.persist(product);
			t.begin();
			t.commit();
			return product;
		}
		return null;
	}

}
