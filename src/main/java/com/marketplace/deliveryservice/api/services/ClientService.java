package com.marketplace.deliveryservice.api.services;


import com.marketplace.deliveryservice.api.entity.Client;
import com.marketplace.deliveryservice.api.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void saveClient(Client client) {
        clientRepository.save(client);
    }
}
