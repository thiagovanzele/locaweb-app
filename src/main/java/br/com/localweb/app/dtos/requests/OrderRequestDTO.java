package br.com.localweb.app.dtos.requests;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(UUID clientID, List<OrderItemRequestDTO> orderItems) {

}
