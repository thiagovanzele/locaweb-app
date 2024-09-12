package br.com.localweb.app.services;

import br.com.localweb.app.domain.invoice.Invoice;
import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.dtos.InvoiceDTO;
import br.com.localweb.app.exceptions.ResourceNotFoundException;
import br.com.localweb.app.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public void insert(Order order) {
        Invoice invoice = new Invoice();
        invoice.setClient(order.getClient());
        invoice.setOrder(order);
        invoice.setIssueDate(LocalDateTime.now());
        invoice.calculateTax();
        invoice.calculateInvoiceAmount();

        invoiceRepository.save(invoice);
    }

    public InvoiceDTO findById(UUID id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Invoice.class, id));
        return new InvoiceDTO(invoice);
    }
}
