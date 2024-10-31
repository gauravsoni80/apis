package com.users.account.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    public static boolean isValidIndianPhoneNumber(String phoneNumber) {
        // Pattern explanation:
        // ^           : Start of the string
        // (\\+91|91|0)? : Optional country code prefix (+91, 91, or 0)
        // [6789]      : Starts with 6, 7, 8, or 9 (Indian mobile numbers typically start with these digits)
        // \\d{9}      : Followed by exactly 9 digits
        // $           : End of the string
        String regex = "^(\\+91|91|0)?[6789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
