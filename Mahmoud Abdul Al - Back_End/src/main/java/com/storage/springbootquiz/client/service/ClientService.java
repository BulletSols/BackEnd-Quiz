package com.storage.springbootquiz.client.service;

import com.storage.springbootquiz.client.model.Client;
import com.storage.springbootquiz.client.repoistory.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        log.info("Fetching all clients");
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        log.info("Creating client: {}", client);
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {
        log.info("Updating client with id {}: {}", id, updatedClient);
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            existingClient.setName(updatedClient.getName());
            existingClient.setLastName(updatedClient.getLastName());
            existingClient.setMobile(updatedClient.getMobile());
            return clientRepository.save(existingClient);
        }
        return null;
    }
}
