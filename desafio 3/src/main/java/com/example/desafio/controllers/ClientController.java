package com.example.desafio.controllers;

import com.example.desafio.dto.request.ClientRequestDTO;
import com.example.desafio.dto.response.ClientResponseDTO;
import com.example.desafio.entities.Client;
import com.example.desafio.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> getAllClients(Pageable Pageable) {
        Page<ClientResponseDTO> clients = clientService.getAllClients(Pageable);
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        ClientResponseDTO client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> saveClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.saveClient(clientRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientResponseDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(clientResponseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.updateClient(id, clientRequestDTO);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }
}
