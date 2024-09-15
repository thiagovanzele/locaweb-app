package br.com.localweb.app.dtos;

import br.com.localweb.app.domain.address.Address;
import br.com.localweb.app.domain.client.Client;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ClientDTO(UUID id, @NotBlank(message = "Name must not be null.") String name, @NotBlank(message = "Document must not be null.") String document, Address address, Boolean isActive) {

    public ClientDTO (Client client) {
        this(client.getId(), client.getName(), client.getDocument(), client.getAddress(), client.getActive());
    }

}
