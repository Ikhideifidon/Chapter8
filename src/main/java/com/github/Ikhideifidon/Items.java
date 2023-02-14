package com.github.Ikhideifidon;

import java.util.Arrays;

public class Items<K, V> {
    private K key;
    private V value;
    private static final Items<?, ?>[] instances = new Items<?, ?>[100];
    private static int count = 0;

    public Items(K key, V value) {
        this.key = key;
        this.value = value;
        instances[count++] = this;
    }

    public Items() {
        // Default constructor with no arguments
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public static Items<?, ?>[] getInstances() {
        return Arrays.copyOf(instances, count);
    }

    public void add(K key, V value) {
        Items<K, V> newItem = new Items<>(key, value);
    }
}

