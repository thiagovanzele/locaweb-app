package br.com.localweb.app.services;

import br.com.localweb.app.domain.client.Client;
import br.com.localweb.app.dtos.ClientDTO;
import br.com.localweb.app.repositories.ClientRepository;
import br.com.localweb.app.util.ReflectionService;
import jakarta.websocket.ClientEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReflectionService reflectionService;

    public Client insert(ClientDTO data) {
        Client client = new Client(data);
        return clientRepository.save(client);
    }

    public ClientDTO findById(UUID id) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        return reflectionService.transform(client, "br.com.localweb.app.dtos");
    }

    public Page<ClientDTO> findAll(Pageable pageable) {
        List<Client> clients = clientRepository.findAll();
        return clientRepository.findAll(pageable).map(ClientDTO::new);
    }


    public void delete(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found");
        }

        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        client.setIsActive(false);
        clientRepository.save(client);
    }

    public ClientDTO update(UUID id, ClientDTO data)  {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        updateClient(client, data);
        clientRepository.save(client);
        return new ClientDTO(client);
    }

    public void updateClient(Client client, ClientDTO data) {
        client.setName(data.name());
        client.setDocument(data.document());
    }
}
