package com.dergon.studio.my.cv.api.exceptions;

import com.dergon.studio.my.cv.api.controllers.client.dto.CreateClientResponse;
import lombok.Getter;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
@Getter
public class InvalidRequestException extends Exception {

    private CreateClientResponse response;

    public InvalidRequestException(CreateClientResponse response) {
        super();
        this.response = response;
    }
}
