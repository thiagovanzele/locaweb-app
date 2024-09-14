package br.com.localweb.app.services.validators;

import br.com.localweb.app.dtos.requests.OrderRequestDTO;
import br.com.localweb.app.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductQuantityGreaterThanZero implements ValidateOrderInterface{

    @Override
    public void validateOrder(OrderRequestDTO order) {
        order.orderItems().forEach(item -> {
            if (item.quantity() <= 0) {
                throw new ValidationException("Quantity cannot be zero.");
            }
        });
    }
}
