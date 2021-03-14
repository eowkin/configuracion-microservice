package com.usb.ve.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usb.ve.model.Customer;



//@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
//@RequestMapping(name = "/customers")
//@FeignClient(name = "customer-service")
//@RequestMapping("/customers")
@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {

	@GetMapping(value = "/customers/{id}")
	//@GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
}
