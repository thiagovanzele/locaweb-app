package br.com.localweb.app.domain.invoice;

import br.com.localweb.app.domain.client.Client;
import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @ManyToMany
    @JoinTable(
            name = "tb_invoice_product",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    private LocalDateTime issueDate;

    private Double tax;

    private Double grossValue;

    @OneToOne
    private Order order;

    public void calculateInvoiceAmount() {
        this.grossValue = products.stream()
                .mapToDouble(Product::getValue)
                .sum();
    }



}
