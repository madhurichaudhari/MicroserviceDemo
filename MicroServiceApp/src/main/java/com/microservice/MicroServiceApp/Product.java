package com.microservice.MicroServiceApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;



@Entity
public class Product {

	private Long id;
	@Size(min=2,message="Name should have alteast 2 character")
	private String name;
	
	@Size(min=2,message="Brandyyyy should have alteast 2 character")
	private String brand;
	private String port;
	public String getPort() {
		return port;
	}
	
	

	public void setPort(String port) {
		this.port = port;
	}

	private float price;

	public Product() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
