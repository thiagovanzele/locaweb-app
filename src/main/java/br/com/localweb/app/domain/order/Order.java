package br.com.localweb.app.domain.order;

import br.com.localweb.app.domain.client.Client;
import br.com.localweb.app.domain.invoice.Invoice;
import br.com.localweb.app.domain.orderItem.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Client client;

    @JsonIgnore
    @OneToOne
    private Invoice invoice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private Double total;

    public void setTotal() {
        this.total = orderItems.stream().mapToDouble(OrderItem::getSubTotal).sum();
    }

    public Order(Client client, List<OrderItem> items) {
        this.client = client;
        this.orderItems = items;
        this.total = items.stream().mapToDouble(OrderItem::getSubTotal).sum();
    }

}


