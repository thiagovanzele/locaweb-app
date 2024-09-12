package br.com.localweb.app.controllers;

import br.com.localweb.app.dtos.InvoiceDTO;
import br.com.localweb.app.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> findById(@PathVariable UUID id) {
        InvoiceDTO invoiceDTO = invoiceService.findById(id);
        return ResponseEntity.ok(invoiceDTO);
    }
}
