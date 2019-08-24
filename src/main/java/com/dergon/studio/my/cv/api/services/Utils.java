package com.dergon.studio.my.cv.api.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Damian L. Lisas on 2019-08-24
 */
public class Utils {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValidEmail(String email) {
        if(email != null) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            return matcher.find();
        } else {
            return false;
        }
    }
}
