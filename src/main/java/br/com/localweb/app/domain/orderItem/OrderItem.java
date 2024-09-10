package br.com.localweb.app.domain.orderItem;

import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.domain.product.Product;
import br.com.localweb.app.dtos.requests.OrderItemRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_order_item")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Order order;

    private Integer quantity;

    private Double subTotal;

    public OrderItem(Product product, OrderItemRequestDTO data) {
        this.product = product;
        this.order = new Order();
        this.quantity = data.quantity();
        this.subTotal = data.productType().getPrice() * data.quantity();
    }



}
