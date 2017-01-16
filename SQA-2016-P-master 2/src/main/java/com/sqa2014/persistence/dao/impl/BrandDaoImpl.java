package com.sqa2014.persistence.dao.impl;

import rest.lets.dao.impl.GenericDaoImpl;
import com.sqa2014.persistence.dao.BrandDao;
import com.sqa2014.persistence.entities.Brand;

public class BrandDaoImpl  extends GenericDaoImpl implements BrandDao {
	
	private static Class<?>[] annotatedClasses = {Brand.class};		
	public BrandDaoImpl() {	
		super(Brand.class, annotatedClasses);
	}
	
}
