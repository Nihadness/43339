package com.sqa2014.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@OneToOne
	private Brand brand;
	
	@OneToOne
	private Color color;
	
	@OneToOne
	private Category category;
	
	@OneToOne
	private User addedUser;
	
	@Column(name="image_url")
	private String imageUrl;
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getAddedUser() {
		return addedUser;
	}

	public void setAddedUser(User addedUser) {
		this.addedUser = addedUser;
	}
	
	@Override
	public String toString(){
		return "{"+"\"id\":\""+this.getId()+"\", \"brand\":"+this.getBrand().toString()+", \"color\":"+this.getColor().toString()+", \"category\":"+this.getCategory().toString()
		+", \"url\":\""+this.getImageUrl()+"\", \"user\":"+""+this.getAddedUser().toString()+ "}";
	}
	
}
