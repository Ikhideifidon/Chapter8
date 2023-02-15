package com.github.Ikhideifidon;


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
            item = new Item<>(key, value);
            items[i] = item;
        }
    }

    @Test
    public void size() {
        System.out.println(item.count());
        System.out.println(Arrays.toString(items));
    }

    @Test
    public void keyIndexedCount() {
        // keyIndexed is a mutating method
        // Make a clone of items
        Item<String, Integer>[] cloned = items.clone();
        LinearSorting.keyIndexed(cloned, UPPER_BOUND);
        System.out.println(Arrays.toString(cloned));
    }

    @Test
    public void LSDStringSort() {
        String[] strings =
                {
                        "4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524",
                        "1ICK750", "3CIO720", "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723"
                };
        LinearSorting.LSDStringSort(strings, strings[0].length());
        System.out.println(Arrays.toString(strings));
    }

}