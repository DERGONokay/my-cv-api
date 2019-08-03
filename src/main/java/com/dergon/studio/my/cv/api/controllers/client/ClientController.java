package com.dergon.studio.my.cv.api.controllers.client;

import com.dergon.studio.my.cv.api.controllers.client.dto.ClientRequest;
import com.dergon.studio.my.cv.api.controllers.client.dto.ClientResponse;
import com.dergon.studio.my.cv.api.exceptions.InvalidRequestException;
import com.dergon.studio.my.cv.api.models.Client;
import com.dergon.studio.my.cv.api.services.ClientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

import static com.dergon.studio.my.cv.api.controllers.client.dto.ClientConstants.NOT_CREATED;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    @ApiOperation(value = "Create a client in the database from the given information")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 500, message = "Unexpected error")
    })
    public ResponseEntity<ClientResponse> save(
            @ApiParam(value = "Client information",required = true) @RequestBody @NotNull ClientRequest request
    ) {
        ClientResponse response;

        try {
            response = clientService.save(request);
        } catch (InvalidRequestException e) {
            return new ResponseEntity<>(e.getResponse(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ClientResponse(null, NOT_CREATED, e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
