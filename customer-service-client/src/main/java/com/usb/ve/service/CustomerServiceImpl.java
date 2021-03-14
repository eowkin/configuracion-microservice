package com.usb.ve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usb.ve.entity.Customer;
import com.usb.ve.entity.Region;
import com.usb.ve.repository.ICustomerRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private ICustomerRepository customerRepository;
	
	@Override
	public List<Customer> findCustomerAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> findCustomersByRegion(Region region) {
		// TODO Auto-generated method stub
		return customerRepository.findByRegion(region);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer customerDB = customerRepository.findByNumberID ( customer.getNumberID () );
        if (customerDB != null){
            return  customerDB;
        }

        customer.setState("CREATED");
        customerDB = customerRepository.save ( customer );
        return customerDB;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer customerDB = getCustomer(customer.getId());
        if (customerDB == null){
            return  null;
        }
        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());

        return  customerRepository.save(customerDB);
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer customerDB = getCustomer(customer.getId());
        if (customerDB ==null){
            return  null;
        }
        customer.setState("DELETED");
        return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		// TODO Auto-generated method stub
		return  customerRepository.findById(id).orElse(null);
	}

}
