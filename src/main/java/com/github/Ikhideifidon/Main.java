package com.github.Ikhideifidon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String path = "src/main/resources/sample.txt";
        List<String> result = test(path);
        System.out.println(result);

        System.out.println(GeneralExercises.multiply("177772366542", "1172571"));
        String s = "assurance";
        String b =  (String) s.subSequence(1, s.length());
        System.out.println(b);
        String sentences = "706hzu76jjh7yufr5x9ot60v149k5 $7651913186 pw2o $6";
        int discount = 56;
        System.out.println(GeneralExercises.discountPrices(sentences, discount));

        float number = 453738.878F;
        DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
        System.out.println(DECIMAL_FORMAT.format(number));

        int[] arr = {100,-23,-23,404,100,23,23,23,3,404};
        System.out.println(GeneralExercises.minJumps(arr));

        System.out.println(RegularExpressions.check("$2367"));

        int[] sample = {6,4,14,6,8,13,9,7,10,6,12};
        int d = 2;
        System.out.println(GeneralExercises.maxJumpsDynamicProgramming(sample, d));

        String regex =  "you(?= \\$\\d{2}\\.\\d{1})";
        String ss = "this book will cost you $45.60 only. Let me know if you still want to purchase it.";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ss);
        if (matcher.find())
            System.out.println(matcher.group());


        int[] nums = {1, -1, -2, 4, -7, 3};
        int k = 2;
        System.out.println(GeneralExercises.maxResult(nums, k));



    }

    private static List<String> test(String fileName) {
        List<String> result = new ArrayList<>();
        try (Reader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String word = line.trim().toLowerCase();
                result.add(word);
            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
