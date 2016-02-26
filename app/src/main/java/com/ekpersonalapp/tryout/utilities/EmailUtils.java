package com.ekpersonalapp.tryout.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ekta on 18/2/16.
 */
public class EmailUtils {

    public static boolean isEmailValid(String emailAddress) {
        Pattern emailPattern = Pattern
                .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\" +
                        ".[A-Za-z]{1,})$");

        // Match the given string with the pattern
        Matcher emailMatcher = emailPattern.matcher(emailAddress);

        // check whether match is found
        boolean matchFound = emailMatcher.matches();
        return matchFound;
    }
}
