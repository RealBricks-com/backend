package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.InvoiceCreateDTO;
import com.app.admin_backend_v1.dto.InvoiceDTO;
import com.app.admin_backend_v1.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public InvoiceDTO createInvoice(@RequestBody InvoiceCreateDTO dto) {
        return invoiceService.createInvoice(dto);
    }

    @PutMapping("/{id}")
    public InvoiceDTO updateInvoice(@PathVariable Integer id, @RequestBody InvoiceDTO dto) {
        return invoiceService.updateInvoice(id, dto);
    }

    @GetMapping("/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable Integer id) {
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Integer id) {
        invoiceService.deleteInvoice(id);
    }
}