package com.github.Ikhideifidon;

public class RegularExpressions {

    public static boolean check(String s) {
        String regex = "^\\$\\d{4}(\\.\\d+)?$";
        return s.matches(regex);
    }
}
