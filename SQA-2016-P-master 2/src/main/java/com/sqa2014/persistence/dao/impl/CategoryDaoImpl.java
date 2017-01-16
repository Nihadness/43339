package com.sqa2014.persistence.dao.impl;

import com.sqa2014.persistence.dao.CategoryDao;
import com.sqa2014.persistence.entities.Category;

import rest.lets.dao.impl.GenericDaoImpl;

public class CategoryDaoImpl  extends GenericDaoImpl implements CategoryDao {
	
	private static Class<?>[] annotatedClasses = {Category.class};		
	public CategoryDaoImpl() {	
		super(Category.class, annotatedClasses);
	}
	
}
