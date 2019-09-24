package com.dergon.studio.my.cv.api.controllers.client.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
@Getter
@Setter
@ToString
@ApiModel
public class CreateClientRequest {

    @ApiModelProperty(value = "client email", required = true, example = "foo@gmail.com")
    private String email;

    public CreateClientRequest() {
        super();
    }

    public CreateClientRequest(String email) {
        this.email = email;
    }
}
