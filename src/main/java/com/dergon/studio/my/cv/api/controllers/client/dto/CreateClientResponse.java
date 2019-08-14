package com.dergon.studio.my.cv.api.controllers.client.dto;

import com.dergon.studio.my.cv.api.models.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Damian L. Lisas on 2018-08-02
 */
@ApiModel(value = "Response to client requests")
@Getter
@Setter
public class CreateClientResponse {

    @ApiModelProperty(value = "The request email")
    private String email;

    @ApiModelProperty(value = "The client created")
    private Client client;

    @ApiModelProperty(value = "Indicate if the client was successfully created", example = "true")
    private boolean created;

    @ApiModelProperty(value = "The error that causes that the client can't be created", notes = "Only if created = false")
    private String error;

    public CreateClientResponse() {
        super();
    }

    public CreateClientResponse(String email, Client client, boolean created, String error) {
        this.email = email;
        this.client = client;
        this.created = created;
        this.error = error;
    }

}
