package com.github.Ikhideifidon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
