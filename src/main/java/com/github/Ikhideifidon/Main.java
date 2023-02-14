package com.github.Ikhideifidon;

public class Main {
    public static void main(String[] args) {
        Items<String, Integer> item1 = new Items<>("apple", 5);
        Items<String, Integer> item2 = new Items<>("banana", 10);

        System.out.println("Initial items:");
        printInstances();

        item1.add("cherry", 15);
        item2.add("orange", 20);

        System.out.println("After adding new items:");
        printInstances();
    }

    private static void printInstances() {
        Items<?, ?>[] instances = Items.getInstances();
        for (Items<?, ?> instance : instances) {
            if (instance == null) {
                break;
            }
            System.out.println(instance.key() + ": " + instance.value());
        }
        System.out.println();
    }
}
