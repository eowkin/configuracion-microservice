package com.usb.ve.service;

import java.util.List;

import com.usb.ve.entity.Customer;
import com.usb.ve.entity.Region;

public interface ICustomerService {
	

    public List<Customer> findCustomerAll();
    public List<Customer> findCustomersByRegion(Region region);

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public  Customer getCustomer(Long id);
}
