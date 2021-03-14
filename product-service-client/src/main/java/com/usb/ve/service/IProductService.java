package com.usb.ve.service;

import java.util.List;

import com.usb.ve.entity.Category;
import com.usb.ve.entity.Product;

public interface IProductService {

	public List<Product> listAllProducts();
	
	public Product getProduct(Long id);
	
	public Product createProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public Product deleteProduct(Long id);
	
	public List<Product> findByCategory(Category category);
	
	public Product updateStock(Long id, Double quantity);
}
