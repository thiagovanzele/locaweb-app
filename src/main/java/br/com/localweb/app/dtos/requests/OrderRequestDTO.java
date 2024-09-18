package br.com.localweb.app.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(@NotNull(message = "A client is required to place the order.") UUID clientID, @NotEmpty(message = "Order must have at least one item.") List<OrderItemRequestDTO> orderItems) {

}
