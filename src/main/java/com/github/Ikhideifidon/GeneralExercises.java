package com.github.Ikhideifidon;

import java.text.DecimalFormat;
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

        // Create a Comparator
        Comparator<Map.Entry<String, Integer>> comp = (e1, e2) -> {
            String word1 = e1.getKey();
            String word2 = e2.getKey();
            int count1 = e1.getValue();
            int count2 = e2.getValue();
            if (count1 == count2)
                return word2.compareTo(word1);
            return Integer.compare(count1, count2);
        };
        // Create a minimum priorityQueue
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k + 1, comp);

        for (Map.Entry<String, Integer> map : frequency.entrySet()) {
            pq.offer(map);

            if (pq.size() > k)
                pq.poll();
        }

        List<String> result = new ArrayList<>(k);
        for (int i = 0; i < k && pq.peek() != null; i++)
            result.add(0, pq.poll().getKey());

        return result;
    }

    // Maximum PriorityQueue
    public static List<String> topKFrequentMaxPQ(String[] words, int k) {
        if (words == null) return null;
        int N = words.length;
        if (N == 0) return new ArrayList<>();

        // Create a frequency Map
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words)
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);

        // Create a Comparator for Max PQ.
        Comparator<Map.Entry<String, Integer>> comp = (e1, e2) -> {
            String word1 = e1.getKey();
            String word2 = e2.getKey();
            int count1 = e1.getValue();
            int count2 = e2.getValue();
            if (count1 == count2)
                return word1.compareTo(word2);
            return Integer.compare(count2, count1);
        };

        // Create a Max PQ
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(frequency.size(), comp);
        for (Map.Entry<String, Integer> map : frequency.entrySet())
            pq.offer(map);

        List<String> result = new ArrayList<>();
        // k is in the range [1, The number of unique words[i]]
        for (int i = 0; i < k && pq.peek() != null; i++)
            result.add(pq.poll().getKey());
        return result;
    }

    public static String decodeMessage(String key, String message) {
        Map<Character, Character> map = new HashMap<>();
        char character = 'a';
        // Map key to alphabets. White space is not mapped.
        for (char letter : key.toCharArray()) {
            if (!map.containsKey(letter) && letter != ' ')
                map.put(letter, character++);
        }
        StringBuilder sb = new StringBuilder();
        for (char letter : message.toCharArray()) {
            if (letter != ' ')
                sb.append(map.get(letter));
            else
                sb.append(' ');
        }
        return sb.toString();
    }

    public static String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        int n = command.length();
        int i = 0;
        while (i < n) {
            char character = command.charAt(i);
            if (character == '(' &&  i + 1 < n) {
                char letter = command.charAt(i + 1);
                if (letter == ')') {
                    sb.append("o");
                    i += 2;
                } else {
                    sb.append("al");
                    i += 4;
                }
            } else {
                sb.append("G");
                i++;
            }
        }
        return sb.toString();
    }

    public String toLowerCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z')
                chars[i] += 32;
        }
        return new String(chars);
    }

    //  Multiply Strings
    public static String multiply(String num1, String num2) {
        // Always makes sure that num1 has a greater length;
        if (num1.length() < num2.length())
            multiply(num2, num1);

        int m = num1.length();                  // Greater length
        int n = num2.length();
        int c = m + n;

        if (m == 1 && num1.charAt(0) == '0' || n == 1 && num2.charAt(0) == '0')
            return "0";

        int[] numbers = new int[c];
        int carry;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int p = (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                int x = i + j + 1;
                int sum = numbers[x] + p;
                carry = sum / 10;
                int p1 = sum % 10;

                numbers[x] = p1;
                numbers[x - 1] += carry;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            if (i == 0 && numbers[i] == 0)
                continue;
            sb.append(numbers[i]);
        }
        return sb.toString();
    }

    // 2288. Apply Discount to Prices
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    public static String discountPrices(String sentence, int discount) {
        if (sentence == null) return null;
        String[] s = sentence.split("\\s+");

        StringBuilder sb = new StringBuilder();
        for (String word : s) {
            if (!word.startsWith("$"))
                sb.append(word);
            else {
                if (examine(word)) {
                    if (discount == 100)
                        sb.append("$0.00");
                    else {
                        String newPrice = calculate(word, discount);
                        sb.append("$").append(newPrice);
                    }
                } else
                    sb.append(word);
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    private static String calculate(String word, int discount) {
        word = word.substring(1);
        double oldPrice = Double.parseDouble(word);
        double discounted = oldPrice * (discount / 100D);
        double newPrice = oldPrice - discounted;
        return DECIMAL_FORMAT.format(newPrice);
    }

    private static boolean examine(String word) {
        return word.matches("^\\$\\d+(\\.\\d{2})?$");
    }

    public static int minJumps(int[] arr) {
        if (arr == null) return 0;
        int n = arr.length;
        if (n <= 2)
            return n <= 1 ? 0 : 1;

        // Create an adjacency list
        Map<Integer, List<Integer>> adjacent = new HashMap<>(n);
        for (int i = 0; i < n; i++)
            // If arr[i] is mapped to null, map it to new ArrayList<>() and add(i), otherwise just add(i).
            adjacent.computeIfAbsent(arr[i], j -> new ArrayList<>()).add(i);

        Deque<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                //noinspection DataFlowIssue
                int head = queue.poll();
                if (head == n - 1)
                    return steps;

                // Neighbors of index head
                List<Integer> neighbors = adjacent.get(arr[head]);
                // Add neighbors obtained from permissible movements.
                neighbors.add(head - 1);
                neighbors.add(head + 1);
                for (int j : neighbors) {
                    if (j >= 0 && j < n && !visited[j]) {
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
                neighbors.clear();
            }
            steps++;
        }
        return steps;
    }

    // 1340. Jump Game V
    public static int maxJumpsGraph(int[] arr, int d) {
        if (arr == null) return 0;
        int n = arr.length;

        if (n == 1) return 1;

        // Create an adjacency list
        //noinspection unchecked
        List<Integer>[] adjacent = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacent[i] = new ArrayList<>();
            for (int j = 1; j <= d && i + j < n && arr[i] > arr[i + j];  j++)
                adjacent[i].add(i + j);

            for (int j = 1; j <= d && i - j >= 0 && arr[i] > arr[i - j]; j++)
                adjacent[i].add(i - j);
        }

        int[] memo = new int[n];
        int max = 1;
        int localMax;
        for (int i = 0; i < n; i++) {
            localMax = dfs(adjacent, i, memo);
            max = Math.max(max, localMax);
        }
        return max;
    }

    private static int dfs(List<Integer>[] adjacent, int source, int[] memo) {
        if (source == adjacent.length)
            return 0;
        if (memo[source] != 0)
            return memo[source];

        int path = 1;
        for (int singleSource : adjacent[source])
            path = Math.max(1 + dfs(adjacent, singleSource, memo), path);

        memo[source] = path;
        return path;
    }

    public static int maxJumpsDynamicProgramming(int[] arr, int d) {
        if (arr == null) return 0;
        int n = arr.length;
        if (n == 1) return 1;

        int[] dp = new int[n];
        int result = 1;
        for (int source = 0; source < n; source++)
            result = Math.max(result, helper(arr, d, n, source, dp));
        return result;
    }

    private static int helper(int[] arr, int d, int n, int i, int[] dp) {
        if (dp[i] != 0)
            return dp[i];

        int result = 1;
        for (int j = 1; j <= d && i + j < n && arr[i] > arr[i + j]; j++)
            result = Math.max(result, 1 + helper(arr, d, n, i + j, dp));

        for (int j = 1; j <= d && i - j >= 0 && arr[i] > arr[i - j]; j++)
            result = Math.max(result, 1 + helper(arr, d, n, i - j, dp));

        return dp[i] = result;
    }

    // Jump Game VII
    public static boolean canReach(String s, int minJump, int maxJump) {
        // s = "0110100010", minJump = 2, maxJump = 3
        // Possible tree (using indices)
        //                   0
        //                    \
        //                     3
        //                    /  \
        //                   5    6
        //                  / \    \
        //                 7   8    9

        int n = s.length();
        if (s.charAt(n - 1) != '0') return false;
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int farthest = 0;
        int i = 0;
        while (!queue.isEmpty()) {
            int index = queue.pop();
            if (index == n - 1) return true;

            // Compute jumps
            for (int j = Math.max(farthest, index + minJump); j <= Math.min(index + maxJump, n - 1); j++) {
                if (s.charAt(j) == '0')
                    queue.offer(j);
            }
            farthest = (index + maxJump + 1);
        }
        return false;
    }

    public static int maxResult(int[]nums, int k) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[nums.length - 1] = nums[nums.length - 1];  // dp[n-1]=nums[n-1]
        return solve(nums, dp, k, 0);
    }
    // recursive solver which finds max score to reach n-1 starting from ith index
    private static int solve(int[]nums, int[]dp, int k, int i) {
        if(dp[i] != Integer.MIN_VALUE) return dp[i];   // already calculated result for index i
        int score = Integer.MIN_VALUE;
        for(int j = 1; j <= k; j++)          // try jumps of all length and choose the one which maximises the score
            if(i + j < nums.length)
                score = Math.max(score, nums[i] + solve(nums, dp, k, i + j));
        return dp[i] = score;
    }

}
