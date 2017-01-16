package com.sqa2014.persistence;

import java.io.IOException;

import com.sqa2014.persistence.dao.*;
import com.sqa2014.persistence.dao.impl.*;
import com.sqa2014.persistence.entities.*;
import com.sqa2016.api.ApiHandler;

/**
*
* @author Nihadness
*/
public class Main {
	public static boolean started;

	public static void main(String[] args) {
		//System.out.println("******* STARTED *******");

		started=true;
		// TODO Testing
		
		/*ProductDao products = new ProductDaoImpl();
		BrandDao brands = new BrandDaoImpl();
		ColorDao colors = new ColorDaoImpl();
		CategoryDao categories = new CategoryDaoImpl();
		UserDao users = new UserDaoImpl();

		Category cat = new Category();
		cat.setName("T-Shirt");
		categories.create(cat);
		System.out.println(((Category)categories.get((long)1)).toString());
		
		Brand br = new Brand();
		br.setName("N&M");
		brands.create(br);
		System.out.println(((Brand)brands.get((long)1)).toString());
		
		Color col = new Color();
		col.setName("turquoise");
		colors.create(col);
		System.out.println(((Color)colors.get((long)1)).toString());
		
		User user = new User();
		user.setName("Nihad");
		user.setSurname("ness");
		user.setPassword("guessme");
		user.setRole("Admin");
		users.create(user);
		System.out.println(((User)users.get((long)1)).toString());

		Product product = new Product();
		product.setAddedUser(user);
		product.setBrand(br);
		product.setColor(col);
		product.setCategory(cat);
		product.setImageUrl("nm.com//blahblah");
		products.create(product);
		System.out.println(((Product)products.get((long)1)).toString());*/
		try {
			ApiHandler.run();
		} catch (IOException e) {
			// TODO Testing
			e.printStackTrace();
		}
		System.out.println("******* END OF MAIN *******");
	}
}
