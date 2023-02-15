package com.github.Ikhideifidon;

public class Item<K, V> {
    private K key;
    private V value;
    private static int n = 0;

    public Item(K key, V value) {
        nullChecker(key, value);
        this.key = key;
        this.value = value;
        n++;
    }

    public Item() {}

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    public int size() { return n; }

    @Override
    public String toString() {
        return "(" + this.key + ", " + this.value + ")";
    }


    private void nullChecker(K key, V value) {
        if (key == null)
            throw new NullPointerException("'key' cannot be null.");
        if (value == null)
            throw new NullPointerException("'value' cannot be null.");
    }
}

