package com.dergon.studio.my.cv.api.controllers.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.regex.Matcher;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
@Getter
@Setter
@ToString
@ApiModel
public class CreateClientRequest {

    public CreateClientRequest() {
        super();
    }

    public CreateClientRequest(String email) {
        this.email = email;
    }

    @ApiModelProperty(value = "client email", required = true, example = "foo@gmail.com")
    private String email;

    @JsonIgnore
    public boolean isValid() {
        if(this.email != null) {
            Matcher matcher = ClientConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(this.email);

            return matcher.find();
        }
        else {
            return false;
        }
    }
}
