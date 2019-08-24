package com.dergon.studio.my.cv.api.services.client;

import com.dergon.studio.my.cv.api.controllers.client.dto.ClientConstants;
import com.dergon.studio.my.cv.api.controllers.client.dto.CreateClientRequest;
import com.dergon.studio.my.cv.api.controllers.client.dto.CreateClientResponse;
import com.dergon.studio.my.cv.api.exceptions.InvalidRequestException;
import com.dergon.studio.my.cv.api.models.Client;
import com.dergon.studio.my.cv.api.services.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;


    public List<Client> findAll() {
        return (List<Client>) repository.findAll();
    }

    public CreateClientResponse save(String email) throws InvalidRequestException {
        CreateClientResponse response = new CreateClientResponse();

        response.setEmail(email);

        if(!Utils.isValidEmail(email)) {
            response.setCreated(ClientConstants.NOT_CREATED);
            response.setError(ClientConstants.INVALID_REQUEST_BODY);

            throw new InvalidRequestException(response);
        }

        Optional<Client> optional = repository.findByEmail(email);
        Client client;

        if(optional.isPresent()) {
            client = optional.get();
            client.addDownload();

            response.setCreated(ClientConstants.NOT_CREATED);
        } else {
            client = new Client();
            client.setEmail(email);
            client.setDownloadNumbers(ClientConstants.FIRST_TIME);

            response.setCreated(ClientConstants.CREATED);
        }

        response.setClient(repository.save(client));

        return response;
    }

    public Client findById(Long id) {
        Optional<Client> client = repository.findById(id);

        return client.orElse(null);
    }

    public void delete(Client client) {

    }
}
