package br.com.localweb.app.dtos.requests;

import br.com.localweb.app.domain.enums.ProductType;
import br.com.localweb.app.domain.order.Order;

public record OrderItemRequestDTO(Integer quantity, ProductType product, Order order) {


}
