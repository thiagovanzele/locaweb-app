package br.com.localweb.app.services.validators;

import br.com.localweb.app.domain.order.Order;
import br.com.localweb.app.dtos.requests.OrderRequestDTO;

public interface ValidateOrderInterface {

    void validateOrder(OrderRequestDTO order);
}
