package com.github.Ikhideifidon;

import java.util.*;

public class GeneralExercises {

    // 347. Top K Frequent Elements
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null) return null;
        int n = nums.length;
        if (n == 0) return new int[0];
        // k is in the range [1, the number of unique elements in the array].
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums)
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);

        // Create a bucket of array of List
        //noinspection unchecked
        List<Integer>[] bucket = new List[n + 1];
        for (int key : frequency.keySet()) {
            int count = frequency.get(key);
            if (bucket[count] == null)
                bucket[count] = new ArrayList<>();
            bucket[count].add(key);
        }

        int index = 0;
        int[] result = new int[k];
        for (int i = bucket.length - 1; i >= 0 && index < k; i--) {
            if (bucket[i] != null) {
                for (int position = 0; position < bucket[i].size(); position++) {
                    result[index++] = bucket[i].get(position);
                    if (index == k)
                        break;
                }
            }
        }
        return result;
    }

    // 692. Top K Frequent Words
    // Using TreeSet
    public static List<String> topKFrequentTreeSet(String[] words, int k) {
        if (words == null) return null;
        int N = words.length;
        if (N == 0) return new ArrayList<>();

        // Create a frequency Map
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words)
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);

        // Create a buckets array of TreeSet. TreeSet is preferred because of its sorted nature.
        //noinspection unchecked
        TreeSet<String>[] buckets = new TreeSet[N + 1];

        // buckets[i] = new TreeSet<> and put its key
        for (Map.Entry<String, Integer> map : frequency.entrySet()) {
            String word = map.getKey();
            Integer count = map.getValue();
            if (buckets[count] == null)
                buckets[count] = new TreeSet<>();
            buckets[count].add(word);
        }

        List<String> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                TreeSet<String> temp = buckets[i];
                if (temp.size() < k) {
                    k -= temp.size();
                    result.addAll(temp);
                }
                else {
                    while (k > 0) {
                        result.add(temp.pollFirst());
                        k--;
                    }
                    break;
                }
            }
        }
        return result;
    }

    // Using Minimum PriorityQueue
    public static List<String> topKFrequentMinPQ(String[] words, int k) {
        if (words == null) return null;
        int N = words.length;
        if (N == 0) return new ArrayList<>();

        // Create a frequency Map
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words)
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);

        // Create a minimum priorityQueue
        Comparator<Map.Entry<String, Integer>> comp = (e1, e2) -> {
            String word1 = e1.getKey();
            String word2 = e2.getKey();
            int count1 = e1.getValue();
            int count2 = e2.getValue();
            if (count1 == count2)
                return word2.compareTo(word1);
            return Integer.compare(count1, count2);
        };
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k + 1, comp);

        for (Map.Entry<String, Integer> map : frequency.entrySet()) {
            pq.offer(map);

            if (pq.size() > k)
                pq.poll();
        }

        List<String> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            if (pq.peek() != null)
                result.add(i, pq.poll().getKey());
        }

        return result;
    }
}
