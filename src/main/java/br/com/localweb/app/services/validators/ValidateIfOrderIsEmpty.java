package br.com.localweb.app.services.validators;

import br.com.localweb.app.dtos.requests.OrderRequestDTO;
import br.com.localweb.app.exceptions.ValidationException;

public class ValidateIfOrderIsEmpty implements ValidateOrderInterface{
    @Override
    public void validateOrder(OrderRequestDTO order) {
        if (order.orderItems().isEmpty()) {
            throw new ValidationException("Order must have at least one item.");
        }
    }
}
