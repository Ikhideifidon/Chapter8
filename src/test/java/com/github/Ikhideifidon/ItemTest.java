package com.github.Ikhideifidon;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemTest {
    private static final int DEFAULT_CAPACITY = 500;
    private static final int FIXED_STRING_LENGTH = 10;
    private static final int UPPER_BOUND = 20;
    private static final int LOWER_BOUND = 1;
    private static final Random random = new Random(0);
    private static Item<String, Integer> item;
    private static Item<String, Integer>[] items;
    private static final String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static int[] randomArray;
    private static final int RANGE = 729;

    private String getRandomString() {
        StringBuilder sb = new StringBuilder(FIXED_STRING_LENGTH);
        for (int i = 0; i < FIXED_STRING_LENGTH; i++) {
            int index = random.nextInt(chars.length());
            char character = chars.charAt(index);
            sb.append(character);
        }
        return sb.toString();
    }

    @BeforeAll
    public void setUp() {
        //noinspection unchecked
        items = new Item[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            String key = getRandomString();
            Integer value = random.nextInt(UPPER_BOUND) + LOWER_BOUND;
            item = new Item<>(key, value);                          // Compare by value.
            items[i] = item;
        }
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
        LinearSorting.keyIndexed(items, UPPER_BOUND + 1);
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
        shuffle();
        int[] cloned = randomArray.clone();
        Arrays.sort(cloned);
        LinearSorting.countingSort(randomArray);
        Assertions.assertEquals(Arrays.toString(cloned), Arrays.toString(randomArray));
    }

    private static void shuffle() {
        randomArray = new int[1_635_378];
        for (int i = 0; i < randomArray.length; i++) {
            int num = random.nextInt(RANGE) + 5;                    // Range shift by 5.
            randomArray[i] = num;
        }
    }

    @Test
    public void radixSort() {

    }



}