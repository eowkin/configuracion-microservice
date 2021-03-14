package com.usb.ve.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.usb.ve.model.Product;


@FeignClient(name = "product-service")
@RequestMapping(value="/products")
public interface ProductClient {
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);
	
	@GetMapping(value="/{id}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable("id")   Long id,  @RequestParam(name="quantity")  Double quantity);

}
