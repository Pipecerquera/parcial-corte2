package com.backend_parcial.backend_parcial.repository;

import com.backend_parcial.backend_parcial.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
