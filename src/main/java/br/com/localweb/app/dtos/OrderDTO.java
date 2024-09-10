package br.com.localweb.app.dtos;

import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.domain.orderItem.OrderItem;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderDTO(UUID orderId, UUID clientId, String clientName, String clientDocument, List<OrderItem> orderItems, Double total) {

    public OrderDTO(Order data) {
        this(data.getId(), data.getClient().getId(), data.getClient().getName(), data.getClient().getDocument(), data.getOrderItems(), data.getTotal());
    }

}
