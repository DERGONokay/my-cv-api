package com.dergon.studio.my.cv.api.controllers.client.dto;

import java.util.regex.Pattern;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
public class ClientConstants {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final boolean CREATED = true;
    public static final boolean NOT_CREATED = false;
    public static final String INVALID_REQUEST_BODY = "Invalid request body, email may not be present or is not valid";
}
