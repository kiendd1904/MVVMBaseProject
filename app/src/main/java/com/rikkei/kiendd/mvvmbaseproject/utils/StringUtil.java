package com.rikkei.kiendd.mvvmbaseproject.utils;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static final String PHONE_PATTERN = "^[0-9]{10,11}$";
    private static final String EMAIL_PATTERN = "^(([\\w+-]+\\.)+[\\w+-]+|([a-zA-Z+]{1}|[\\w+-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
        //return email.matches(EMAIL_PATTERN);
    }

    public static boolean isPhoneValid(String phoneNumber) {
        return phoneNumber.matches(PHONE_PATTERN);
    }

    public static boolean isEmpty(String... strings) {
        for (String s : strings) {
            if (isEmpty(s)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check Edit Text input string
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isHalfWidth(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!isHalfWidth(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isHalfWidth(char c) {
        return '\u0000' <= c && c <= '\u00FF'
                || '\uFF61' <= c && c <= '\uFFDC'
                || '\uFFE8' <= c && c <= '\uFFEE';
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}
