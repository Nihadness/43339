package com.sqa2014.persistence.dao.impl;

import java.util.Collection;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sqa2014.persistence.HibernateUtil;
import com.sqa2014.persistence.dao.ProductDao;
import com.sqa2014.persistence.entities.*;

import rest.lets.dao.impl.GenericDaoImpl;

/**
*
* @author Nihadness
*/
public class ProductDaoImpl  extends GenericDaoImpl implements ProductDao{

	private static Class<?>[] annotatedClasses = {Product.class, Brand.class,Color.class,User.class,Category.class};		
	
	public ProductDaoImpl() {	
		super(Product.class, annotatedClasses);
	}
	
	public String getProducts(String color,String cat, String br){
		String result="";
		boolean isFirst = true;
		String queryString = "SELECT * FROM softwearhouse.Products p where  ";
		if (color != null) {
			queryString += "color_id=" + color;
			isFirst = false;
		}
		if (cat != null) {
			if (!isFirst) {
				queryString += " and ";
			}
			queryString += " category_id=" + cat;
			isFirst = false;
		}
		if (br != null) {
			if (!isFirst) {
				queryString += " and ";
			}
			queryString += " brand_id=" + br;
			isFirst = false;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery(queryString);
		query.addEntity(Product.class);
		if (query.list().isEmpty()) {
			result="ZERO";
		} else {
			result=careateJsonString((Collection<Product>)query.list());
		}	
		return result;
	}
	
	private String careateJsonString(Collection<Product> products){
		String result="{\"records\":[";
		for (Product product : products) {
			result+=(product.toString())+",";
		}
		result=result.substring(0, result.length()-1);
		result+="]}";
		return result;
	}
}
