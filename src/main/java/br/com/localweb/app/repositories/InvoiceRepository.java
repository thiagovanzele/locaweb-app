package br.com.localweb.app.repositories;

import br.com.localweb.app.domain.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
