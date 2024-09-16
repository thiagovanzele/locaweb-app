package br.com.localweb.app.domain.invoice;

import br.com.localweb.app.domain.client.Client;
import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.domain.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_invoice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Client client;

    private LocalDateTime issueDate;

    private Double tax;

    private Double grossValue;

    @OneToOne
    private Order order;

    public void calculateInvoiceAmount() {
        this.grossValue = order.getTotal() + calculateTax();
    }

    public Double calculateTax() {
        double tax = 0;
        for (OrderItem item : order.getOrderItems()) {
            tax += item.getSubTotal() * 0.1;
        }

        this.tax = tax;
        return tax;
    }

    public Double getIssueTotal() {
        return grossValue;
    }



}
