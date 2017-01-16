package com.sqa2014.persistence.dao.impl;

import com.sqa2014.persistence.dao.ColorDao;
import com.sqa2014.persistence.entities.Color;

import rest.lets.dao.impl.GenericDaoImpl;

public class ColorDaoImpl  extends GenericDaoImpl implements ColorDao {
	
	private static Class<?>[] annotatedClasses = {Color.class};		
	public ColorDaoImpl() {	
		super(Color.class, annotatedClasses);
	}
	
}
