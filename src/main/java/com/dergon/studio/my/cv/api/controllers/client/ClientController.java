package com.dergon.studio.my.cv.api.controllers.client;

import com.dergon.studio.my.cv.api.controllers.client.dto.CreateClientRequest;
import com.dergon.studio.my.cv.api.controllers.client.dto.CreateClientResponse;
import com.dergon.studio.my.cv.api.exceptions.InvalidRequestException;
import com.dergon.studio.my.cv.api.models.Client;
import com.dergon.studio.my.cv.api.services.client.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.dergon.studio.my.cv.api.controllers.client.dto.ClientConstants.NOT_CREATED;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ApiOperation(value = "Retrieve all the clients")
    public ResponseEntity<List<Client>>get() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a client by ID")
    public ResponseEntity delete(
            @ApiParam(value = "client ID", required = true) @PathVariable(name = "id") Long id
    ) {
        Client client = clientService.findById(id);

        clientService.delete(client);

        return ResponseEntity.accepted().build();
    }

    @PostMapping("/create")
    @ApiOperation(value = "Creates a client with the given information. If the client already exists it updates his downloads number")
    @ApiResponses( value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 500, message = "Unexpected error")
    })
    public ResponseEntity<CreateClientResponse> save(
            @ApiParam(value = "client information",required = true) @RequestBody @NotNull CreateClientRequest request
    ) {
        CreateClientResponse response;

        try {
            response = clientService.save(request.getEmail());
        } catch (InvalidRequestException e) {
            return new ResponseEntity<>(e.getResponse(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new CreateClientResponse(request.getEmail(),null, NOT_CREATED, e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
