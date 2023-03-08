package com.github.Ikhideifidon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.*;
import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemTest {
    private static final int DEFAULT_CAPACITY = 500;
    private static final int FIXED_STRING_LENGTH = 10;
    private static final int MINIMUM_VARIABLE_STRING_LENGTH = 1;
    private static final int MAXIMUM_VARIABLE_STRING_LENGTH = 21;
    private static final int UPPER_BOUND = 20;
    private static final int LOWER_BOUND = 1;
    private static final Random random = new Random();
    private static Item<String, Integer> item;
    private static Item<String, Integer>[] items;
    private static String[] strings;
    private static Dictionary  dictionary;
    private static int[] randomArray;
    private static final String chars = "abcdefghijklmnopqrstuvwxyz0123456789";

    private static class Dictionary {
        private static final int CACHE_SIZE = 1000;
        private final Set<String> words;
        private final List<String> wordList;
        private final List<String> cachedWord;
        private static Random random;

        private Dictionary(String fileName) {
            words = new HashSet<>();
            wordList = new ArrayList<>();
            cachedWord = new ArrayList<>();
            random = new Random();
            loadDictionary(fileName);
        }

        private void loadDictionary(String fileName) {
            try (Reader reader = new FileReader(fileName);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String word = line.trim().toLowerCase();
                    words.add(word);
                    wordList.add(word);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private boolean contains(String word) {
            return words.contains(word.trim().toLowerCase());
        }

        private String getRandomWord() {
            int index = random.nextInt(wordList.size());
            return wordList.get(index);
        }

        private List<String> getRandomWords(int count) {
            if (count <= 0) return new ArrayList<>();
            List<String> result = new ArrayList<>();
            for (int i = 0; i < count; i++)
                result.add(getRandomWordsFromCache());
            return result;
        }

        private String getRandomWordsFromCache() {
            int index = random.nextInt(CACHE_SIZE);
            if (index < cachedWord.size())
                return cachedWord.get(index);
            else {
                index = random.nextInt(wordList.size());
                String word = wordList.get(index);
                // Check if cachedWord is non-full
                if (cachedWord.size() < CACHE_SIZE)
                    cachedWord.add(word);

                // Since cachedWord is full, replace a random index value
                else
                    cachedWord.set(index % CACHE_SIZE, word);
                return word;
            }
        }
    }

    @BeforeAll
    public void setUp() {
        //noinspection unchecked
        items = new Item[DEFAULT_CAPACITY];
        getRandomItems(items);

        int CAPACITY = 5000;
        strings = new String[CAPACITY];
        getVariableLengthRandomString(strings);


        CAPACITY = 1_000_00;
        randomArray = new int[CAPACITY];
        int lowerBound = 1102;
        int upperBound = 83_292;
        generateArray(randomArray, lowerBound, upperBound);

        String path = "src/main/resources/words_alpha.txt";
        dictionary = new Dictionary(path);
    }

    @Test
    public void size() {
        Assertions.assertEquals(item.count(), DEFAULT_CAPACITY);
    }

    @Test
    public void keyIndexedCount() {
        // keyIndexed is a mutating method
        // Make a clone of items
        Item<String, Integer>[] cloned = items.clone();
        Arrays.sort(cloned);
        LinearSorting.keyIndexed(items);
        Assertions.assertEquals(Arrays.toString(cloned), Arrays.toString(items));
    }

    @Test
    public void LSDStringSort() {
        String[] strings =
                {
                        "4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524",
                        "1ICK750", "3CIO720", "1OHV845", "1OHV845", "2RLA629", "2RLA629",
                        "3ATW723"
                };
        String[] cloned = strings.clone();
        Arrays.sort(cloned);
        LinearSorting.LSDStringSort(strings, strings[0].length());
        Assertions.assertEquals(Arrays.toString(cloned), Arrays.toString(strings));

    }

    @Test
    public void countingSort() {
        // Generate 1635378 array of integers in the range of [5, 734)
        // N = 1635378 and k = 734. k = Ω(N). Counting sort is the best fit.
        int[] A = new int[1_635_378];
        int lowerBound = 5;
        int upperBound = 734;
        generateArray(A, lowerBound, upperBound);
        int[] cloned = A.clone();
        Arrays.sort(cloned);
        LinearSorting.countingSort(A);
        Assertions.assertEquals(Arrays.toString(cloned), Arrays.toString(A));
    }

    @Test
    public void radixSort() {
        int[] cloned = randomArray.clone();
        Arrays.sort(cloned);
        LinearSorting.radixSort(randomArray);
        Assertions.assertEquals(Arrays.toString(cloned), Arrays.toString(randomArray));
    }

    @Test
    public void MSDSort() {
        String[] cloned = strings.clone();
        Arrays.sort(cloned);
        MSD.sort(strings);
        Assertions.assertEquals(Arrays.toString(cloned), Arrays.toString(strings));
    }

    @Test
    public void topKFrequentTreeSet() {
        List<String> list = dictionary.getRandomWords(5_000);
        String[] randomWords = list.toArray(new String[0]);
        int k  = generateKValues(list);
        System.out.println(GeneralExercises.topKFrequentTreeSet(randomWords, k));

    }

    @Test
    public void canReach() {
        // Generate random variable-length strings of 0s and 1s and each must start with 0.
        // Generate random integers from [1 : s.length()) for minJump and maxJump.
        // minJump <= maxJump
        // 2 <= s.length() <= 10^5
        int upper = 1_000_00;
        String s = generateRandomZeroesAndOnesStrings(upper);
        int minJump = random.nextInt(upper - 1) + 1;
        int maxJump = minJump + random.nextInt(upper - minJump);
        System.out.println(GeneralExercises.canReach(s, minJump, maxJump));
    }

    // Utility Methods
    // Generate an n-length array within a specific bound
    private static void generateArray(int[] A, int lowerBound, int upperBound) {
        for (int i = 0; i < A.length; i++) {
            int num = random.nextInt(upperBound - lowerBound) + lowerBound;                    // Range shift by 5.
            A[i] = num;
        }
    }


    // Generate a fixed-length string array.
    private String getFixedLengthRandomString() {
        StringBuilder sb = new StringBuilder(FIXED_STRING_LENGTH);
        for (int i = 0; i < FIXED_STRING_LENGTH; i++) {
            int index = random.nextInt(chars.length());
            char character = chars.charAt(index);
            sb.append(character);
        }
        return sb.toString();
    }

    // Generate a variable-length string array.
    // minimum length = 1 and maximum length = 21.
    private void getVariableLengthRandomString(String[] strings) {
        if (strings ==  null)
            //noinspection ConfusingArgumentToVarargsMethod,ConstantValue
            throw new NullPointerException(String.format("%s" + "cannot be null", strings));
        if (strings.length == 0)
            return;

        for (int i = 0; i < strings.length; i++) {
            int count = random.nextInt(MAXIMUM_VARIABLE_STRING_LENGTH) + MINIMUM_VARIABLE_STRING_LENGTH;
            StringBuilder sb = new StringBuilder(count);
            while (count > 0) {
                int j = random.nextInt(chars.length());
                sb.append(chars.charAt(j));
                count--;
            }
            strings[i] = sb.toString();
        }
    }

    private void getRandomItems(Item<String, Integer>[] items) {
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            String key = getFixedLengthRandomString();
            Integer value = random.nextInt(UPPER_BOUND) + LOWER_BOUND;
            item = new Item<>(key, value);                                      // Compare by value.
            items[i] = item;
        }
    }

    private static int generateKValues(List<String> list) {
        Set<String> uniqueWordsInWordList = new HashSet<>(list);
        final int upperLimit = uniqueWordsInWordList.size();
        final int lowerLimit = 1;
        return random.nextInt(upperLimit) + lowerLimit;
    }

    private static String generateRandomZeroesAndOnesStrings(int upper) {
        StringBuilder sb = new StringBuilder("0");
        for (int i = 0; i < upper - 1; i++) {
            int value = random.nextInt(2);
            sb.append(value);
        }
        return sb.toString();
    }
}