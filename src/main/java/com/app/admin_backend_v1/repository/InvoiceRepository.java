package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}