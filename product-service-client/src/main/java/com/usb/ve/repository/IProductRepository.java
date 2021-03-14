package com.usb.ve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usb.ve.entity.Category;
import com.usb.ve.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findByCategory(Category category);
}
