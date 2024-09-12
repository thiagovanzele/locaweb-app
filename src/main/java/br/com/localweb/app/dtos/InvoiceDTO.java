package br.com.localweb.app.dtos;

import br.com.localweb.app.domain.invoice.Invoice;
import br.com.localweb.app.domain.order.Order;

import java.util.UUID;

public record InvoiceDTO(UUID id, Order order, Double tax, Double total) {

    public InvoiceDTO(Invoice data) {
        this(data.getId(), data.getOrder(), data.getTax(), data.getIssueTotal());
    }
}
