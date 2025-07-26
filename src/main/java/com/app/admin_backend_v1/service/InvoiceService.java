package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.InvoiceCreateDTO;
import com.app.admin_backend_v1.dto.InvoiceDTO;
import com.app.admin_backend_v1.entity.DeveloperCore;
import com.app.admin_backend_v1.entity.Invoice;
import com.app.admin_backend_v1.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    private InvoiceDTO toDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setDeveloperId(invoice.getDeveloper() != null ? invoice.getDeveloper().getId() : null);
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setPaymentStatus(invoice.getPaymentStatus());
        dto.setDueDate(invoice.getDueDate());
        dto.setCreatedAt(invoice.getCreatedAt());
        return dto;
    }

    private Invoice toEntity(InvoiceCreateDTO dto) {
        Invoice invoice = new Invoice();
        DeveloperCore developer = new DeveloperCore();
        developer.setId(dto.getDeveloperId());
        invoice.setDeveloper(developer);
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setPaymentStatus(dto.getPaymentStatus());
        invoice.setDueDate(dto.getDueDate());
        invoice.setCreatedAt(Instant.now());
        return invoice;
    }

    public InvoiceDTO createInvoice(InvoiceCreateDTO dto) {
        Invoice saved = invoiceRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public InvoiceDTO updateInvoice(Integer id, InvoiceDTO dto) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        DeveloperCore developer = new DeveloperCore();
        developer.setId(dto.getDeveloperId());
        invoice.setDeveloper(developer);
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setPaymentStatus(dto.getPaymentStatus());
        invoice.setDueDate(dto.getDueDate());
        Invoice updated = invoiceRepository.save(invoice);
        return toDTO(updated);
    }

    public InvoiceDTO getInvoiceById(Integer id) {
        return invoiceRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteInvoice(Integer id) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("Invoice not found");
        }
        invoiceRepository.deleteById(id);
    }
}