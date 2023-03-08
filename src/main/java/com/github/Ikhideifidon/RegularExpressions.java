package com.github.Ikhideifidon;

public class RegularExpressions {

    public static boolean check(String s) {
        String regex = "^\\$\\d{4}(\\.\\d+)?$";
        return s.matches(regex);
    }

    String s = "this book will cost you $45.60 only. Let me know if you still want to purchase it.";
    // Any word that precedes cost.
    // "(?<=$45.60 )only"

}
