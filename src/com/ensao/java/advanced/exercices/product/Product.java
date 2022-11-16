package com.ensao.java.advanced.exercices.product;

public class Product extends AbstractProduct {
	
	@Override
	public Product clone() throws CloneNotSupportedException {
		Product product = new Product();

		throw new ToBeCompletedException("Clone a product");
	}
	
	
}