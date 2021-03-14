package com.usb.ve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usb.ve.entity.Customer;
import com.usb.ve.entity.Region;

public interface ICustomerRepository extends JpaRepository<Customer, Long>{
	
	public Customer findByNumberID(String numberID);
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByRegion(Region region);
}
