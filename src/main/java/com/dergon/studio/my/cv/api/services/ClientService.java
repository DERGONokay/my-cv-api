package com.dergon.studio.my.cv.api.services;

import com.dergon.studio.my.cv.api.controllers.client.dto.ClientRequest;
import com.dergon.studio.my.cv.api.controllers.client.dto.ClientResponse;
import com.dergon.studio.my.cv.api.exceptions.InvalidRequestException;
import com.dergon.studio.my.cv.api.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

import static com.dergon.studio.my.cv.api.controllers.client.dto.ClientConstants.*;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public ClientResponse save(ClientRequest request) throws InvalidRequestException {

        if(!request.isValid()) {
            throw new InvalidRequestException(new ClientResponse(null, NOT_CREATED, INVALID_REQUEST_BODY));
        }

        Client client = new Client(request.getEmail(), Calendar.getInstance());

        return new ClientResponse(repository.save(client), CREATED, null);
    }
}
