package com.github.Ikhideifidon;

public class Main {
    public static void main(String[] args) {
        Item<String, Integer> item = new Item<>("ENG1002138", 4);

        System.out.println(item.count());
        System.out.println(item);

    }
}
