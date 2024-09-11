package br.com.localweb.app.services;

import br.com.localweb.app.domain.address.Address;
import br.com.localweb.app.domain.client.Client;
import br.com.localweb.app.dtos.ClientDTO;
import br.com.localweb.app.exceptions.ResourceNotFoundException;
import br.com.localweb.app.repositories.ClientRepository;
import br.com.localweb.app.util.ReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReflectionService reflectionService;

    @Autowired
    private AddressService addressService;

    public Client insert(ClientDTO data, String zipCode, String number) {
        Address adress = addressService.findAddress(zipCode, number);
        Client client = new Client(data);
        client.setAddress(adress);
        return clientRepository.save(client);
    }

    public ClientDTO findById(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Client.class, id));
        return new ClientDTO(client);
    }

    public Page<ClientDTO> findAll(Pageable pageable) {
        List<Client> clients = clientRepository.findAll();
        return clientRepository.findAll(pageable).map(ClientDTO::new);
    }


    public void delete(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException(Client.class, id);
        }

        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Client.class, id));
        client.setIsActive(false);
        clientRepository.save(client);
    }

    public ClientDTO update(UUID id, ClientDTO data)  {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Client.class, id));
        updateClient(client, data);
        clientRepository.save(client);
        return new ClientDTO(client);
    }

    public void updateClient(Client client, ClientDTO data) {
        client.setName(data.name());
        client.setDocument(data.document());
    }
}
