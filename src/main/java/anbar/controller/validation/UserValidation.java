package anbar.controller.validation;
import anbar.tools.ConnectionProvider;

import java.sql.SQLException;
import java.util.regex.*;


public class UserValidation {

    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[A-Za-z]\\w{5,29}$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(
                    "^(?=.*[0-9])" +
                            "(?=.*[a-z])" +
                            "(?=.*[A-Z])" +
                            "(?=.*[@#$%^&+=!])" +
                            "(?=\\S+$).{8,20}$"
            );

    private static final Pattern NATIONALID_PATTERN =
            Pattern.compile("^[A-Za-z]\\w{5,29}$");

    public static boolean isValid(String username) {
        if (username == null) return false;
        return USERNAME_PATTERN.matcher(username).matches();
    }
}

