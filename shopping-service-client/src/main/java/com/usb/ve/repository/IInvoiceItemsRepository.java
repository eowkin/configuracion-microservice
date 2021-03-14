package com.usb.ve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usb.ve.entity.InvoiceItem;

public interface IInvoiceItemsRepository extends JpaRepository<InvoiceItem, Long>{

}
