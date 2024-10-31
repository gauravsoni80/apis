package com.users.account.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
        // Pattern explanation:
        // ^                 : Start of the string
        // [a-zA-Z0-9._%+-]+ : Local part can include letters, numbers, dots, underscores, percent, plus, or hyphens
        // @                 : @ symbol separating local and domain parts
        // [a-zA-Z0-9.-]+    : Domain part can include letters, numbers, dots, or hyphens
        // \\.[a-zA-Z]{2,6}  : Top-level domain (TLD) starting with a dot, followed by 2-6 letters
        // $                 : End of the string
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
