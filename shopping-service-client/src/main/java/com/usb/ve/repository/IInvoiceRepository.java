package com.usb.ve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usb.ve.entity.Invoice;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long>{
	
	public List<Invoice> findByCustomerId(Long customerId );
    public Invoice findByNumberInvoice(String numberInvoice);
}
