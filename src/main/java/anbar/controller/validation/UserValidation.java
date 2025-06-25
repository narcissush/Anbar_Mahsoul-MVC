package anbar.controller.validation;
import java.util.regex.*;


public class UserValidation {
    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[A-Za-z]\\w{5,29}$");

    public static boolean isValid(String username) {
        if (username == null) return false;
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public static void main(String[] args) {
        String[] tests = {
                "Geeksforgeeks_21", // true
                "1Geeksforgeeks",   // false
                "User_1",           // true (length 6)
                "Ab",               // false (کوتاه‌تر از 6)
                "User.123",         // false (کاراکتر غیرمجاز ".")
        };
        for (String u : tests) {
            System.out.printf("%s → %b%n", u, isValid(u));
        }
    }
}

