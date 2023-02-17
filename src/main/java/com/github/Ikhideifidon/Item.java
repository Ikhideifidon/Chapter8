package com.github.Ikhideifidon;

/**
 * This Item class allows a two-way comparison-comparison can be based on the keys or values.
 * @param <K>
 * @param <V>
 */
public class Item<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Item<K, V>> {
    private final K key;
    private final V value;
    private static int n = 0;
    private boolean compareByKey;

    public Item(K key, V value, boolean compareByKey) {
        nullChecker(key, value);
        this.key = key;
        this.value = value;
        this.compareByKey = compareByKey;
        n++;
    }

    public Item(K key, V value) {
        this(key, value,false);
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    public void setCompareByKey(boolean compareByKey) { this.compareByKey = compareByKey; }

    public int count() { return n; }

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

    @Override
    public int compareTo(Item<K, V> other) {
        if (compareByKey)
            return this.key().compareTo(other.key);
        return this.value().compareTo(other.value);
    }
}

