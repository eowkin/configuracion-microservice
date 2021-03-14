package com.usb.ve.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usb.ve.entity.Category;
import com.usb.ve.entity.Product;
import com.usb.ve.repository.IProductRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor //Con esto indicamos que se requiere hacer una injeccion de dependencias por constructor
public class ProductServiceImpl implements IProductService{
	/*
	@Autowired
	private IProductRepository repo;
	*/
	
	private final IProductRepository repo;
	
	
	@Override
	public List<Product> listAllProducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		product.setStatus("CREATED");
		product.setCreateAt(new Date());
		return repo.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		Product productDB = getProduct(product.getId());
		
		if(productDB == null) {
			return null;
		}
		
		productDB.setName(product.getName());
		productDB.setDescription(product.getDescription());
		productDB.setCategory(product.getCategory());
		productDB.setPrice(product.getPrice());
		
		return repo.save(productDB);
	}
	
	@Override
	public Product deleteProduct(Long id) {
		// TODO Auto-generated method stub
		Product productDB = getProduct(id);
		
		if(productDB == null) {
			return null;
		}
		
		productDB.setStatus("DELETED");
		return repo.save(productDB);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		// TODO Auto-generated method stub
		return repo.findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Double quantity) {
		// TODO Auto-generated method stub
		Product productDB = getProduct(id);
		System.out.println("productDB: "+productDB);
		if(productDB == null) {
			return null;
		}
		Double stock = productDB.getStock() + quantity;
		productDB.setStock(stock);
		return repo.save(productDB);
	}

	

}
