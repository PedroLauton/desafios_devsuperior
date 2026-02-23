package com.example.desafio.services;

import com.example.desafio.dto.request.ClientRequestDTO;
import com.example.desafio.dto.response.ClientResponseDTO;
import com.example.desafio.entities.Client;
import com.example.desafio.repositories.ClientRepository;
import com.example.desafio.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> getAllClients(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(x -> new ClientResponseDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO getClientById(Long Id) {
        Client client = clientRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));
        return new ClientResponseDTO(client);
    }

    @Transactional
    public ClientResponseDTO saveClient(ClientRequestDTO clientRequestDTO) {
        Client client = new Client();
        Client savedClient = clientRepository.save(clientDTOToEntity(client, clientRequestDTO));
        return new ClientResponseDTO(savedClient);
    }

    @Transactional
    public ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) {
        try{
            Client client = clientRepository.getReferenceById(id);
            Client updatedClient = clientRepository.save(clientDTOToEntity(client, clientRequestDTO));
            return new ClientResponseDTO(updatedClient);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente não encontrado.");
        }
    }

    @Transactional
    public void deleteClientById(Long Id) {
        if(!clientRepository.existsById(Id)) {
            throw new ResourceNotFoundException("Cliente não encontrado.");
        }
        clientRepository.deleteById(Id);
    }

    private Client clientDTOToEntity(Client client, ClientRequestDTO clientRequestDTO) {
        client.setName(clientRequestDTO.getName());
        client.setCpf(clientRequestDTO.getCpf());
        client.setIncome(clientRequestDTO.getIncome());
        client.setBirthDate(clientRequestDTO.getBirthDate());
        client.setChildren(clientRequestDTO.getChildren());
        return client;
    }
}
