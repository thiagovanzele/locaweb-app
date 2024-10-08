package br.com.localweb.app.controllers;

import br.com.localweb.app.domain.client.Client;
import br.com.localweb.app.dtos.ClientDTO;
import br.com.localweb.app.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody @Valid ClientDTO data) {
        Client client = clientService.insert(data, data.address().getZipCode(), data.address().getNumber());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(client.getId()).toUri();
        ClientDTO clientDTO = new ClientDTO(client);
        return ResponseEntity.created(uri).body(clientDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable UUID id) {
        ClientDTO clientDTO = clientService.findById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(@PageableDefault(sort = "name", size = 10) Pageable pageable) {
        Page<ClientDTO> clientDTOS = clientService.findAll(pageable);
        return ResponseEntity.ok(clientDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable UUID id, @RequestBody @Valid ClientDTO data) {
        ClientDTO clientDTO = clientService.update(id, data);
        return ResponseEntity.ok(clientDTO);
    }
}
