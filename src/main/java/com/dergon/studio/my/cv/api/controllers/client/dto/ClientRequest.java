package com.dergon.studio.my.cv.api.controllers.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ClientRequest {

    public ClientRequest() {
        super();
    }

    public ClientRequest(String email) {
        this.email = email;
    }

    @ApiModelProperty(value = "Client email", required = true, example = "foo@gmail.com")
    private String email;

    @JsonIgnore
    public boolean isValid() {
        return !this.email.isEmpty() &&
                this.email != null;
    }
}
