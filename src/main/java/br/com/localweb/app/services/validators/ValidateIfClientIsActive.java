package br.com.localweb.app.services.validators;

import br.com.localweb.app.dtos.requests.OrderRequestDTO;
import br.com.localweb.app.exceptions.ValidationException;
import br.com.localweb.app.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateIfClientIsActive implements ValidateOrderInterface {

    @Autowired
    private ClientRepository clientRepository;

    public void validateOrder(OrderRequestDTO order) {
        if (!clientRepository.existsById(order.clientID())) {
            throw new ValidationException("Client not found");
        }

        Boolean clientIsActive = clientRepository.findActiveById(order.clientID());

        if (!clientIsActive) {
            throw new ValidationException("Client must be active to place the order.");
        }
    }
}
