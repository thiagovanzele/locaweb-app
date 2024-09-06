package br.com.localweb.app.dtos;

import br.com.localweb.app.domain.client.Client;

import java.util.UUID;

public record ClientDTO(UUID id, String name, String document) {

    public ClientDTO (Client client) {
        this(client.getId(), client.getName(), client.getDocument());
    }

}
