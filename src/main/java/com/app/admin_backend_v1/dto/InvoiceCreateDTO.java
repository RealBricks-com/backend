package com.app.admin_backend_v1.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceCreateDTO {
    private Integer developerId;
    private String invoiceNumber;
    private BigDecimal totalAmount;
    private String paymentStatus;
    private LocalDate dueDate;
}