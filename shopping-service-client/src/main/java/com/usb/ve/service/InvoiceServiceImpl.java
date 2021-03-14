package com.usb.ve.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usb.ve.client.CustomerClient;
import com.usb.ve.client.ProductClient;
import com.usb.ve.entity.Invoice;
import com.usb.ve.entity.InvoiceItem;
import com.usb.ve.model.Customer;
import com.usb.ve.model.Product;
import com.usb.ve.repository.IInvoiceItemsRepository;
import com.usb.ve.repository.IInvoiceRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InvoiceServiceImpl implements IInvoiceService{

	@Autowired
	private IInvoiceRepository invoiceRepository;   
	
	@Autowired
    private IInvoiceItemsRepository invoiceItemsRepository;
	
	@Autowired
	private CustomerClient customerClient;
	
	@Autowired
	private ProductClient productClient;
	
	@Override
	public List<Invoice> findInvoiceAll() {
		// TODO Auto-generated method stub
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice createInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );
        if (invoiceDB !=null){
            return  invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        
        invoiceDB.getItems().forEach( invoiceItem -> {
            productClient.updateStockProduct( invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });
        
        
        
        return  invoiceDB;
        
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice deleteInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice getInvoice(Long id) {
		// TODO Auto-generated method stub
		 Invoice invoice= invoiceRepository.findById(id).orElse(null);
	        if (null != invoice ){
	            Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
	            invoice.setCustomer(customer);
	            List<InvoiceItem> listItem=invoice.getItems().stream().map(invoiceItem -> {
	                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
	                invoiceItem.setProduct(product);
	                return invoiceItem;
	            }).collect(Collectors.toList());
	            invoice.setItems(listItem);
	        }
	        return invoice ;
	}

}
