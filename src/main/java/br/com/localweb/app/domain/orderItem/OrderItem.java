package br.com.localweb.app.domain.orderItem;

import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.domain.pk.orderItemPK.OrderItemPK;
import br.com.localweb.app.domain.product.Product;
import br.com.localweb.app.dtos.requests.OrderItemRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_order_item")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;

    private Double subTotal;

    public OrderItem(Order order, Product product, Integer quantity) {
        this.id.setOrder(order);
        this.id.setProduct(product);
        this.quantity = quantity;
        this.subTotal = product.getValue() * quantity;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    @JsonIgnore
    public OrderItemPK getId() {
        return id;
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }



}
